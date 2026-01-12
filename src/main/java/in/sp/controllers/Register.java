package in.sp.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.sp.dbcon.DbConnection;


@WebServlet("/Register")
public class Register extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String name=request.getParameter("name");	
		String email=request.getParameter("email");	
		String pass=request.getParameter("password");
		
		Connection con=DbConnection.getConnection();
		String sql="INSERT INTO USERS(name,email,password) VALUES(?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2,email);
		ps.setString(3, pass);
		
		ps.executeUpdate();
		response.sendRedirect("login.jsp");
		}
		catch(Exception e) {
		e.printStackTrace();
		response.getWriter().print("Error :"+e.getMessage());
		}
	}

}
