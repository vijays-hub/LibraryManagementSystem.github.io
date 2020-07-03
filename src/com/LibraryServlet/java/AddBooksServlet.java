package com.LibraryServlet.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookDisplayServlet
 */
@WebServlet("/AddBooksServlet")
public class AddBooksServlet extends HttpServlet {
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
	private int book_count;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBooksServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		String type = request.getParameter("type");


		ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.utilities.mysqlInfo");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);		
			statement = conn.createStatement();
			sql="select * from `admin_book_details`";
			resultSet = statement.executeQuery(sql);

			if(type.equalsIgnoreCase("user")) {
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


				while(resultSet.next()==true) {
					book_id=(resultSet.getInt(1));
					book_name = resultSet.getString(2);
					book_category = resultSet.getString(3);
					book_author = resultSet.getString(4);
					book_price = resultSet.getDouble(5);
					book_rating = resultSet.getDouble(6);
					book_count=resultSet.getInt(7);

					writer.println("<tr>\r\n" + 
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
			}
			else if(type.equalsIgnoreCase("admin")){
				writer.println("<!DOCTYPE html>\r\n" + 
						"<html lang=\"en\">\r\n" + 
						"\r\n" + 
						"<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"    <title>404Found! | Admin</title>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\r\n" + 
						"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
						"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
						"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>\r\n" + 
						"    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n" + 
						"\r\n" + 
						"\r\n" + 
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
						"        .container {\r\n" + 
						"            text-align: center;\r\n" + 
						"            background-color: darkkhaki;\r\n" + 
						"            box-shadow: 20px 20px 50px 5px #719ECE;\r\n" + 
						"            border-bottom: 3%;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        nav {\r\n" + 
						"            box-shadow: 20px 20px 50px 10px #719ECE;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"        button {\r\n" + 
						"            height: 50px;\r\n" + 
						"            background-color: white;\r\n" + 
						"            border-radius: 30%;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        select {\r\n" + 
						"            border: none;\r\n" + 
						"            height: 50px;\r\n" + 
						"            width: 180px;\r\n" + 
						"            border-radius: 5%;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"        select:focus {\r\n" + 
						"            outline: none !important;\r\n" + 
						"            outline-style: groove;\r\n" + 
						"            border: 1px solid slategray;\r\n" + 
						"\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        input {\r\n" + 
						"            border: none;\r\n" + 
						"            height: 50px;\r\n" + 
						"            width: 400px;\r\n" + 
						"            margin-left: -1px;\r\n" + 
						"            border-radius: 3%;\r\n" + 
						"            padding: 10px;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        input:focus {\r\n" + 
						"            outline: none !important;\r\n" + 
						"            outline-style: groove;\r\n" + 
						"            border: 1px solid slategray;\r\n" + 
						"\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        input[type=image] {\r\n" + 
						"            height: 80px;\r\n" + 
						"            width: 80px;\r\n" + 
						"            margin-bottom: -19.5px;\r\n" + 
						"        }\r\n" + 
						"\r\n"
						+ " button img {\r\n" + 
						"            height: 60px;\r\n" + 
						"            width: 60px;\r\n" + 
						"            margin-top: -5px; opacity: 0.5;\r\n" + 
						"        }\r\n"
						+ "img:hover{\r\n" + 
						"            opacity: 1;\r\n" + 
						"        }" + 
						"        button{\r\n" + 
						"            background: none;\r\n" + 
						"            border: none;\r\n" + 
						"            height: 80;\r\n" + 
						"            width: 52;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        button:focus{\r\n" + 
						"            outline: none !important;\r\n" + 
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
						"        <ul class=\"navbar-nav\" id=\"ul\">\r\n" + 
						"            <li class=\"nav-item bg-light\" style=\" border-radius: 5%;\">\r\n" + 
						"                <a class=\"nav-link bg-danger\" href=\"admin.html\" style=\"color: black;\">Go Back</a>\r\n" + 
						"            </li>\r\n" + 
						"        </ul>\r\n" + 
						"        <h3 style=\"margin-left: 38%;\">Admin Books Attach</h3>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"    </nav>\r\n" + 
						"\r\n" + 
						"    <br>\r\n" + 
						"    <div class=\"container\">\r\n" + 
						"        <div class=\"row\">\r\n" + 
						"            <div class=\"col-sm-4\" style=\"text-align: center; padding-top: 7%;padding-left: 5%;\">\r\n" + 
						"                <h3 style=\"margin-left: 80px;\">Add Books</h3>\r\n" + 
						"                <img src=\"books.jpg\" alt=\"\" style=\"height: 250px;\">\r\n" + 
						"            </div>\r\n" + 
						"            <div class=\"col-sm-8\">\r\n" + 
						"                <br>\r\n" + 
						"                <form action=\"AdminBooksAdd\" method=\"post\">\r\n" + 
						"                    <input type=\"text\" placeholder=\"Insert the Book Name Here\" name=\"name\"> <br> <br>\r\n" + 
						"                    <input type=\"text\" placeholder=\"Mention the Category of the Book\" name=\"category\"> <br> <br>\r\n" + 
						"                    <input type=\"text\" placeholder=\"Mention the Author of the Book\" name=\"author\"> <br> <br>\r\n" + 
						"                    <input type=\"text\" placeholder=\"Mention the Rating of the Book\" name=\"rating\"> <br> <br>\r\n" + 
						"                    <input type=\"text\" placeholder=\"Mention the Price of the Book\" name=\"price\"> <br> <br>\r\n" + 
						"                    <input type=\"text\" placeholder=\"Provide the count available\" name=\"count\"> <br><br>\r\n"+
						"                    <button name=\"type\" value=\"admin\"><img src=\"add.png\" title=\"click to add this book\" ></button>" + 
						"                </form>\r\n" + 
						"                <br><br>\r\n" + 
						"            </div>\r\n" + 
						"        </div>\r\n" + 
						"    </div>\r\n" + 
						"</body>\r\n" + 
						"\r\n" + 
						"</html>");
				
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
