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
 * Servlet implementation class SearchByServlet
 */
@WebServlet("/SearchByServlet")
public class SearchByServlet extends HttpServlet {
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

	private PreparedStatement prepareStatement;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchByServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		String option = request.getParameter("searchOption");
		String key = request.getParameter("keyword");
		String type = request.getParameter("type");

		ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.utilities.mysqlInfo");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();

			if(type.equalsIgnoreCase("user")) {

				sql="select * from `admin_book_details` where "+option+" like ?  ";

				prepareStatement = conn.prepareStatement(sql);

				prepareStatement.setString(1, "%"+ key+"%");

				resultSet = prepareStatement.executeQuery();

				boolean next = resultSet.next();

				if(next==false) {
					while(next==false) {
						writer.println("<!DOCTYPE html>\r\n" + 
								"<html lang=\"en\">\r\n" + 
								"\r\n" + 
								"<head>\r\n" + 
								"<meta charset=\"UTF-8\">\r\n" + 
								"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
								"<title>404Found! | User</title>\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"<link rel=\"stylesheet\"\r\n" + 
								"	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\r\n" + 
								"<script\r\n" + 
								"	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
								"<script\r\n" + 
								"	src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
								"<script\r\n" + 
								"	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>\r\n" + 
								"<link rel=\"stylesheet\"\r\n" + 
								"	href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"<style>\r\n" + 
								"@font-face {\r\n" + 
								"	font-family: Raleway;\r\n" + 
								"	src: url(Raleway-SemiBold.ttf);\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								"body {\r\n" + 
								"	font-family: Raleway;\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								".container {\r\n" + 
								"	text-align: center;\r\n" + 
								"	background-color: darkkhaki;\r\n" + 
								"	box-shadow: 20px 20px 50px 5px #719ECE;\r\n" + 
								"	border-bottom: 3%;\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								"nav {\r\n" + 
								"	box-shadow: 20px 20px 50px 10px #719ECE;\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								"button {\r\n" + 
								"	height: 50px;\r\n" + 
								"	background-color: white;\r\n" + 
								"	border-radius: 30%;\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								"select {\r\n" + 
								"	border: none;\r\n" + 
								"	height: 50px;\r\n" + 
								"	width: 180px;\r\n" + 
								"	border-radius: 5%;\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								"select:focus {\r\n" + 
								"	outline: none !important;\r\n" + 
								"	outline-style: groove;\r\n" + 
								"	border: 1px solid slategray;\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								"input {\r\n" + 
								"	border: none;\r\n" + 
								"	height: 50px;\r\n" + 
								"	width: 300px;\r\n" + 
								"	margin-left: -1px;\r\n" + 
								"	border-radius: 3%;\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								"input:focus {\r\n" + 
								"	outline: none !important;\r\n" + 
								"	outline-style: groove;\r\n" + 
								"	border: 1px solid slategray;\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								"\r\n" + 
								".adminButton1 {\r\n" + 
								"	margin-top: 30px;\r\n" + 
								"	width: 280px;\r\n" + 
								"	border: none;\r\n" + 
								"	font-size: 32px;\r\n" + 
								"}\r\n" + 
								"</style>\r\n" + 
								"\r\n" + 
								"</head>\r\n" + 
								"\r\n" + 
								"<body>\r\n" + 
								"	<div class=\"container\"\r\n" + 
								"		style=\"width: 1200px; box-shadow: 20px 20px 50px darkgray;\">\r\n" + 
								"		<div class=\"page-header\">\r\n" + 
								"			<h1\r\n" + 
								"				style=\"text-align: center; background-color: whitesmoke; height: 80px; padding: 15px; margin-bottom: -10px;\">\r\n" + 
								"				<span style=\"color: red; text-decoration: overline black;\">404</span><span\r\n" + 
								"					style=\"text-decoration: underline red;\">Found!</span>\r\n" + 
								"			</h1>\r\n" + 
								"		</div>\r\n" + 
								"	</div>\r\n" + 
								"	<br>\r\n" + 
								"	<br>\r\n" + 
								"	<nav class=\"navbar navbar-expand-sm navbar-primary sticky-top\"\r\n" + 
								"		style=\"height: auto; background-color: teal; border-bottom: 1px solid black; color: white;\"\r\n" + 
								"		id=\"nav\">\r\n" + 
								"		<h3 style=\"margin-left: 44%;\">User Zone</h3>\r\n" + 
								"	</nav>\r\n" + 
								"\r\n" + 
								"	<br>\r\n" + 
								"	<br>\r\n" + 
								"	<div class=\"container\">\r\n" + 
								"		<br>\r\n" + 
								" <center style=\"color:red\"> Oopsie....Invalid Keyword! </center>" + 
								"		<h4>Browse through the Library</h4>\r\n" + 
								"		<br>\r\n" + 
								"		<div class=\"row\" style=\"height: 150px;\">\r\n" + 
								"			<div class=\"col-sm-12\">\r\n" + 
								"				<form action=\"BookDisplayServlet\" method=\"\">\r\n" + 
								"					<button title=\"Click to display books\" style=\"border-radius: 5%;\"\r\n" + 
								"						name=\"type\" value=\"user\">Books in your Shelf</button>\r\n" + 
								"				</form>\r\n" + 
								"				<br> [OR] <br>\r\n" + 
								"				<br>\r\n" + 
								"				<form action=\"SearchByServlet\">\r\n" + 
								"					<select name=\"searchOption\">\r\n" + 
								"						<option selected=\"true\" disabled=\"disabled\">Search By:</option>\r\n" + 
								"						<option value=\"book_name\">Book Name</option>\r\n" + 
								"						<option value=\"book_author\">Author</option>\r\n" + 
								"						<option value=\"book_rating\">Rating</option>\r\n" + 
								"						<option value=\"book_category\">Category</option>\r\n" + 
								"						<option value=\"book_price\">Price</option>\r\n" + 
								"						<option value=\"book_rating\">Rating</option>\r\n" + 
								"					</select> <input type=\"text\" placeholder=\"Enter your keyword\" name=\"keyword\">\r\n" + 
								"					<button name=\"type\" value=\"user\" type=\"submit\"\r\n" + 
								"						style=\"height: 50px; padding-bottom: 5px; border-radius: 10%;\">\r\n" + 
								"					Search\r\n" + 
								"					</button>\r\n" + 
								"\r\n" + 
								"				</form>\r\n" + 
								"			</div>\r\n" + 
								"		</div>\r\n" + 
								"	</div>\r\n" + 
								"	<br>\r\n" + 
								"	<br>\r\n" + 
								"\r\n" + 
								"	<div class=\"container\" style=\"background-color: white;\">\r\n" + 
								"		<div class=\"row\">\r\n" + 
								"			<div class=\"col-sm-6\">\r\n" + 
								"				<br>\r\n" + 
								"				<div class=\"card\">\r\n" + 
								"					<br>\r\n" + 
								"					<form action=\"AddBooksServlet\">\r\n" + 
								"						<img src=\"add_book.jpg\" alt=\"add_book img\"\r\n" + 
								"							style=\"width: 50%; height: 40%; margin-left: 15%;\"\r\n" + 
								"							title=\"Add Books\"> <br>\r\n" + 
								"						<button style=\"font-family: Raleway;\" class=\"adminButton1\"\r\n" + 
								"							title=\"click\" name=\"type\" value=\"user\">\r\n" + 
								"							Add to Shelf\r\n" + 
								"							</h2>\r\n" + 
								"					</form>\r\n" + 
								"					<br>\r\n" + 
								"					<br>\r\n" + 
								"				</div>\r\n" + 
								"			</div>\r\n" + 
								"			<div class=\"col-sm-6\">\r\n" + 
								"				<br>\r\n" + 
								"				<div class=\"card\">\r\n" + 
								"					<br>\r\n" + 
								"					<form action=\"\">\r\n" + 
								"						<img src=\"return-book.png\" alt=\"return-book img\"\r\n" + 
								"							style=\"width: 50%; height: 40%; margin-left: 5%;\"\r\n" + 
								"							title=\"Return Books\"> <br>\r\n" + 
								"						<button style=\"font-family: Raleway;\" class=\"adminButton1\"\r\n" + 
								"							title=\"click\" name=\"type\" value=\"user\">\r\n" + 
								"							Return Books\r\n" + 
								"							</h2>\r\n" + 
								"					</form>\r\n" + 
								"					<br>\r\n" + 
								"					<br>\r\n" + 
								"				</div>\r\n" + 
								"			</div>\r\n" + 
								"		</div>\r\n" + 
								"		<br>\r\n" + 
								"		<br>\r\n" + 
								"		<div class=\"row\">\r\n" + 
								"			<div class=\"col-sm-6\">\r\n" + 
								"				<br>\r\n" + 
								"				<div class=\"card\">\r\n" + 
								"					<br>\r\n" + 
								"					<form action=\"userInfo.html\">\r\n" + 
								"						<img src=\"manage_users.svg\" alt=\"Account Info img\"\r\n" + 
								"							style=\"width: 50%; height: 40%; margin-left: 5%;\"\r\n" + 
								"							title=\"Account Info\"> <br>\r\n" + 
								"						<button style=\"font-family: Raleway;\" class=\"adminButton1\"\r\n" + 
								"							title=\"click\" name=\"type\" value=\"user\">\r\n" + 
								"							Account Info\r\n" + 
								"							</h2>\r\n" + 
								"					</form>\r\n" + 
								"				</div>\r\n" + 
								"			</div>\r\n" + 
								"			<div class=\"col-sm-6\">\r\n" + 
								"				<br>\r\n" + 
								"				<div class=\"card\">\r\n" + 
								"					<br>\r\n" + 
								"					<form action=\"index.html\">\r\n" + 
								"						<img src=\"LogOut.svg\" alt=\"Log Out img\"\r\n" + 
								"							style=\"width: 50%; height: 40%; margin-left: 5%;\" title=\"Log Out\">\r\n" + 
								"						<br>\r\n" + 
								"						<button style=\"font-family: Raleway;\" class=\"adminButton1\"\r\n" + 
								"							title=\"click to log out\">\r\n" + 
								"							Log Out\r\n" + 
								"							</h2>\r\n" + 
								"					</form>\r\n" + 
								"				</div>\r\n" + 
								"			</div>\r\n" + 
								"		</div>\r\n" + 
								"		<br>\r\n" + 
								"		<br>\r\n" + 
								"	</div>\r\n" + 
								"	<br>\r\n" + 
								"	<br>\r\n" + 
								"</body>\r\n" + 
								"</html>");
						break;
					}
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
							"        <h3 style=\"margin-left: 37%;\">Search results from Library</h3>\r\n" + 
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
							"\r\n" + 
							"            </tr>");
					while(next==true) {

						book_id=resultSet.getInt(1);
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
								"                <td>"+ book_price+"</td>\r\n"+ 
								"            </tr>");

						if(resultSet.next()==false) {
							writer.println("</table>" + 
									"<br><center>You can Add these Books from `Add Book` Section! </center>");
							break;
						}

					}

				}
			}
			else {
				sql="select * from `admin_book_details` where "+option+" like ?  ";

				prepareStatement = conn.prepareStatement(sql);

				prepareStatement.setString(1, "%"+ key+"%");

				resultSet = prepareStatement.executeQuery();

				boolean next = resultSet.next();

				if(next==false) {
					while(next==false) {
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
								"        #adminButton {\r\n" + 
								"            margin-top: 30px;\r\n" + 
								"            width: 200px;\r\n" + 
								"            border: none;\r\n" + 
								"            font-size: 32px;\r\n" + 
								"        }\r\n" + 
								"\r\n" + 
								"        .adminButton1 {\r\n" + 
								"            margin-top: 30px;\r\n" + 
								"            width: 280px;\r\n" + 
								"            border: none;\r\n" + 
								"            font-size: 32px;\r\n" + 
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
								"            width: 300px;\r\n" + 
								"            margin-left: -1px;\r\n" + 
								"            border-radius: 3%;\r\n" + 
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
								"            height: 50px;\r\n" + 
								"            width: 50px;\r\n" + 
								"            margin-bottom: -19.5px;\r\n" + 
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
								"                <span style=\"color: red;text-decoration: overline black\">404</span><span style=\"text-decoration: underline red\">Found!</span></h1>\r\n" + 
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
								"        <h3 style=\"margin-left: 38%;\">Admin Portal</h3>\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"    </nav>\r\n" + 
								"\r\n" + 
								"    <br><br>\r\n" + 
								"    <div class=\"container\">\r\n" + 
								"        <br><br>"
								+ "<center style=\"color:red\"> Oopsie....Invalid Keyword! </center> " + 
								"        <h4>Browse through the Library</h4>\r\n" + 
								"        <br>\r\n" + 
								"        <div class=\"row\" style=\"height: 150px;\">\r\n" + 
								"            <div class=\"col-sm-12\">\r\n" + 
								"                <form action=\"BookDisplayServlet\" method=\"\">\r\n" + 
								"                    <button title=\"Click to display books\" name=\"type\" value=\"admin\">Display All Books</button>\r\n" + 
								"                </form>\r\n" + 
								"                <br>\r\n" + 
								"                [OR]\r\n" + 
								"                <br><br>\r\n" + 
								"                <form action=\"SearchByServlet\">\r\n" + 
								"					<select name=\"searchOption\">\r\n" + 
								"						<option selected=\"true\" disabled=\"disabled\">Search By:</option>\r\n" + 
								"						<option value=\"book_name\">Book Name</option>\r\n" + 
								"						<option value=\"book_author\">Author</option>\r\n" + 
								"						<option value=\"book_rating\">Rating</option>\r\n" + 
								"						<option value=\"book_category\">Category</option>\r\n" + 
								"						<option value=\"book_price\">Price</option>\r\n" + 
								"						<option value=\"book_rating\">Rating</option>\r\n" + 
								"					</select> <input type=\"text\" placeholder=\"Enter your keyword\" name=\"keyword\">\r\n" + 
								"					<button name=\"type\" value=\"admin\" type=\"submit\"\r\n" + 
								"						style=\"height: 50px; padding-bottom: 5px; border-radius: 10%;\">\r\n" + 
								"					Search\r\n" + 
								"					</button>\r\n" + 
								"				</form>\r\n" + 
								"            </div>\r\n" + 
								"        </div>\r\n" + 
								"    </div>\r\n" + 
								"    <br><br>\r\n" + 
								"\r\n" + 
								"    <div class=\"container\" style=\"background-color: white;\">\r\n" + 
								"        <div class=\"row\">\r\n" + 
								"            <div class=\"col-sm-6\">\r\n" + 
								"                <br>\r\n" + 
								"                <div class=\"card\">\r\n" + 
								"                    <form action=\"AddBooksServlet\">\r\n" + 
								"                        <img src=\"add_book.jpg\" alt=\"Add Book Img\" style=\"width:50%; height: 40%; margin-left: 15%;\"\r\n" + 
								"                            title=\"Add Books\"> <br>\r\n" + 
								"                        <button style=\"font-family: Raleway;\" id=\"adminButton\" title=\"click\" name=\"type\" value=\"admin\">Add Books</h2>\r\n" + 
								"                    </form>\r\n" + 
								"                    <br><br>\r\n" + 
								"\r\n" + 
								"                </div>\r\n" + 
								"            </div>\r\n" + 
								"            <div class=\"col-sm-6\">\r\n" + 
								"                <br>\r\n" + 
								"                <div class=\"card\">\r\n" + 
								"                    <form action=\"admin.html\">\r\n" + 
								"                        <img src=\"update_book.jpg\" alt=\"update_book img\"\r\n" + 
								"                            style=\"width:50%; height: 40%;\" title=\"Update Books\"> <br>\r\n" + 
								"                        <button style=\"font-family: Raleway;\" class=\"adminButton1\" title=\"click\" name=\"type\" value=\"admin\">Update Books</h2>\r\n" + 
								"                    </form>\r\n" + 
								"                    <br><br>\r\n" + 
								"                </div>\r\n" + 
								"            </div>\r\n" + 
								"        </div>\r\n" + 
								"        <br><br>\r\n" + 
								"        <div class=\"row\">\r\n" + 
								"            <div class=\"col-sm-6\">\r\n" + 
								"                <br>\r\n" + 
								"                <div class=\"card\">\r\n" + 
								"                    <form action=\"admin.html\">\r\n" + 
								"                        <img src=\"manage_users.svg\" alt=\"manage_users img\"\r\n" + 
								"                            style=\"width:50%; height: 40%;\" title=\"Manage Users\"> <br>\r\n" + 
								"                        <button style=\"font-family: Raleway;\" class=\"adminButton1\" title=\"click\" name=\"type\" value=\"admin\">Manage Users</h2>\r\n" + 
								"                    </form>\r\n" + 
								"                    <br><br>\r\n" + 
								"                </div>\r\n" + 
								"            </div>\r\n" + 
								"            <div class=\"col-sm-6\">\r\n" + 
								"                <br>\r\n" + 
								"                <div class=\"card\">\r\n" + 
								"                    <form action=\"admin.html\">\r\n" + 
								"                        <img src=\"delete_book.png\" alt=\"delete_book img\"\r\n" + 
								"                            style=\"width:50%; height: 40%;\" title=\"Delete Books\"> <br>\r\n" + 
								"                        <button style=\"font-family: Raleway;\" class=\"adminButton1\" title=\"click\" name=\"type\" value=\"admin\">Obliterate Books</h2>\r\n" + 
								"                    </form>\r\n" + 
								"                    <br><br>\r\n" + 
								"                </div>\r\n" + 
								"            </div>\r\n" + 
								"        </div>\r\n" + 
								"        <br><br>\r\n" + 
								"    </div>\r\n" + 
								"    <br><br>\r\n" + 
								"</body>\r\n" + 
								"\r\n" + 
								"</html>");
						break;
					}
				}
				else {
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
							"        #adminButton {\r\n" + 
							"            margin-top: 30px;\r\n" + 
							"            width: 200px;\r\n" + 
							"            border: none;\r\n" + 
							"            font-size: 32px;\r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"        .adminButton1 {\r\n" + 
							"            margin-top: 30px;\r\n" + 
							"            width: 280px;\r\n" + 
							"            border: none;\r\n" + 
							"            font-size: 32px;\r\n" + 
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
							"            width: 300px;\r\n" + 
							"            margin-left: -1px;\r\n" + 
							"            border-radius: 3%;\r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"        input:focus {\r\n" + 
							"            outline: none !important;\r\n" + 
							"            outline-style: groove;\r\n" + 
							"            border: 1px solid slategray;\r\n" + 
							"\r\n" + 
							"        }\r\n" + 
							"\r\n"
							+ "table {\r\n" + 
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
							"                <span style=\"color: red;text-decoration: overline black\">404</span><span style=\"text-decoration: underline red\">Found!</span></h1>\r\n" + 
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
							"        <h3 style=\"margin-left: 32%;\">Search Results from Library</h3>\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"    </nav>\r\n" + 
							"\r\n" + 
							"    <br><br>"
//							+ "   <div class=\"container\">\r\n" + 
							+"			 <table>\r\n" + 
							"				 <tr>\r\n" + 
							"					<th>Book ID</th> \r\n" + 
							"			        <th>Book Name</th>\r\n" + 
							"					<th>Category</th>\r\n" + 
							"					<th>Author</th>\r\n" + 
							"					<th>Rating</th>\r\n" + 
							"					 <th>Price</th>\r\n" + 
							"				</tr>");
					
					while(next==true) {

						book_id=resultSet.getInt(1);
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
								"                <td>"+ book_price+"</td>\r\n"+ 
								"            </tr>");

						if(resultSet.next()==false) {
							break;
						}
					}
				}
			}
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
		if(prepareStatement!=null) {
			prepareStatement.close();
		}
		if(resultSet!=null) {
			resultSet.close();
		}
	



	}

}
