package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.lab02.controller.AuthorsController;

public class AuthorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AuthorsController controller = null;	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nAuthorServlet: doGet");

		String user = (String) req.getSession().getAttribute("user");
		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// now we have the user's User object,
		// proceed to handle request...
		
		System.out.println("   User: <" + user + "> logged in");

		req.getRequestDispatcher("/_view/authors.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("\nAuthorsServlet: doPost");		
		
		String errorMessage		  = null;
		String title			  = null;
		ArrayList<Author> authors = null;
		
		// Decode form parameters and dispatch to controller
		title = req.getParameter("book_title");
		
		if (title == null || title.equals("")) {
			errorMessage = "Please specify a title";
		} else {
			controller = new AuthorsController();

			// get list of authors returned from query
			authors = controller.getAuthorByTitle(title);
			
			// any authors found?
			if (authors == null) {
				errorMessage = "Title was not found in the Library: " + title;
			}
		}

		// Add parameters as request attributes
		req.setAttribute("book_title", title);
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("authors", authors);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/authors.jsp").forward(req, resp);
	}	
}
