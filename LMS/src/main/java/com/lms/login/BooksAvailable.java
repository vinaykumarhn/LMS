package com.lms.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/booksavailable")

public class BooksAvailable extends HttpServlet
{
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
	   resp.setContentType("text/html");
	   PrintWriter out=resp.getWriter();
	   
	   Connection con=null;
	   PreparedStatement ps=null;
	   ResultSet rs=null;
	   String url="jdbc:mysql://localhost:3306/library";
	   String user="root";
	   String pass="tiger";
	   
	  
	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		 con=DriverManager.getConnection(url, user, pass);
		 String query="SELECT * FROM BOOKS";
		 ps=con.prepareStatement(query);
		 rs=ps.executeQuery();
		 out.println("<!DOCTYPE html>");
         out.println("<html>");
         out.println("<head>");
         out.println("<title>BooksAvailable</title>");
         out.println("</head>");
         out.println("<body>");
         out.println("<h1>BooksAvailable</h1>");
         out.println("<table border='1'>");
         out.println("<tr><th>BOOK_ID</th><th>CATEGORY</th><th>NAME</th><th>AUTHOR</th><th>COPIES</th></tr>");
         while (rs.next()) {
             out.println("<tr>");
             out.println("<td>" + rs.getString("BOOK_ID") + "</td>");
             out.println("<td>" + rs.getString("CATEGORY") + "</td>");
             out.println("<td>" + rs.getString("NAME") + "</td>");
             out.println("<td>" + rs.getString("AUTHOR") + "</td>");
             out.println("<td>" + rs.getString("COPIES") + "</td>");
             out.println("</tr>");
             
         }
         out.println("</table>");
         out.println("<form action='dashboard.html'>");
         out.println("<input type='submit' value='Back to Dashboard'>");
         out.println("</form>");
         out.println("</body>");
         out.println("</html>");
		 
	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		out.println("Error: " + e.getMessage());
	}
	   finally {
		   try {
	            if (rs != null) rs.close();
	           if (ps != null) ps.close();
	           if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	   }
	   
   }
}
