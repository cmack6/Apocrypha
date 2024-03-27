package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.GameEngineController;
import models.GameModel;
import models.LoginModel;

public class LoginServlet extends HttpServlet {
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


		LoginModel model = new LoginModel();
		model.setUsername(req.getParameter("username"));
		model.setPassword(req.getParameter("password"));
		String testUsername = "spencer";
		String testUsername2 = "demo";

		String testPassword = "hayes";
		String testPassword2 = "123";

		String errorMessage = null;

		if((model.getUsername().equals(testUsername)&&(model.getPassword().equals(testPassword)))||(model.getUsername().equals(testUsername2)&&model.getPassword().equals(testPassword2))) {
			resp.sendRedirect("http://localhost:8081/lab02/account");
		}
		else {
			req.setAttribute("username",model.getUsername());
			errorMessage = "Invalid credentials.";
		}



		req.setAttribute("login",model);
		req.setAttribute("errorMessage", errorMessage);





		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);

	}
}