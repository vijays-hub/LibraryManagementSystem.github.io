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
 * Servlet implementation class UserDisplayServlet
 */
@WebServlet("/UserDisplayServlet")
public class UserDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	private String sql;
	private Statement statement;
	private ResultSet resultSet;

	private int user_Id;
	private String user_Email;
	private String user_Password;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDisplayServlet() {
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
		
		ResourceBundle bundle = ResourceBundle.getBundle("com.jdbc.utilities.mysqlInfo");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);		
			statement = conn.createStatement();
			
			sql="select * from `user_details`";
			resultSet = statement.executeQuery(sql);
			boolean next = resultSet.next();
			 
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
					"            background-color: white;\r\n" + 
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
					"            border-radius: 5%; \r\n" + 
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
					"        }\r\n"
					+ " table {\r\n" + 
					"            border-collapse: collapse;\r\n" + 
					"            border-spacing: 0;\r\n" + 
					"            width: 100%;\r\n" + 
					"            border: 1px solid #ddd;\r\n" +  
					"           \r\n" + 
					"        }"
					+ " th,\r\n" + 
					"        td {\r\n" + 
					"            text-align: left;\r\n" + 
					"            padding: 16px;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        tr:nth-child(even) {\r\n" + 
					"            background-color: #f2f2f2;\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"        button{\r\n" + 
					"            background: none;\r\n" + 
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
					"        <h3 style=\"margin-left: 35%;\">Users Of Library</h3>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"    </nav>\r\n" + 
					"\r\n" + 
					"    <br><br>"
					+ "<div class=\"container\">"
					+ "  <table>\r\n" + 
					"            <tr>\r\n" + 
					"                <th>User ID</th>\r\n" + 
					"                <th>User Email</th>\r\n" + 
					"                <th>User Password</th>\r\n" + 
					"                <th>Detach User</th>\r\n" + 
					"\r\n" + 
					"            </tr>");
			
			while(next==false) {
				writer.println("<br><br>"
						+ "<center style=\"color:red\">"
						+ "	Currently NO USERS available to Detach!"
						+ "</center>");
				break;
			}
			
			while(next==true) {
				user_Id = resultSet.getInt(1);
				user_Email = resultSet.getString(2);
				user_Password = resultSet.getString(3);
				
				writer.println("<tr>\r\n" + 
						"                <td>"+ user_Id +"l</td>\r\n" + 
						"                <td> "+ user_Email +"  </td>\r\n" + 
						"                <td> "+ user_Password +"  </td>\r\n" + 
						"                <td>\r\n" + 
						"                    <form action=\"UserRemoveServlet\">\r\n" + 
						"                        <button style=\" border: none; margin-left: 15px;\" title=\"click\" name=\"id\" value="+ user_Id +"><img src=\"remove-user.png\" alt=\"\"\r\n" + 
						"                                style=\"height: 50px;\"></button>\r\n" + 
						"                    </form>\r\n" + 
						"                </td>\r\n" + 
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
		if(resultSet!=null) {
			resultSet.close();
		}
	
	}

}
