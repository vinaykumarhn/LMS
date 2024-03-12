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
@WebServlet("/login")

public class Login extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
	   String name=req.getParameter("name");
	   String password=req.getParameter("password");
	   Connection con=null;
	   PreparedStatement ps=null;
	   ResultSet rs=null;
	   String url="jdbc:mysql://localhost:3306/library";
	   String user="root";
	   String pass="tiger";
	   String query="select * from admin where user_id=? and password=?";
	   PrintWriter out=resp.getWriter();	   
	   try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection(url, user, pass);
		ps=con.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, password);
		rs=ps.executeQuery();
		if(rs.next())
			{
			String name1=rs.getString(1);
			String password1=rs.getString(3);
			JOptionPane.showMessageDialog(null, "Login successful! Welcome back!" +name);
			resp.sendRedirect("dashboard.html");  
             } 
		else {
                 // Login failed
                 resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                 PrintWriter out1 = resp.getWriter();
                 JOptionPane.showMessageDialog(null, "Invalid Username or password" );
                 resp.sendRedirect("liblogin.html");
             }
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	
}
