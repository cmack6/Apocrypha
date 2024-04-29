package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import edu.ycp.cs320.sqldemo.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.booksdb.model.*;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;
import edu.ycp.cs320.lab02.model.LoginModel;


public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Account Servlet: doGet");
		req.getRequestDispatcher("/_view/create_account.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		List<User> userList = db.findAllUsers();
		
		System.out.println("Create Account Servlet: doPost");
		String username = req.getParameter("accountName");
		String password = req.getParameter("accountPass");
		String confirmPassword = req.getParameter("confirmPass");
        LoginModel model = new LoginModel();
        Boolean isUnused = true;
        for(User user: userList) {
        	if(user.getUsername().equals(username)) {
        		isUnused=false;
        	}
        }
        if(!isUnused) {
        	String errorMessage = "User already exists with that username";
        	req.setAttribute("errorMessage", errorMessage);
        	req.getRequestDispatcher("/_view/create_account.jsp").forward(req, resp);
        }
        else if(password.equals(confirmPassword))
        {
            User newUser = db.insertNewUser(username, password);
            req.getSession().setAttribute("userID", newUser.getUserID());
		    resp.sendRedirect("http://localhost:8081/lab02/file-select");
        }
		else
        {
            String errorMessage = "Password and confirm password do not match.";
            req.setAttribute("errorMessage", errorMessage);
    		req.getRequestDispatcher("/_view/create_account.jsp").forward(req, resp);
        }
	
        
		/*try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			System.err.println("Could not load Derby JDBC driver");
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		
            try {
                conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);

		try {
			
			String u = req.getParameter("accountName");
			String p = req.getParameter("accountPass");
			

			// a canned query to find book information (including author name) from title
			stmt = conn.prepareStatement(
					"insert into authors (firstname, lastname) "
					+ "  values (?, ?) "
			);

			// substitute the title entered by the user for the placeholder in the query
			stmt.setString(1, u);
			stmt.setString(2, p);

			// execute the query
			resultSet = stmt.executeQuery();
			
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(conn);

		}*/
	
	}
		
		
		
}





