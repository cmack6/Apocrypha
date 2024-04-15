package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.controller.InsertBookController;

public class InsertBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InsertBookController controller = null;	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nInsertBookServlet: doGet");

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

		req.getRequestDispatcher("/_view/insertBook.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("\nInsertBookServlet: doPost");		
		
		String errorMessage   = null;
		String successMessage = null;
		String firstName      = null;
		String lastName       = null;
		String title		  = null;
		String isbn			  = null;
		String strPublished   = null;
		int    published	  = 0;
		
		// Decode form parameters and dispatch to controller
		firstName    = req.getParameter("author_firstname");
		lastName     = req.getParameter("author_lastname");
		title        = req.getParameter("book_title");
		isbn         = req.getParameter("book_isbn");
		strPublished = req.getParameter("book_published");
		
		if (firstName    == null || firstName.equals("") ||
			lastName     == null || lastName.equals("")  ||
			title        == null || title.equals("")     ||
			isbn         == null || isbn.equals("")	     ||
			strPublished == null) {
			
			errorMessage = "Please fill in all of the required fields";
		} else {
			controller = new InsertBookController();

			// convert published to integer now that it is valid
			published = Integer.parseInt(strPublished);
			
			// get list of books returned from query			
			if (controller.insertBookIntoLibrary(title, isbn, published, lastName, firstName)) {
				successMessage = title;
			}
			else {
				errorMessage = "Failed to insert Book: " + title;					
			}
		}
		
		// Add parameters as request attributes
		req.setAttribute("author_firstname", firstName);
		req.setAttribute("author_lastname",  lastName);
		req.setAttribute("book_title",       title);
		req.setAttribute("book_isbn",        isbn);
		req.setAttribute("book_published",   published);
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage",   errorMessage);
		req.setAttribute("successMessage", successMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/insertBook.jsp").forward(req, resp);
	}	
}
