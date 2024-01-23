package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.MessageDor;
import services.MessageService;

@WebServlet("/messages")
public class MessagesShow extends CommonServlet {
	private static final long serialVersionUID = 1L;
	
	private MessageService messageService = new MessageService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MessageDor> listeMessages = new ArrayList<>();
		
		try {
			listeMessages = messageService.getAllMessageDor();
		} catch (Exception e) {
			e.printStackTrace();
			ajouterErreur(e.getMessage(), request);
		}
		request.setAttribute("messages", listeMessages);
		
		view("messages", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
