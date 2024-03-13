package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.controller.NumbersController;
import edu.ycp.cs320.lab02.model.Numbers;

public class MultiplyNumbersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("MultiplyNumbers Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/multiplyNumbers.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("MultiplyNumbers Servlet: doPost");
		
		String stringFirst = req.getParameter("stringFirst");
		String stringSecond = req.getParameter("stringSecond");
		String stringThird = req.getParameter("stringThird");
		
		
		Numbers model = new Numbers(stringFirst, stringSecond, stringThird);
		
		NumbersController controller = new NumbersController();
		
		

		// holds the error message text, if there is any
		String errorMessage = null;

		// result of calculation goes here
		Double result = null;
		
		// decode POSTed form parameters and dispatch to controller
		try {
			

			// check for errors in the form data before using is in a calculation
			if (model.checkString(model.getStringFirst()) == null || model.checkString(model.getStringSecond()) == null || model.checkString(model.getStringThird()) == null) {
				errorMessage = "Please specify three numbers";
			}
			
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				
				controller.setModel(model);
				controller.setNumbersDouble(stringFirst, stringSecond, stringThird);
				result = controller.multiply(model.getFirst(), model.getSecond(), model.getThird());
				controller.setResult(result);
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
			
		}
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("numbers", model);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/multiplyNumbers.jsp").forward(req, resp);
	}
}