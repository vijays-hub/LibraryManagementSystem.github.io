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
 * Servlet implementation class AdminBooksAdd
 */
@WebServlet("/AdminBooksAdd")
public class AdminBooksAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private String sql;
	private Statement statement;
	private PreparedStatement pstatement;
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
	public AdminBooksAdd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter writer = response.getWriter();

		String bookName = request.getParameter("name");
		String bookCategory = request.getParameter("category");
		String bookAuthor = request.getParameter("author");
		double bookRating = Double.parseDouble(request.getParameter("rating"));
		double bookPrice = Double.parseDouble(request.getParameter("price"));
		int bookCount = Integer.parseInt(request.getParameter("count"));

		ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.utilities.mysqlInfo");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);	

			sql="insert into `admin_book_details`(`book_name`,`book_category`,`book_author`,`book_price`,`book_rating`,`book_count`) values (?,?,?,?,?,?)";
			pstatement = conn.prepareStatement(sql);

			pstatement.setString(1, bookName);
			pstatement.setString(2, bookCategory);
			pstatement.setString(3, bookAuthor);
			pstatement.setDouble(4, bookPrice);
			pstatement.setDouble(5, bookRating);
			pstatement.setInt(6, bookCount);

			pstatement.executeUpdate();

			statement = conn.createStatement();
			sql="select * from `admin_book_details`";
			resultSet = statement.executeQuery(sql);

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
					"        }"
					+ " table {\r\n" + 
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
					"                <a class=\"nav-link bg-danger\" href=\"index.html\" style=\"color: black;\">Log Out</a>\r\n" + 
					"            </li>\r\n" + 
					"        </ul>\r\n" + 
					"        <h3 style=\"margin-left: 38%;\">Admin Books Attach</h3>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"    </nav>\r\n" + 
					"\r\n" + 
					"    <br>\r\n"
					+ "<center style=\"color:green\"> Book Attaching Successful!! </center><br>"+
					"<div class=\"container\"><br><center> Attach More Books! </center> <br>"
					+ " <form action=\"AddBooksServlet\" >\r\n" + 
					"                        <button name=\"type\" value=\"admin\" style=\" border: none;\" title=\"click\"><img src=\"add.png\" alt=\"\"\r\n" + 
					"                                style=\"height: 60px;\"></button>\r\n" + 
					"    </form> <br><br>"
					+ "[OR]"
					+ " <form action=\"BookDisplayServlet\" method=\"get\">\r\n"
					+ "<button name=\"type\" value=\"admin\" title=\"Click to display books\">Display All Books</button>" +  
					"                </form>"
					+ "</div> <br><br>");



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
