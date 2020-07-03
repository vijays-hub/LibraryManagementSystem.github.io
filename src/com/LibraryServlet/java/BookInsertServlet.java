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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookInsertServlet
 */
@WebServlet("/BookInsertServlet")
public class BookInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private String sql;
	private Statement statement;
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;

	private int book_id;
	private String book_name;
	private String book_category;
	private String book_author;
	private double book_price;
	private double book_rating;
	private int book_count;
	private int issue_count;




	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		int id = Integer.parseInt(request.getParameter("id")); 

		ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.utilities.mysqlInfo");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();

			sql="select * from `user_book_details` where book_id="+id;
			resultSet = statement.executeQuery(sql);

			boolean next = resultSet.next();

			while(next==true) 
			{
				sql="select * from `admin_book_details`";
				resultSet = statement.executeQuery(sql);
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



				writer.println("<center style=\"color:red\" >Cant add same book again..Try adding a different One</center>");



				while(resultSet.next()==true) {
					book_id=(resultSet.getInt(1));
					book_name = resultSet.getString(2);
					book_category = resultSet.getString(3);
					book_author = resultSet.getString(4);
					book_price = resultSet.getDouble(5);
					book_rating = resultSet.getDouble(6);
					book_count=resultSet.getInt(7);

					writer.println( "<tr>\r\n" + 
							"                <td>"+ book_id+"</td>\r\n" + 
							"                <td>"+ book_name+"</td>\r\n" + 
							"                <td>"+ book_category+"</td>\r\n" + 
							"                <td>"+ book_author+"</td>\r\n" + 
							"                <td>"+ book_rating+"</td>\r\n" + 
							"                <td>"+ book_price+"</td>\r\n" + 
							"                <td>"+ book_count+"</td>\r\n"
							+ "<td><form action=\"BookInsertServlet\">\r\n" + 
							"                <button style=\" border: none; margin-left: 15px;\" title=\"click\" name=\"id\" value="+book_id+"><img src=\"add.png\" alt=\"\" style=\"height: 50px;\"></button> \r\n" + 
							"            </form></td>" + 
							"                \r\n" + 
							"            </tr>");
				}

				break;	
			}
			while(next==false) 
			{
				sql="select * from `admin_book_details` where `book_id`= "+id;
				resultSet = statement.executeQuery(sql);

				while(resultSet.next()) {
					book_id=resultSet.getInt(1);
					book_name = resultSet.getString(2);
					book_category = resultSet.getString(3);
					book_author = resultSet.getString(4);
					book_price = resultSet.getDouble(5);
					book_rating = resultSet.getDouble(6);
				}

				sql="select count(book_id) from user_book_details";
				resultSet = statement.executeQuery(sql);
				resultSet.next();
				issue_count = resultSet.getInt(1);
				
				
				sql="insert into `user_book_details` values(?,?,?,?,?,?)";
				prepareStatement = conn.prepareStatement(sql);

				prepareStatement.setInt(1, book_id);
				prepareStatement.setString(2, book_name);
				prepareStatement.setString(3, book_category);
				prepareStatement.setString(4, book_author);
				prepareStatement.setDouble(5, book_price);
				prepareStatement.setDouble(6, book_rating);

				if(issue_count<5) {
					prepareStatement.executeUpdate();
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
							"        }\r\n"
							+ " .container {\r\n" + 
							"            text-align: center;\r\n" + 
							"            background-color: darkkhaki;\r\n" + 
							"            box-shadow: 20px 20px 50px 5px #719ECE;\r\n" + 
							"            border-bottom: 3%;\r\n" + 
							"        }" + 
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
							"        <h3 style=\"margin-left: 40%;\">Status of Issue</h3>\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"    </nav>\r\n"
							+ "<br><br><br>\r\n" + 
							"    <div class=\"container\">\r\n" + 
							"        <div class=\"row\">\r\n" + 
							"            <div class=\"col-sm-12\">\r\n" + 
							"                <br>\r\n" + 
							"               <p style=\"color:green\">Book Added Successfully!</p>\r\n" + 
							"               <br>\r\n" + 
							"               <form action=\"BookDisplayServlet\">\r\n" + 
							"                <button title=\"Click to display books\" name=\"type\" value=\"user\" style=\"border-radius: 5%;\">See Books in your Shelf</button>\r\n" + 
							"            </form>\r\n" + 
							"            <br>\r\n" + 
							"            <form action=\"user.html\" method=\"\">\r\n" + 
							"                <button title=\"Click to go back\" style=\"border-radius: 5%;\">Go back</button>\r\n" + 
							"            </form>\r\n" + 
							"            </div>\r\n" + 
							"            \r\n" + 
							"        </div>\r\n" + 
							"        <br><br>\r\n" + 
							"    </div>");
					break;

				}
				else {
					
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
							"        }\r\n"
							+ " .container {\r\n" + 
							"            text-align: center;\r\n" + 
							"            background-color: darkkhaki;\r\n" + 
							"            box-shadow: 20px 20px 50px 5px #719ECE;\r\n" + 
							"            border-bottom: 3%;\r\n" + 
							"        }" + 
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
							"        <h3 style=\"margin-left: 40%;\">Status of Issue</h3>\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"    </nav>\r\n"
							+ "<br><br><br>\r\n" + 
							"    <div class=\"container\">\r\n" + 
							"        <div class=\"row\">\r\n" + 
							"            <div class=\"col-sm-12\">\r\n" + 
							"                <br>\r\n" + 
							"               <p style=\"color:red\">Oopsie! Cant Issue More than <b style=\"color:black\" >5</b> Books! Try Returning some Books!</p>\r\n" + 
							"               <br>\r\n" + 
							"               <form action=\"BookReturnServlet\">\r\n" + 
							"                <button title=\"Click to return books\" name=\"type\" value=\"user\" style=\"border-radius: 5%;\">Return Books</button>\r\n" + 
							"            </form>\r\n" + 
							"            <br>\r\n" + 
							"            <form action=\"user.html\" method=\"\">\r\n" + 
							"                <button title=\"Click to go back\" style=\"border-radius: 5%;\">Go back</button>\r\n" + 
							"            </form>\r\n" + 
							"            </div>\r\n" + 
							"            \r\n" + 
							"        </div>\r\n" + 
							"        <br><br>\r\n" + 
							"    </div>");
				}
				break;
			}


			//			
			//			
			//			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
