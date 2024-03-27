package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import sqldemo.DBUtil;

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

		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			System.err.println("Could not load Derby JDBC driver");
			System.err.println(e.getMessage());
			System.exit(1);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		// connect to the database
		try {
			conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			conn.setAutoCommit(true);

			String u = req.getParameter("usr");
			String p = req.getParameter("pwd");
			// a canned query to find book information (including author name) from title
			stmt = conn.prepareStatement(
					"select authors.firstname, authors.lastname "
					+ "  from authors "
					+ "		where authors.firstname = ? "
					+ "        and authors.lastname = ? "
			);

			// substitute the title entered by the user for the placeholder in the query
			stmt.setString(1, u);
			stmt.setString(2, p);

			// execute the query
			resultSet = stmt.executeQuery();
		
			String errorMessage = null;

			LoginModel model = new LoginModel();
			
			req.setAttribute("login",model);
			req.setAttribute("errorMessage", errorMessage);
			

			if (resultSet.next())
			{
				resp.sendRedirect("http://localhost:8081/lab02/account");
			}
			else{
				req.setAttribute("username",model.getUsername());
				errorMessage = "Invalid credentials.";
			}
		
		
		
		
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(conn);
	}

}


