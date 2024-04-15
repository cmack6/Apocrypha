package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.lab02.controller.BooksController;

public class BooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BooksController controller = null;	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nBooksServlet: doGet");

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

		req.getRequestDispatcher("/_view/books.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("\nBooksServlet: doPost");		
		
		String errorMessage   = null;
		String firstName      = null;
		String lastName       = null;		
		Book   book			  = null;
		ArrayList<Book> books = null;
		
		// Decode form parameters and dispatch to controller
		firstName = req.getParameter("author_firstname");
		lastName  = req.getParameter("author_lastname");
		
		if (firstName == null     || lastName == null ||
			firstName.equals("")  || lastName.equals("")) {
			errorMessage = "Please specify the author's first and last names";
		} else {
			controller = new BooksController();

			// get list of books returned from query
			books = controller.getTitleByAuthor(lastName);
			
			// any books found?
			if (books == null) {
				errorMessage = "No Books found for Author: " + lastName;
			}
			else {
				book  = books.get(0);
			}
		}
		
		// Add parameters as request attributes
		req.setAttribute("author_firstname", firstName);
		req.setAttribute("author_lastname",  lastName);
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("book",   book);
		req.setAttribute("books",  books);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/books.jsp").forward(req, resp);
	}	
}
