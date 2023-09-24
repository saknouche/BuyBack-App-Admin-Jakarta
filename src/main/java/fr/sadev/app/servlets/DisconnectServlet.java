package fr.sadev.app.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DisconnectServlet
 */
public class DisconnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String LOGIN_VIEW = "/login";
       
    public DisconnectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();		
		response.sendRedirect(request.getContextPath() + LOGIN_VIEW);
	}

}
