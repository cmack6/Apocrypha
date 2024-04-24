package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.booksdb.model.*;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;

//import controllers.GameEngineController;

import edu.ycp.cs320.lab02.model.*;

public class GameLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Login Servlet: doGet");

		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Login Servlet: doPost");
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();

		List<User> userList = db.findAllUsers();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");


		String errorMessage = null;
		Boolean isLoggedIn = false;
		System.out.println(username + " " + password);
		for(User user: userList) {
			System.out.println(user.getUsername() + " " + user.getPassword());
			if(user.getUsername().equals(username)&&user.getPassword().equals(password)) {
				isLoggedIn = true;
				req.getSession().setAttribute("userID", user.getUserID());
				resp.sendRedirect("http://localhost:8081/lab02/account");
			}
		}
		if(!isLoggedIn) {
			req.setAttribute("username",username);
			errorMessage = "Invalid credentials.";
		}



		req.setAttribute("errorMessage", errorMessage);





		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);

	}
}