package in.sp.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.sp.dbcon.DbConnection;
import in.sp.model.Expense;
import in.sp.model.User;


@WebServlet("/ViewExpense")
public class ViewExpense extends HttpServlet {
@Override	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=	request.getSession(false);
	if(session==null || session.getAttribute("session_user")==null) {
		response.sendRedirect("login.jsp");
		return;
	}
	User user=(User)session.getAttribute("session_user");
	List<Expense>list=new ArrayList<>();
	try {
		Connection con=DbConnection.getConnection();
		String sql="SELECT * FROM expenses WHERE user_id=? ORDER BY expense_date DESC";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,user.getUserId());
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			Expense e=new Expense();
			e.setUserId(rs.getInt("user_id"));
			e.setAmount(rs.getDouble("amount"));
			e.setCategory(rs.getString("category"));
			e.setDescription(rs.getString("description"));
			e.setExpenseDate(rs.getString("expense_date"));
			list.add(e);
		}
		request.setAttribute("expenses", list);
		RequestDispatcher rd=request.getRequestDispatcher("viewExpenses.jsp");
		rd.forward(request, response);
	    } 
		catch(Exception e) {
			e.printStackTrace();
			response.getWriter().print("Error: "+e.getMessage());
		}
	}

}
