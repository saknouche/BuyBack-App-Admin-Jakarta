package fr.sadev.app.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sadev.app.beans.User;
import fr.sadev.app.repository.UserRepository;
import fr.sadev.app.utils.JPAUtil;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EntityManagerFactory emf;
	UserRepository userRepo;
    public LoginServlet() {
         emf = JPAUtil.getEntityManagerFactory("db_config");
         userRepo = new UserRepository(emf, User.class);
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 List<User> users = userRepo.findAll();
		 System.out.println(users);
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

