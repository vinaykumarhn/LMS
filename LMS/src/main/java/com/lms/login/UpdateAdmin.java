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
@WebServlet("/updateadmin")
public class UpdateAdmin extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		String user_id=req.getParameter("user_id");
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		String contact=req.getParameter("contact");
		
		 Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String url="jdbc:mysql://localhost:3306/library";
			String user="root";
			String pass="tiger";
			String query="update admin set name=?, password=?,contact=? where user_id=?";
			int res1=0;
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(url, user, pass);
				
				ps=con.prepareStatement(query);
				ps.setString(1, user_id);
				ps.setString(2, name);
				ps.setString(3, password);
				ps.setString(4, contact);
			    res1=ps.executeUpdate();
				
					if(res1>0) {
//						resp.getWriter().println("User details updated successfully!");
				JOptionPane.showMessageDialog(null, "Admin updated successfully!!!");
				req.getRequestDispatcher("editadmin.html").include(req, resp);
//					resp.sendRedirect("editadmin.html");
					
				}
					
					else {
//						resp.getWriter().println("Failed to update user details.");
						JOptionPane.showMessageDialog(null, "Admin not updated!!!");
						resp.sendRedirect("updateadmin.html");
		            }
						
					
				
					
				
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.getWriter().println("Error: " + e.getMessage());
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
