package servlets;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.MessageDor;
import services.MessageService;

@WebServlet("/update")
public class UpdateMessage extends CommonServlet {

	private static final long serialVersionUID = 1L;

	private MessageService messageService = new MessageService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer numMsg = recupererNumeroMessage(request);
		
		if (Objects.isNull(numMsg)) {
			ajouterErreur("Erreur d'identifiant", request);
			forward("/messages", request, response);
			return;
		}
		MessageDor messageDor = null;
		try {
			messageDor = messageService.getMessageDorById(numMsg);
		} catch (Exception e) {
			ajouterErreur("Aucun message d'or ne correspond à l'id " + numMsg, request);
			forward("/messages", request, response);
			return;
		}
		
		request.setAttribute("messageDor", messageDor);
		
		view("update/index", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer numMsg = recupererNumeroMessage(request);
		String pseudo = (String) request.getParameter("pseudo");
		String message = (String) request.getParameter("message");
		
		if (Objects.isNull(numMsg)) {
			ajouterErreur("Erreur d'identifiant", request);
			forward("/messages", request, response);
			return;
		}
		viderErreurs(request);
		
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
		
		MessageDor messageDor = null;
		try {
			messageDor = messageService.getMessageDorById(numMsg);
		} catch (Exception e) {
			ajouterErreur("Aucun message d'or ne correspond à l'id " + numMsg, request);
			forward("/messages", request, response);
			return;
		}

		// Modification des champs
		messageDor.setPseudo(pseudo);
		messageDor.setMessage(message);
		
		// TODO Mettre en place la modification en base
		try {
			messageService.updateMessageDor(messageDor);
		} catch (Exception e) {
			ajouterErreur("Une erreur est survenue lors de la MAJ", request);
		}
		
		// TODO Prévoir l'affichage d'un message de succès
		if (isValid(request)) {
			ajouterErreur("Tout est OK, message mis à jour", request);
		}
		
		// TODO Mettre en place la redirection
		doGet(request, response);
		
	}

	/**
	 * Récupération du numéro de message
	 * 
	 * @param request
	 * @return
	 */
	private Integer recupererNumeroMessage(HttpServletRequest request) {
		Integer numeroMessage = null;
		
		if (Objects.nonNull(request)) {
			String urlParam = (String) request.getParameter("numMsg");
			
			if (Objects.nonNull(urlParam)) {
				try {
					numeroMessage = Integer.parseInt(urlParam);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return numeroMessage;
	}

}
