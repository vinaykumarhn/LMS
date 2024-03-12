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
@WebServlet("/addbook")
public class AddBooks extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	resp.setContentType("text/html");
    	PrintWriter out=resp.getWriter();
    	
       String BOOK_ID=req.getParameter("book_id");
  	   String CATEGORY=req.getParameter("category");
  	   String NAME=req.getParameter("name");
  	   String AUTHOR=req.getParameter("author");
  	   String COPIES=req.getParameter("copies");
    	
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	String url="jdbc:mysql://localhost:3306/library";
    	String user="root";
    	String pass="tiger";
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			
			String query1="INSERT INTO BOOKS(BOOK_ID, CATEGORY, NAME, AUTHOR, COPIES) VALUES(?,?,?,?,?)";
			ps=con.prepareStatement(query1);
			ps.setString(1, BOOK_ID);
			ps.setString(2, CATEGORY);
			ps.setString(3, NAME);
			ps.setString(4, AUTHOR);
			ps.setString(5, COPIES);
			
			String buttonClicked = req.getParameter("button");
			if ("addbook".equals(buttonClicked)) {
			 
				
				ps.executeUpdate();
	             out.println("<p>StaffID: " + BOOK_ID + "</p>");
	             out.println("<p>Name: " + CATEGORY + "</p>");
	             out.println("<p>Contact: " + NAME + "</p>");
	             out.println("<p>Contact: " + AUTHOR + "</p>");
	             out.println("<p>Contact: " + COPIES + "</p>");
	             resp.sendRedirect("booksavailable.html");
	             
	         
			}
			else
			{
				out.println("Staff not added");
			}
		} catch (ClassNotFoundException | SQLException e) {
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
