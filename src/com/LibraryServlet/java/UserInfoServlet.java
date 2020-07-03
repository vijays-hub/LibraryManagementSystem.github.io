package com.LibraryServlet.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public String username;
	public String userpsw;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		int id = Integer.parseInt(request.getParameter("submit"));

		ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.utilities.mysqlInfo");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			String sql="select `user_email`, `user_password` from `user_details` where user_ID=?";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);

			prepareStatement.setInt(1, id);

			ResultSet resultSet = prepareStatement.executeQuery();

			boolean next = resultSet.next();


			if(next==false) {
				while(next==false) {
					writer.println("<!DOCTYPE html>\r\n" + 
							"<html lang=\"en\">\r\n" + 
							"\r\n" + 
							"<head>\r\n" + 
							"    <meta charset=\"UTF-8\">\r\n" + 
							"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
							"    <title>404Found! | User</title>\r\n" + 
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
							"       \r\n" + 
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
							"\r\n" + 
							"        .adminButton1 {\r\n" + 
							"            margin-top: 30px;\r\n" + 
							"            width: 280px;\r\n" + 
							"            border: none;\r\n" + 
							"            font-size: 32px;\r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"         /* The Modal (background) */\r\n" + 
							"        .modal {\r\n" + 
							"            position: fixed;\r\n" + 
							"            /* Stay in place */\r\n" + 
							"            z-index: 1;\r\n" + 
							"            /* Sit on top */\r\n" + 
							"            padding-top: 500px;\r\n" + 
							"            /* Location of the box */\r\n" + 
							"            left: 0;\r\n" + 
							"            top: 0;\r\n" + 
							"            width: 100%;\r\n" + 
							"            /* Full width */\r\n" + 
							"            height: 100%;\r\n" + 
							"            /* Full height */\r\n" + 
							"            overflow: auto;\r\n" + 
							"            /* Enable scroll if needed */\r\n" + 
							"            background-color: rgb(0, 0, 0);\r\n" + 
							"            /* Fallback color */\r\n" + 
							"            background-color: rgba(0, 0, 0, 0.4);\r\n" + 
							"            /* Black w/ opacity */\r\n" + 
							"           \r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"        /* Modal Content */\r\n" + 
							"        .modal-content {\r\n" + 
							"            background-color:snow;\r\n" + 
							"            margin: auto;\r\n" + 
							"            padding: 20px;\r\n" + 
							"            border: 1px solid #888;\r\n" + 
							"            width: 80%;\r\n" + 
							"            text-align: center;\r\n" + 
							"            box-shadow: 20px 20px 50px 50px burlywood;\r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"        /* The Close Button */\r\n" + 
							"        .close {\r\n" + 
							"            color: #aaaaaa;\r\n" + 
							"            float: right;\r\n" + 
							"            font-size: 28px;\r\n" + 
							"            font-weight: bold;\r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"        .close:hover,\r\n" + 
							"        .close:focus {\r\n" + 
							"            color: #000;\r\n" + 
							"            text-decoration: none;\r\n" + 
							"            cursor: pointer;\r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"        #back {\r\n" + 
							"            background-color: #4CAF50;\r\n" + 
							"            color: white;\r\n" + 
							"            padding: 14px 20px;\r\n" + 
							"            margin: 8px 0;\r\n" + 
							"            border: none;\r\n" + 
							"            cursor: pointer;\r\n" + 
							"            width: 50%;\r\n" + 
							"            font-size: 20px;\r\n" + 
							"            font-family: Raleway;\r\n" + 
							"            \r\n" + 
							"        }\r\n" + 
							"\r\n" + 
							"        button:hover{\r\n" + 
							"           text-decoration: underline;\r\n" + 
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
							"                <span style=\"color: red; text-decoration: overline black;\">404</span><span style=\"text-decoration: underline red;\">Found!</span>\r\n" + 
							"            </h1>\r\n" + 
							"        </div>\r\n" + 
							"    </div>\r\n" + 
							"    <br><br>\r\n" + 
							"    <nav class=\"navbar navbar-expand-sm navbar-primary sticky-top\"\r\n" + 
							"        style=\"height: auto; background-color: teal; border-bottom: 1px solid black; color:white;\" id=\"nav\">\r\n" + 
							"        <h3 style=\"margin-left: 44%;\">User Zone</h3>\r\n" + 
							"    </nav>\r\n" + 
							"\r\n" + 
							"    <br><br><br>\r\n" + 
							"      <!-- The Modal -->\r\n" + 
							"      <!-- <div id=\"myModal\" class=\"modal\"> -->\r\n" + 
							"\r\n" + 
							"        <!-- Modal content -->\r\n" + 
							"        <div class=\"modal-content\">\r\n" + 
							"\r\n"
							+ "<center style="
							+ "color:red> Invalid Unique ID! Try Again! </center>" + 
							"           <form action=\"UserInfoServlet\">\r\n" + 
							"            <input type=\"number\"name=\"submit\" placeholder=\"Please Mention Your Unique ID Again!\" style=\"background-color: wheat; margin-left: 0%;text-align: center;\">\r\n" + 
							"            <br> <br>\r\n" + 
							"            <input type=\"submit\" style=\"margin-left: 0%;background-color: wheat\">\r\n" + 
							"           </form>\r\n" + 
							"            <br><br><br>\r\n" +  
							"        \r\n" + 
							"            <form action=\"user.html\">\r\n" + 
							"                <button style=\"border-radius: 0%; width: 100px; background-color: #4CAF50; color: white;\"> Back </button>\r\n" + 
							"            </form>\r\n" + 
							"            \r\n" + 
							"        </div>\r\n" + 
							"\r\n" + 
							"    </div>\r\n" + 
							"    \r\n" + 
							"</body>\r\n" + 
							"</html>");
					break;
				}
			}
			else {
				
				while(next==true) {
					username = resultSet.getString(1);
					userpsw = resultSet.getString(2);
					
					response.setContentType("text/html");
					if(resultSet.next()==false) {
						break;
					}
				}
				writer.println("<!DOCTYPE html>\r\n" + 
						"<html lang=\"en\">\r\n" + 
						"\r\n" + 
						"<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"    <title>404Found! | User</title>\r\n" + 
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
						"       \r\n" + 
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
						"\r\n" + 
						"        .adminButton1 {\r\n" + 
						"            margin-top: 30px;\r\n" + 
						"            width: 280px;\r\n" + 
						"            border: none;\r\n" + 
						"            font-size: 32px;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"         /* The Modal (background) */\r\n" + 
						"        .modal {\r\n" + 
						"            position: fixed;\r\n" + 
						"            /* Stay in place */\r\n" + 
						"            z-index: 1;\r\n" + 
						"            /* Sit on top */\r\n" + 
						"            padding-top: 500px;\r\n" + 
						"            /* Location of the box */\r\n" + 
						"            left: 0;\r\n" + 
						"            top: 0;\r\n" + 
						"            width: 100%;\r\n" + 
						"            /* Full width */\r\n" + 
						"            height: 100%;\r\n" + 
						"            /* Full height */\r\n" + 
						"            overflow: auto;\r\n" + 
						"            /* Enable scroll if needed */\r\n" + 
						"            background-color: rgb(0, 0, 0);\r\n" + 
						"            /* Fallback color */\r\n" + 
						"            background-color: rgba(0, 0, 0, 0.4);\r\n" + 
						"            /* Black w/ opacity */\r\n" + 
						"           \r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        /* Modal Content */\r\n" + 
						"        .modal-content {\r\n" + 
						"            background-color:snow;\r\n" + 
						"            margin: auto;\r\n" + 
						"            padding: 20px;\r\n" + 
						"            border: 1px solid #888;\r\n" + 
						"            width: 80%;\r\n" + 
						"            text-align: center;\r\n" + 
						"            box-shadow: 20px 20px 50px 50px burlywood;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        /* The Close Button */\r\n" + 
						"        .close {\r\n" + 
						"            color: #aaaaaa;\r\n" + 
						"            float: right;\r\n" + 
						"            font-size: 28px;\r\n" + 
						"            font-weight: bold;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        .close:hover,\r\n" + 
						"        .close:focus {\r\n" + 
						"            color: #000;\r\n" + 
						"            text-decoration: none;\r\n" + 
						"            cursor: pointer;\r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        #back {\r\n" + 
						"            background-color: #4CAF50;\r\n" + 
						"            color: white;\r\n" + 
						"            padding: 14px 20px;\r\n" + 
						"            margin: 8px 0;\r\n" + 
						"            border: none;\r\n" + 
						"            cursor: pointer;\r\n" + 
						"            width: 50%;\r\n" + 
						"            font-size: 20px;\r\n" + 
						"            font-family: Raleway;\r\n" + 
						"            \r\n" + 
						"        }\r\n" + 
						"\r\n" + 
						"        button:hover{\r\n" + 
						"           text-decoration: underline;\r\n" + 
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
						"                <span style=\"color: red; text-decoration: overline black;\">404</span><span style=\"text-decoration: underline red;\">Found!</span>\r\n" + 
						"            </h1>\r\n" + 
						"        </div>\r\n" + 
						"    </div>\r\n" + 
						"    <br><br>\r\n" + 
						"    <nav class=\"navbar navbar-expand-sm navbar-primary sticky-top\"\r\n" + 
						"        style=\"height: auto; background-color: teal; border-bottom: 1px solid black; color:white;\" id=\"nav\">\r\n" + 
						"        <h3 style=\"margin-left: 44%;\">User Zone</h3>\r\n" + 
						"    </nav>\r\n" + 
						"\r\n" + 
						"    <br><br><br>\r\n" + 
						"      <!-- The Modal -->\r\n" + 
						"      <!-- <div id=\"myModal\" class=\"modal\"> -->\r\n" + 
						"\r\n" + 
						"        <!-- Modal content -->\r\n" + 
						"        <div class=\"modal-content\">\r\n" + 
						"\r\n" + 
						"            <br><br>\r\n" + 
						"            <p>Unique ID : "+ id+"</p>\r\n" + 
						"            <p>Email : "+ username+"</p>\r\n" + 
						"            <p>Password : "+ userpsw+"</p>\r\n" + 
						"        \r\n" + 
						"            <form action=\"user.html\">\r\n" + 
						"                <button style=\"border-radius: 0%; width: 100px; background-color: #4CAF50; color: white;\"> Back </button>\r\n" + 
						"            </form>\r\n" + 
						"            \r\n" + 
						"        </div>\r\n" + 
						"\r\n" + 
						"    </div>\r\n" + 
						"    \r\n" + 
						"</body>\r\n" + 
						"</html>");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
