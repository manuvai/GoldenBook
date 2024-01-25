package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class CommonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Vide la valeur dans la session
	 * 
	 * @param name
	 * @param session
	 */
	protected void viderSessionValue(String name, HttpSession session) {
		if (Objects.nonNull(session) && Objects.nonNull(name)) {
			session.setAttribute(name, null);
		}
	}

	/**
	 * Redirige la requête vers une page
	 * 
	 * @param path
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void view(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (Objects.nonNull(path)) {

			this.getServletContext()
				.getRequestDispatcher("/WEB-INF/content/" + path + ".jsp")
				.forward(request, response);
		}
	}
	
	/**
	 * Vide la variable contenant les erreurs
	 * 
	 * @param request
	 */
	protected void viderErreurs(HttpServletRequest request) {
		if (Objects.nonNull(request)) {
			request.setAttribute("errors", new ArrayList<>());
		}
	}
	
	/**
	 * Détermine si aucune erreur n'est présent
	 */
	@SuppressWarnings("unchecked")
	protected boolean isValid(HttpServletRequest request) {
		return Objects.nonNull(request)
				&& Objects.nonNull(request.getAttribute("errors"))
				&& ((List<String>) request.getAttribute("errors")).isEmpty();
	}
	
	/**
	 * Ajoute une erreur à la liste
	 * 
	 * @param erreur
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void ajouterErreur(String erreur,  HttpServletRequest request) {
		if (Objects.nonNull(erreur) && Objects.nonNull(request)) {
			List<String> erreurList = (List<String>) request.getAttribute("errors");
			
			List<String> nouvelleErreurList = Objects.isNull(erreurList)
					? new ArrayList<>()
					: erreurList;
			
			nouvelleErreurList.add(erreur);
			
			request.setAttribute("errors", nouvelleErreurList);
		}
	}
}
