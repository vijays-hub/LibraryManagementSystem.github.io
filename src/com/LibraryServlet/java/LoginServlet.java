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
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(
		urlPatterns = { "/LoginServlet" }, 
		initParams = { 
				@WebInitParam(name = "Admin Name", value = "vijayskumar82@gmail.com"), 
				@WebInitParam(name = "Admin Password", value = "vijay@2781"),
				@WebInitParam(name = "Admin ID", value ="10" )
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int count=3;
	private Connection conn;
	private String sql;
	private Statement statement;
	private ResultSet resultSet;
	private RequestDispatcher dispatcher;
	String username;
	String userpsw;
	private PreparedStatement prepareStatement;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.utilities.mysqlInfo");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Getting Admin Details!
		ServletConfig config = getServletConfig();
		String adminUsername = config.getInitParameter("Admin Name");
		String adminPassword = config.getInitParameter("Admin Password");
		String adminID = config.getInitParameter("Admin ID");

		response.setContentType("text/html");

		String type = request.getParameter("type");

		if(type.equalsIgnoreCase("Admin")) {
			String adName = request.getParameter("uname");
			String adID = request.getParameter("uid");
			String adPass = request.getParameter("psw");

			if(adminUsername.equals(adName) && adminPassword.equals(adPass) && adID.equals(adminID)) 
			{
				dispatcher = request.getRequestDispatcher("admin.html");
				dispatcher.include(request, response);
			}
			else
			{
				if(count==1)
				{
					dispatcher = request.getRequestDispatcher("block.html");
					dispatcher.include(request, response);
				}
				else {
					writer.println("  <center "+" style=" +"color:red;"+" >"+ "Invalid Admin Username or Password...Try Again </center>");
					--count;
					writer.println("  <center "+" style=" +"color:red;"+" >"+ "Attempts Left :"+count +"</center>");
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
					dispatcher.include(request, response);
				}
			}
		}
		else if(type.equalsIgnoreCase("User")) 
		{
			String Name = request.getParameter("uname");
			int id = Integer.parseInt(request.getParameter("uid"));
			String Pass = request.getParameter("psw");
			
			try {
				sql="select `user_email`, `user_password` from `user_details` where user_ID=?";
				prepareStatement = conn.prepareStatement(sql);
				
				prepareStatement.setInt(1, id);
				
				 resultSet = prepareStatement.executeQuery();
				
				while(resultSet.next()==true) {
					username = resultSet.getString(1);
					userpsw = resultSet.getString(2);
				}
				
				if(username.equals(Name) && userpsw.equals(Pass)) {
					dispatcher = request.getRequestDispatcher("user.html");
					dispatcher.include(request, response);
				}
				else
				{
					if(count==1)
					{
						dispatcher = request.getRequestDispatcher("block.html");
						dispatcher.include(request, response);
					}
					else {
						writer.println("  <center "+" style=" +"color:red;"+" >"+ "Invalid Username or Password...Try Again </center>");
						--count;
						writer.println("  <center "+" style=" +"color:red;"+" >"+ "Attempts Left :"+count +"</center>");
					
						RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
						dispatcher.include(request, response);
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(NullPointerException ne) {
				writer.println("  <center "+" style=" +"color:red;"+" >"+ "Data Doesn't Exists...Try Again </center>");
				--count;
				writer.println("  <center "+" style=" +"color:red;"+" >"+ "Attempts Left :"+count +"</center>");
			
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
				dispatcher.include(request, response);
			}
			
			
			
			
			
			
			
//			if(adminUsername.equals(Name)) {
//				writer.println("Next Servlet Loading");
//			}
//			else
//			{
//				if(count==1)
//				{
//					RequestDispatcher dispatcher = request.getRequestDispatcher("block.html");
//					dispatcher.include(request, response);
//				}
//				else {
//					writer.println("  <center "+" style=" +"color:red;"+" >"+ "Invalid Admin Username or Password...Try Again </center>");
//					--count;
//					writer.println("  <center "+" style=" +"color:red;"+" >"+ "Attempts Left :"+count +"</center>");
//					RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
//					dispatcher.include(request, response);
//				}
//			}
		}


	}

}
