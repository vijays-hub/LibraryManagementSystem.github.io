package com.LibraryServlet.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAddBooks
 */
@WebServlet("/UserAddBooks")
public class UserAddBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private String sql;
	private Statement statement;
	private ResultSet resultSet;

	private int book_id;
	private String book_name;
	private String book_category;
	private String book_author;
	private double book_price;
	private double book_rating;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAddBooks() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.utilities.mysqlInfo");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);		
			statement = conn.createStatement();
			sql="select * from `book_details`";
			resultSet = statement.executeQuery(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		



		writer.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <title>404Found! | All Books in Library</title>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\r\n" + 
				"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
				"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
				"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>\r\n" + 
				"    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n" + 
				"\r\n" + 
				"    <style>\r\n" + 
				"        @font-face {\r\n" + 
				"            font-family: Raleway;\r\n" + 
				"            src: url(Raleway-SemiBold.ttf);\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        body {\r\n" + 
				"            font-family: Raleway;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        nav {\r\n" + 
				"            box-shadow: 20px 20px 50px 10px #719ECE;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        table {\r\n" + 
				"            border-collapse: collapse;\r\n" + 
				"            border-spacing: 0;\r\n" + 
				"            width: 100%;\r\n" + 
				"            border: 1px solid #ddd;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        th,\r\n" + 
				"        td {\r\n" + 
				"            text-align: left;\r\n" + 
				"            padding: 16px;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        tr:nth-child(even) {\r\n" + 
				"            background-color: #f2f2f2;\r\n" + 
				"        }\r\n" + 
				"    </style>\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"    <div class=\"container\" style=\"width: 1200px;  box-shadow: 20px 20px 50px  darkgray;\">\r\n" + 
				"        <div class=\"page-header\">\r\n" + 
				"            <h1\r\n" + 
				"                style=\"text-align: center;background-color:whitesmoke; height: 80px; padding: 15px; margin-bottom: -10px;\">\r\n" + 
				"                <span style=\"color: red;\">404</span><span>Found!</span></h1>\r\n" + 
				"        </div>\r\n" + 
				"    </div>\r\n" + 
				"    <br><br>\r\n" + 
				"    <nav class=\"navbar navbar-expand-sm navbar-primary sticky-top\"\r\n" + 
				"        style=\"height: auto; background-color: teal; border-bottom: 1px solid black; color:white;\" id=\"nav\">\r\n" + 
				"        <h3 style=\"margin-left: 40%;\">All Books in Library</h3>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"    </nav>\r\n" + 
				"    <br><br>\r\n" + 
				"    <div class=\"container\">\r\n" + 
				"        <table>\r\n" + 
				"            <tr>\r\n" + 
				"                <th>Book ID</th>\r\n" + 
				"                <th>Book Name</th>\r\n" + 
				"                <th>Category</th>\r\n" + 
				"                <th>Author</th>\r\n" + 
				"                <th>Rating</th>\r\n" + 
				"                <th>Price</th>\r\n" + 
				"                <th>Count</th>\r\n" + 
				"                <th>Add to Shelf</th>\r\n" + 
				"\r\n" + 
				"            </tr>");


		try {
			while(resultSet.next()==true) {
				book_id=(resultSet.getInt(1));
				book_name = resultSet.getString(2);
				book_category = resultSet.getString(3);
				book_author = resultSet.getString(4);
				book_price = resultSet.getDouble(5);
				book_rating = resultSet.getDouble(6);

				writer.println("<tr>\r\n" + 
						"                <td>"+ book_id+"</td>\r\n" + 
						"                <td>"+ book_name+"</td>\r\n" + 
						"                <td>"+ book_category+"</td>\r\n" + 
						"                <td>"+ book_author+"</td>\r\n" + 
						"                <td>"+ book_rating+"</td>\r\n" + 
						"                <td>"+ book_price+"</td>\r\n" + 
						"                <td><button style=\" border: none; margin-left: 15px;\" title=\"click\" ><img src=\"add.png\" alt=\"\" style=\"height: 50px;\"></button></td>\r\n" + 
						"                \r\n" + 
						"            </tr>");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				close(conn,statement,resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void close(Connection conn2, Statement statement2, ResultSet resultSet2) throws SQLException {
		if(conn!=null) {
			conn.close();
		}
		if(statement!=null) {
			statement.close();
		}
		if(resultSet!=null) {
			resultSet.close();
		}
	}
	

}

