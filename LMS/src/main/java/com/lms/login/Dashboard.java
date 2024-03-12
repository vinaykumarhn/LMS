package com.lms.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")

public class Dashboard extends HttpServlet
{
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	 {
		 String buttonClicked = req.getParameter("button");
		 if ("staffdetails".equals(buttonClicked)) {
			 resp.sendRedirect("staffdetails.html");
		 } else if ("addstaff".equals(buttonClicked)) {
	            resp.sendRedirect("addstaff.html");
	        } else if ("removestaff".equals(buttonClicked)) {
	            resp.sendRedirect("removestaff.html");
	        }else if ("booksavailable".equals(buttonClicked)) {
	            resp.sendRedirect("booksavailable.html");
	        } else if ("addbook".equals(buttonClicked)) {
	            resp.sendRedirect("addbook.html");
		 }else if ("removebook".equals(buttonClicked)) {
	            resp.sendRedirect("removebook.html");
		 }else if ("editadmin".equals(buttonClicked)) {
	            resp.sendRedirect("editadmin.html");
		 }else {
	            // Handle invalid button click
//			 JOptionPane.showMessageDialog(null, "Invalid Button clicked");
           resp.getWriter().println("Invalid button clicked!");
	        }
		 
		 
	 }
	

	

}

		
	

	

