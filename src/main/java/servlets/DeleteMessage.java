package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modeles.MessageDor;
import services.MessageService;

@WebServlet("/delete")
public class DeleteMessage extends CommonServlet {

	private static final long serialVersionUID = 1L;

	private MessageService messageService = new MessageService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MessageDor> listeMessages = new ArrayList<>();
		HttpSession session = request.getSession();
		
		boolean isActionNo = isActionNo(request);
		
		if (!isActionNo) {
			viderSessionValue("numMsgList", session);
		}
		
		try {
			listeMessages = messageService.getAllMessageDor();
		} catch (Exception e) {
			e.printStackTrace();
			ajouterErreur(e.getMessage(), request);
		}
		request.setAttribute("messages", listeMessages);
		
		view("delete/index", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> numMsgList = recupererListeNumMsg(request);
		
		if (numMsgList.isEmpty()) {
			ajouterErreur("Veuillez cocher au moins un message à supprimer", request);
			doGet(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		viderSessionValue("numMsgList", session);
		
		session.setAttribute("numMsgList", numMsgList);
		
		response.sendRedirect(request.getContextPath() + "/delete/confirm");
		
	}

	/**
	 * Détermine si la requête demande l'annulation de la suppression ou non
	 * 
	 * @param request
	 * @return
	 */
	private boolean isActionNo(HttpServletRequest request) {
		return Objects.nonNull(request)
				&& Objects.nonNull((String) request.getParameter("a"))
				&& "no".equals((String) request.getParameter("a"));
	}

	/**
	 * Permet de récupérer la liste des identifiants de message
	 * 
	 * @param request
	 * @return
	 */
	private List<Integer> recupererListeNumMsg(HttpServletRequest request) {
		List<Integer> numMsgList = new ArrayList<>();
		
		if (Objects.nonNull(request)) {
			String[] numMsgListValues = request.getParameterValues("num_msg[]");
			
			numMsgList = (Objects.isNull(numMsgListValues))
					? new ArrayList<>()
					: Arrays.asList(numMsgListValues)
							.stream()
							.filter(Objects::nonNull)
							.map(Integer::parseInt)
							.collect(Collectors.toList());
		}
		
		return numMsgList;
	}

}
