package com.LibraryServlet.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReturnBooks
 */
@WebServlet("/ReturnBooks")
public class ReturnBooks extends HttpServlet {
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

	public LocalDate date;
	private CallableStatement callableStatement;
	private Date return_date;
	private LocalDate issue_date;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnBooks() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		int bookId = Integer.parseInt(request.getParameter("id"));

		issue_date = LocalDate.now();

		ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.utilities.mysqlInfo");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);	

			statement = conn.createStatement();

			sql="update `admin_book_details` set `book_count`=`book_count`+1 where `book_id`=" + bookId + " ";
			statement.executeUpdate(sql);

			sql="delete from `user_book_details` where `book_id`= "+ bookId+" ";
			statement.executeUpdate(sql);

			callableStatement = conn.prepareCall("{call.dateFetch(?)}");
			callableStatement.registerOutParameter(1, Types.DATE);
			callableStatement.execute();
			return_date = callableStatement.getDate(1);

			sql="select * from `user_book_details`";
			resultSet = statement.executeQuery(sql);
			boolean next = resultSet.next();

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
					"}\r\n"
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
					"		<h3 style=\"margin-left: 44%;\">Return Area</h3>\r\n" + 
					"	</nav>\r\n" + 
					"\r\n" + 
					"	<br>\r\n" + 
					"	<br>"
					+ "<center style=\"color:green;font-size:20px\">Return Successful! </center><br>"
					+ "   <table> \r\n" + 
					"					 <tr>\r\n" + 
					"						<th>Book ID</th>\r\n" + 
					"						<th>Book Name</th>\r\n" + 
					"						<th>Category</th>\r\n" + 
					"						<th>Author</th>\r\n" + 
					"						<th>Rating</th>\r\n" + 
					"						<th>Price</th>\r\n" + 
					"						<th>Issue Date</th>\r\n" + 
					"						<th>Return Date</th>\r\n" + 
					"						<th>Return Book</th>\r\n" + 
					"						\r\n" + 
					"					</tr>");

			while(next==false) {
				writer.println("</table> <br><br><br>"
						+ "<center>"
						+ "Oops....Nothing to return!...Try adding some books to Shelf....!!!"
						+ "<br><br><br>"
						+ "<form action=\"AddBooksServlet\" >\r\n" + 
						"                        <button name=\"type\" value=\"user\" style=\" border: none; margin-left: 15px;\" title=\"click\"><img src=\"add.png\" alt=\"\"\r\n" + 
						"                                style=\"height: 50px;\"></button>\r\n" + 
						"                    </form>"
						+ "</center>");
				break;
			}

			while(next==true) {

				book_id=resultSet.getInt(1);
				book_name = resultSet.getString(2);
				book_category = resultSet.getString(3);
				book_author = resultSet.getString(4);
				book_price = resultSet.getDouble(5);
				book_rating = resultSet.getDouble(6);

				writer.println( "<tr>\r\n" + 
						"                <td>"+ book_id+"</td>\r\n" + 
						"                <td>"+ book_name+"</td>\r\n" + 
						"                <td>"+ book_category+"</td>\r\n" + 
						"                <td>"+ book_author+"</td>\r\n" + 
						"                <td>"+ book_rating+"</td>\r\n" + 
						"                <td>"+ book_price+"</td>\r\n"+
						"                <td>"+ issue_date+"</td>\r\n"+
						"                <td>"+ return_date+"</td>\r\n"+
						"<td><form action=\"ReturnBooks\" >\r\n" + 
						"			<button name=\"id\" value=" + book_id + " style=\" border: none; margin-left: 15px;\" title=\"click\"><img src=\"return-book.png\" alt=\"\"\r\n" + 
						"	                style=\"height: 50px;\"></button>\r\n" + 
						"		  </form>" + 
						"            </td>    \r\n" + 
						"            </tr>");

				if(resultSet.next()==false) {
					break;
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
