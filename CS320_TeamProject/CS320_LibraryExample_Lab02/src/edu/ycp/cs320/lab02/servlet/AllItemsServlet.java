package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Item;
import edu.ycp.cs320.booksdb.model.Pair;
import edu.ycp.cs320.lab02.controller.AllBooksController;
import edu.ycp.cs320.lab02.controller.AllItemsController;

public class AllItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AllItemsController controller = null;	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nAllItemsServlet: doGet");

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

		req.getRequestDispatcher("/_view/allItems.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("\nAllItemsServlet: doPost");		
		
		String errorMessage = null;
		ArrayList<Item> items = null;
		
		controller = new AllItemsController();

		// get list of author,book pairs returned from query
		items = controller.getAllItems();
	
		// any books found?
		if (items == null) {
			errorMessage = "No Items were found in the Library";
		}
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("items",  items);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/allItems.jsp").forward(req, resp);
	}	
}
