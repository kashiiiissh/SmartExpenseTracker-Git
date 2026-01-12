<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="in.sp.model.User"%>
<% if(session.getAttribute("session_user")==null){
	response.sendRedirect("login.jsp");
	return;
}
 User u=(User)session.getAttribute("session_user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="navbar">
<h2>Welcome, <%=u.getName() %></h2>
<a href="addExpense.jsp">Add Expense</a> |
<a href="ViewExpense">View Expenses</a> |
<a href="MonthlyReport">Monthly Report</a> |
<a href="logout.jsp">Logout</a>
</div>

<div class="container">
<h2>Expense Tracker Dashboard</h2>
<p>You can manage all your expenses from here.</p>
</div>
</body>
</html>