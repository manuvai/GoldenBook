package servlets;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CommonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void view(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (Objects.nonNull(path)) {

			this.getServletContext()
				.getRequestDispatcher("/WEB-INF/content/" + path + ".jsp")
				.forward(request, response);
		}
	}
}
