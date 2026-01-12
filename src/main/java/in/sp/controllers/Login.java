package in.sp.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.sp.dbcon.DbConnection;
import in.sp.model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String email=request.getParameter("email");	
		String pass=request.getParameter("password");	
		
		Connection con=DbConnection.getConnection();
		String sql="SELECT * FROM users WHERE email=? AND password=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,email);
		ps.setString(2,pass);
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			User u=new User();
			u.setUserId(rs.getInt("user_id"));
			u.setName(rs.getString("name"));
			u.setEmail(rs.getString("email"));
			
			HttpSession session=request.getSession();
			session.setAttribute("session_user", u);
			
			response.sendRedirect("dashboard.jsp");
			}
		    else {
		    	response.sendRedirect("login.jsp?error=1");
		    	response.getWriter().print("Invalid login");
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
		}
	}

}
