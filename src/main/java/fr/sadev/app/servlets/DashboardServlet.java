package fr.sadev.app.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.sadev.app.beans.Login;
import fr.sadev.app.beans.User;
import fr.sadev.app.beans.UserResponse;

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String BASE_URL = "http://localhost:9000/api/v1";
	private static final String ATT_USERS = "users";

	HttpClient httpClient = HttpClient.newHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();

	public DashboardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		String token = userSession.getAccessToken();
		
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.GET()
				.header("Authorization", "Bearer " + token)
				.header("accept", "application/json")
				.uri(URI.create(BASE_URL + "/users")).build();
		try {
			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			List<UserResponse> users = objectMapper.readValue(httpResponse.body(),new TypeReference<List<UserResponse>>(){});
			request.setAttribute(ATT_USERS, users);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
