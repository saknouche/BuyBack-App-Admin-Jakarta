package fr.sadev.app.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;
import java.net.http.HttpRequest.BodyPublishers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.sadev.app.beans.MessageResponse;
import fr.sadev.app.beans.UserRequest;

public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BASE_URL = "http://localhost:9000/api/v1";
	private static final String FIRSTNAME_FIELD = "firstname";
	private static final String LASTNAME_FIELD = "lastname";
	private static final String EMAIL_FIELD = "email";
	private static final String PASSWORD_FIELD = "password";
	private static final String PASSWORD_CONFIRM_FIELD = "passwordConfirmation";

	HttpClient httpClient = HttpClient.newHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();
       
    public AddAdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/addAdmin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstname = request.getParameter(FIRSTNAME_FIELD);
		String lastname = request.getParameter(LASTNAME_FIELD);
		String email = request.getParameter(EMAIL_FIELD);
		String password = request.getParameter(PASSWORD_FIELD);
		String passwordConfirmation = request.getParameter(PASSWORD_CONFIRM_FIELD);
		
		if(firstname != "" && lastname != "" && email != "" && password != "" && passwordConfirmation != "") {	
			String role = "ADMIN";
			Set<String> roles = new HashSet<>();
			roles.add(role);
			UserRequest addAdmin = new UserRequest(firstname, lastname, email, password, passwordConfirmation, roles);
			HttpRequest httpRequest = HttpRequest.newBuilder()
					.POST(BodyPublishers.ofString(objectMapper.writeValueAsString(addAdmin)))
					.header("Content-type", "application/json")
					.uri(URI.create(BASE_URL + "/register")).build();
			
			try {
				HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
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
		request.getRequestDispatcher("/WEB-INF/addAdmin.jsp").forward(request, response);
	}

}
