package fr.sadev.app.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.sadev.app.beans.Login;
import fr.sadev.app.beans.MessageResponse;
import fr.sadev.app.beans.UpdateUserRequest;
import fr.sadev.app.beans.User;
import fr.sadev.app.beans.UserResponse;

public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BASE_URL = "http://localhost:9000/api/v1";
	private static final String ID_USER_PARAM = "idUser";
	private static final String FIRSTNAME_FIELD = "firstname";
	private static final String LASTNAME_FIELD = "lastname";
	private static final String EMAIL_FIELD = "email";
	private static final String PASSWORD_FIELD = "password";
	private static final String PASSWORD_CONFIRM_FIELD = "passwordConfirmation";
	private static final String ROLE_FIELD = "roles";
	
	HttpClient httpClient = HttpClient.newHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();
	
    public EditUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> initialRoles = new HashSet<>();
		initialRoles.add("USER");
		initialRoles.add("ADMIN");
		initialRoles.add("SUPER");
		request.setAttribute("roles", initialRoles);
		
		//idUser
		Long id = null;
		String idUser = request.getParameter(ID_USER_PARAM);
		if(idUser != null) {
			id = Long.parseLong(idUser);
		}
		
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute("user");
		String token = userSession.getAccessToken();
		//Requête GET
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.GET()
				.header("Authorization", "Bearer " + token)
				.header("accept", "application/json")
				.uri(URI.create(BASE_URL + "/users/" + id)).build();
		try {
			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			
			if(httpResponse.statusCode() == 200) {		
				objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
				UserResponse user = objectMapper.readValue(httpResponse.body(),new TypeReference<UserResponse>(){});
				request.setAttribute("user", user);
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/editUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter(FIRSTNAME_FIELD);
		String lastname = request.getParameter(LASTNAME_FIELD);
		String email = request.getParameter(EMAIL_FIELD);
		String password = request.getParameter(PASSWORD_FIELD);
		String confirmPassword = request.getParameter(PASSWORD_CONFIRM_FIELD);
		String role = request.getParameter(ROLE_FIELD);
		
		
		if(firstname != "" && lastname != "" && email != "" && password != "" && confirmPassword != "" && role != "") {	

			//idUser
			Long id = null;
			String idUser = request.getParameter(ID_USER_PARAM);
			if(idUser != null) {
				id = Long.parseLong(idUser);
			}
			HttpSession session = request.getSession();
			User userSession = (User) session.getAttribute("user");
			String token = userSession.getAccessToken();
			
			Set<String> roles = new HashSet<>();
			roles.add(role);
			UpdateUserRequest editUser = new UpdateUserRequest(firstname, lastname, email, password, confirmPassword, roles);
			HttpRequest httpRequest = HttpRequest.newBuilder()
					.PUT(BodyPublishers.ofString(objectMapper.writeValueAsString(editUser)))
					.header("Authorization", "Bearer " + token)
					.header("Content-type", "application/json")
					.uri(URI.create(BASE_URL + "/users/update/" + id)).build();
			
			try {
				HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
				objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
				MessageResponse messageResponse = objectMapper.readValue(httpResponse.body(), new TypeReference<MessageResponse>() {});
				if(httpResponse.statusCode() == 200) {					
					request.setAttribute("success", messageResponse.getMessage());
				}else {
					request.setAttribute("error", messageResponse.getMessage());
				}				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("error", "All fields are required !");
		}
		request.getRequestDispatcher("/WEB-INF/editUser.jsp").forward(request, response);
	}

}
