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
@WebServlet("/removestaff")
public class RemoveStaff extends HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	resp.setContentType("text/html");
    	PrintWriter out=resp.getWriter();
        String STAFF_ID=req.getParameter("staff_id");
  	   
    	
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	String url="jdbc:mysql://localhost:3306/library";
    	String user="root";
    	String pass="tiger";
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			
			String query1="DELETE FROM STAFFS WHERE STAFF_ID=?";
			ps=con.prepareStatement(query1);
			ps.setString(1, STAFF_ID);
			
			String buttonClicked = req.getParameter("button");
			if ("removestaff".equals(buttonClicked)) {
			
			int rowsAffected=ps.executeUpdate();
			
			 if (rowsAffected > 0) {
				 JOptionPane.showMessageDialog(null, "Staff Details Deleted Successfully"+ STAFF_ID );
				 resp.sendRedirect("staffdetails.html");
	                
	            } else {
	            	JOptionPane.showMessageDialog(null, "Error: Staff ID not found" );
	                resp.sendRedirect("removestaff.html");
	            }
	             
	             
	         
			}
			else
			{
				out.println("Staff not Removed");
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
