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

<<<<<<< HEAD
import models.LoginModel;


=======
>>>>>>> d9db90c0fc76b67f362e20026d2558043a68fa05
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Account Servlet: doGet");
		req.getRequestDispatcher("/view/create_account.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		System.out.println("Create Account Servlet: doPost");

        LoginModel model = new LoginModel();

        if(req.getParameter("accountPass") == req.getParameter("confirmPass"))
        {
            resp.sendRedirect("http://localhost:8081/lab02/login");
            model.setUsername(req.getParameter("accountName"));
		    model.setPassword(req.getParameter("password"));
        }
		else
        {
            String errorMessage = "Password and confirm password do not match.";
            req.setAttribute("errorMessage", errorMessage);
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


		System.out.println("Account Servlet: doGet");
		
		req.getRequestDispatcher("/view/create_account.jsp").forward(req, resp);
	}
}

