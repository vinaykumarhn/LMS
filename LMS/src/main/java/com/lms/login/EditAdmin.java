package com.lms.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editadmin")

public class EditAdmin extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
        resp.setContentType("text/html");
		String username=req.getParameter("username");
//		req.getAttribute("user_id");
//		RequestDispatcher dispatcher = req.getRequestDispatcher("editadmin.html");
//        dispatcher.forward(req, resp);
        
        Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String url="jdbc:mysql://localhost:3306/library";
		String user="root";
		String pass="tiger";
		String query="select * from admin where user_id=?";
		PrintWriter out=resp.getWriter();
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			ps=con.prepareStatement(query);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next())
			{
//			String buttonClicked=req.getParameter("button");
//			if("editadmin".equals(buttonClicked)) {
				
				
			
			String username1=rs.getString(1);
			
				JOptionPane.showMessageDialog(null, "Do you like to update the admin details of, "+username);
			
				resp.sendRedirect("updateadmin.html");
			}
			else {
				JOptionPane.showMessageDialog(null, "You have entered wrong user_id!!!");
				resp.sendRedirect("editadmin.html");
			}
			
			
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
