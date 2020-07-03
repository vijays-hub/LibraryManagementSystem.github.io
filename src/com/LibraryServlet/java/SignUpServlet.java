package com.LibraryServlet.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private String sql;
	private Statement statement;
	private ResultSet resultSet;
	private RequestDispatcher dispatcher;
	private PreparedStatement prepareStatement;
	private int ID;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");

		ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.utilities.mysqlInfo");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);		
			sql="insert into `user_details`( `user_email` , `user_password`) values(?,?)";
			prepareStatement = conn.prepareStatement(sql);

			String pass = request.getParameter("psw");
			String email = request.getParameter("email");
			
			
			prepareStatement.setString(1, email);
			prepareStatement.setString(2, pass);

			prepareStatement.executeUpdate();

			
			sql="select `user_ID` from `user_details` where `user_email`=?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, email);
			resultSet = prepareStatement.executeQuery();
			resultSet.next();
			ID= resultSet.getInt(1);
			
			writer.println("<center> Your Unique ID of the Library Card is: "+ID+" <center><br><br>");
			writer.println("<center>Your Email : "+email+" <center><br><br>");
			writer.println("<center> Please use this <strong style="+"color:red;"+ " >"+"UNIQUE ID</strong> while Logging In</center> <br><br>");
			
			 dispatcher = request.getRequestDispatcher("afterSignUp.html");
			 dispatcher.include(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

}
