package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.MessageService;

@WebServlet("/delete/result")
public class DeleteMessageResult extends CommonServlet {

	private static final long serialVersionUID = 1L;

	private MessageService messageService = new MessageService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
		List<Integer> numMsgList = recupererListeNumMsg(request.getSession());
		
		if (numMsgList.isEmpty()) {
			ajouterErreur("Veuillez cocher au moins un message à supprimer", request);
			return;
		}

		viderSessionValue("numMsgList", null);
		view("delete/result", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Integer> numMsgList = recupererListeNumMsg(session);
		
		if (numMsgList.isEmpty()) {
			ajouterErreur("Veuillez cocher au moins un message à supprimer", request);
			return;
		}
		
		List<Integer> numMsgListExecution;
		try {
			numMsgListExecution = messageService.supprimerMessages(numMsgList);
		} catch (Exception e) {
			ajouterErreur("Veuillez cocher au moins un message à supprimer", request);
			e.printStackTrace();
			return;
		}

		request.setAttribute("statusList", numMsgListExecution);
		request.setAttribute("numMsgList", numMsgList);

		
	}

	/**
	 * Permet de récupérer la liste des identifiants de message
	 * 
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Integer> recupererListeNumMsg(HttpSession session) {
		List<Integer> numMsgList = new ArrayList<>();
		
		if (Objects.nonNull(session)) {
			numMsgList = (List<Integer>) session.getAttribute("numMsgList");
		}
		
		return Objects.isNull(numMsgList)
				? new ArrayList<>()
				: numMsgList;
	}

}
