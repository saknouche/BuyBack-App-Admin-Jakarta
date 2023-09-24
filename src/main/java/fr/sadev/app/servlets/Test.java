//package fr.sadev.app.servlets;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URI;
//import java.net.URL;
//import java.net.http.HttpClient;
//import java.net.http.HttpHeaders;
//import java.net.http.HttpRequest;
//import java.net.http.HttpRequest.BodyPublisher;
//import java.net.http.HttpRequest.BodyPublishers;
//import java.net.http.HttpResponse;
//import java.net.http.HttpResponse.BodyHandler;
//import java.net.http.HttpResponse.BodyHandlers;
//import java.util.List;
//import java.util.Scanner;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.mysql.cj.Session;
//
//import fr.sadev.app.beans.Login;
//import fr.sadev.app.beans.Tutorial;
//import fr.sadev.app.beans.User;
//
///**
// * Servlet implementation class Test
// */
//public class Test extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Test() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//   
//    HttpClient httpClient = HttpClient.newHttpClient();
//    ObjectMapper objectMapper = new ObjectMapper();
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//HttpClient
//		HttpRequest httpRequest = HttpRequest.newBuilder()
//				.GET()
//				.header("accept", "application/json")
//				.uri(URI.create("http://localhost:9000/api/v1/users"))
//				.build();
//		try {
//			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
////			System.out.println(httpResponse.body());
//			//Parse Json into objects
//			List<Tutorial> tutos = objectMapper.readValue(httpResponse.body(), new TypeReference<List<Tutorial>>() {});
//			System.out.println(tutos);
//			for (Tutorial tutorial : tutos) {
//				System.out.println(tutorial);
//				
//			}
//		} catch (IOException | InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//POST
////		Tutorial tuto = new Tutorial("Français", "Un trés bon tutoriel de langue", false);
////		HttpRequest httpRequest = HttpRequest.newBuilder()
////				.POST(BodyPublishers.ofString(objectMapper.writeValueAsString(tuto)))
////				.header("Content-type", "application/json")
////				.uri(URI.create("http://localhost:9092/tuto"))
////				.build();
////
////		try {
////			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
////			System.out.println(httpResponse.body());
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
////		POST
//		Login login = new Login("sadev@gmail.com", "root");
//		HttpRequest httpRequest1 = HttpRequest.newBuilder()
//				.POST(BodyPublishers.ofString(objectMapper.writeValueAsString(login)))
//				.header("Content-type", "application/json")
//				.uri(URI.create("http://localhost:9000/api/v1/login"))
//				.build();
//
//		try {
////			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//			HttpResponse<String> httpResponse1 = httpClient.send(httpRequest1, HttpResponse.BodyHandlers.ofString());
//			System.out.println(httpResponse1.body());
//			User user = objectMapper.readValue(httpResponse1.body(), new TypeReference<User>() {});
//			if(user != null) {
//				HttpSession session = request.getSession();
//				session.setAttribute("user", user.getTokenType() + " " + user.getAccessToken());
//				System.out.println(session.getAttribute("user"));
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//*****String authHeader = request.getHeader("authorization");  ******/        
//
//		//PUT
////		Tutorial updatedTuto = new Tutorial("Net", "Un tuto très léger !", true);
////		HttpRequest httpRequest = HttpRequest.newBuilder()
////				.PUT(BodyPublishers.ofString(objectMapper.writeValueAsString(updatedTuto)))
////				.header("Content-type", "application/json")
////				.uri(URI.create("http://localhost:9092/tuto/28"))
////				.build();
////
////		try {
////			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
////			System.out.println(httpResponse.body());
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		//DELETE
////		HttpRequest httpRequest = HttpRequest.newBuilder()
////				.DELETE()
////				.header("Content-type", "application/json")
////				.uri(URI.create("http://localhost:9092/tuto/27"))
////				.build();
////
////		try {
////			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
////			System.out.println(httpResponse.statusCode());
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	
////		doGet(request, response);
//	}
//
//}
//
//
//
////https://www.youtube.com/watch?v=FQ9k5uoQtcw