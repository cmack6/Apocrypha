package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.controller.LoginController;
import edu.ycp.cs320.lab02.model.Library;

public class FileSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Library model;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nFileSelectServlet: doGet");

		req.getRequestDispatcher("/_view/file-select.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nFileSelectServlet: doPost");

		

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/file-select.jsp").forward(req, resp);
	}
}
