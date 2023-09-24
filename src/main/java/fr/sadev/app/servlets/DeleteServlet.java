package fr.sadev.app.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.sadev.app.beans.User;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BASE_URL = "http://localhost:9000/api/v1";

	HttpClient httpClient = HttpClient.newHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();

	public DeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUser = request.getParameter("idUser");
		Long id = null;
		if(idUser != null) {
			id = Long.parseLong(idUser);
		}
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		String token = userSession.getAccessToken();
		
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.DELETE()
				.header("Authorization", "Bearer " + token)
				.uri(URI.create(BASE_URL + "/user/" + id)).build();

		try {
			httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			response.sendRedirect(request.getContextPath() + "/dashboard");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
