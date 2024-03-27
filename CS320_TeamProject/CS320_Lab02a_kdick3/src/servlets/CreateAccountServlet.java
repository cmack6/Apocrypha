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
		}
		
		
	}


		System.out.println("Account Servlet: doGet");
		
		req.getRequestDispatcher("/view/create_account.jsp").forward(req, resp);
	}
}

