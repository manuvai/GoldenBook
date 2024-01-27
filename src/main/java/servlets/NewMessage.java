package servlets;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.MessageDor;
import services.MessageService;

@WebServlet("/new_message")
public class NewMessage extends CommonServlet {
	private static final long serialVersionUID = 1L;
	
	private MessageService messageService = new MessageService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		MessageDor messageDor = new MessageDor();
		messageDor.setPseudo((String) request.getParameter("pseudo"));
		messageDor.setMessage((String) request.getParameter("message"));
		
		request.setAttribute("messageDor", messageDor);
		view("new_message", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = (String) request.getParameter("pseudo");
		String message = (String) request.getParameter("message");
		viderErreurs(request);

		// Gestion des inputs
		if (Objects.isNull(message) || "".equals(message)) {
			ajouterErreur("Le champ message doit être renseigné", request);
		}
		if (Objects.isNull(pseudo) || "".equals(pseudo)) {
			ajouterErreur("Le champ pseudo doit être renseigné", request);
		}
		
		if (!isValid(request)) {
			doGet(request, response);
			return;
		}

		// Insertion en base
		MessageDor messageDor = new MessageDor();
		messageDor.setPseudo(pseudo);
		messageDor.setMessage(message);
		
		int res = 0;
		try {
			res = messageService.ajouterMessage(messageDor);
		} catch (Exception e) {
			ajouterErreur(e.getMessage(), request);
		}
		
		if (res == 0) {
			ajouterErreur("Aucune donné n'a été ajoutée", request);
			doGet(request, response);
			return;
		}
		
		request.setAttribute("total_rows", res);
		view("new_message_success", request, response);
		
	}

}
