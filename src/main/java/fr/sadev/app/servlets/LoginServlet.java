package fr.sadev.app.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.sadev.app.beans.Login;
import fr.sadev.app.beans.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BASE_URL = "http://localhost:9000/api/v1";
	private static final String EMAIL_FIELD = "email";
	private static final String PASSWORD_FIELD = "password";

	HttpClient httpClient = HttpClient.newHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter(EMAIL_FIELD);
		String password = request.getParameter(PASSWORD_FIELD);

		Login login = new Login(email, password);
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.POST(BodyPublishers.ofString(objectMapper.writeValueAsString(login)))
				.header("Content-type", "application/json")
				.uri(URI.create(BASE_URL + "/login")).build();

		try {
			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			User user = objectMapper.readValue(httpResponse.body(), new TypeReference<User>() {
			});
			if (user != null) {
				for (String role : user.getRoles()) {
					if (role.contains("ADMIN") || role.contains("SUPER")) {
						HttpSession session = request.getSession();
						session.setAttribute("user", user);
						response.sendRedirect(request.getContextPath() + "/dashboard");
					} else {
						request.setAttribute("message", "Denied access");
						request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
