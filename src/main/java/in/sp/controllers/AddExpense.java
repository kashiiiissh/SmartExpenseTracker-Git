package in.sp.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.sp.dbcon.DbConnection;
import in.sp.model.User;


@WebServlet("/AddExpense")
public class AddExpense extends HttpServlet {
@Override	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		HttpSession session=request.getSession(false);
		User u=(User)session.getAttribute("session_user");
		double amount=Double.parseDouble(request.getParameter("amount"));
		String category=request.getParameter("category");
		String desc=request.getParameter("description");
		String date=request.getParameter("date");
		
		Connection con=DbConnection.getConnection();
		String sql="INSERT INTO expenses(user_id,amount,category,description,expense_date) VALUES(?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,u.getUserId());
		ps.setDouble(2,amount);
		ps.setString(3,category);
		ps.setString(4,desc);
		ps.setString(5,date);
		ps.executeUpdate();
		
		response.sendRedirect("viewExpenses.jsp");
		}
		catch(Exception e) {
		e.printStackTrace();	
		response.getWriter().print("Error: " +e.getMessage());;
		}
	}

}
