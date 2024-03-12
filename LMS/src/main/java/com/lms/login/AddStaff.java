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
@WebServlet("/addstaff")

public class AddStaff extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	resp.setContentType("text/html");
    	PrintWriter out=resp.getWriter();
       String STAFF_ID=req.getParameter("staff_id");
  	   String NAME=req.getParameter("name");
  	 String CONTACT=req.getParameter("contact");
    	
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	String url="jdbc:mysql://localhost:3306/library";
    	String user="root";
    	String pass="tiger";
    	
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			
			String query1="INSERT INTO STAFFS(STAFF_ID, NAME, CONTACT) VALUES(?,?,?)";
			ps=con.prepareStatement(query1);
			ps.setString(1, STAFF_ID);
			ps.setString(2, NAME);
			ps.setString(3, CONTACT);
			
			String buttonClicked = req.getParameter("button");
			if ("addstaff".equals(buttonClicked)) {
			 
				
				ps.executeUpdate();
	             out.println("<p>StaffID: " + STAFF_ID + "</p>");
	             out.println("<p>Name: " + NAME + "</p>");
	             out.println("<p>Contact: " + CONTACT + "</p>");
	             resp.sendRedirect("staffdetails.html");
	             
	         
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
