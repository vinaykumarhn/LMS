package com.lms.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/removebook")
public class RemoveBook extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	resp.setContentType("text/html");
    	PrintWriter out=resp.getWriter();
        String BOOK_ID=req.getParameter("book_id");
  	   
    	
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	String url="jdbc:mysql://localhost:3306/library";
    	String user="root";
    	String pass="tiger";
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			
			String query1="DELETE FROM BOOKS WHERE BOOK_ID=?";
			ps=con.prepareStatement(query1);
			ps.setString(1, BOOK_ID);
			
			String buttonClicked = req.getParameter("button");
			if ("removebook".equals(buttonClicked)) {
			
			int rowsAffected=ps.executeUpdate();
			
			 if (rowsAffected > 0) {
				 JOptionPane.showMessageDialog(null, "Books Data Deleted Successfully"+ BOOK_ID );
				 resp.sendRedirect("booksavailable.html");
	                
	            } else {
	            	JOptionPane.showMessageDialog(null, "Error: Book ID not found" );
	                resp.sendRedirect("removebook.html");
	            }
	             
	             
	         
			}
			else
			{
				out.println("Book not Removed");
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
