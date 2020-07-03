package com.LibraryServlet.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Google1
 */
@WebServlet("/Google1")
public class Google1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Google1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchValue = request.getParameter("googleValue");
		String parameter = request.getParameter("type");
		
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");

		if(Objects.isNull(parameter)) {
			writer.println(" <center> " + " You have Searched for: " + "  <input type=" +  "text"  +" id=" + "input" + " value =" +  searchValue +" > </center> " );
			RequestDispatcher dispatcher = request.getRequestDispatcher("googleSearch.html");
			dispatcher.include(request, response);
			
		}
		else  {
			response.sendRedirect("https://www.google.com/search?q="+parameter);
			}
		}

	}


