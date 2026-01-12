<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,in.sp.model.Expense" %>
<% if(session.getAttribute("session_user")==null){
	response.sendRedirect("login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Expenses</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<h2>My Expenses</h2>
<table border="1">
<tr>
<th>Amount</th>
<th>Category</th>
<th>Description</th>
<th>Date</th>
<th>Action</th>
</tr>
<% 
List<Expense> list=(List<Expense>)
request.getAttribute("expenses");
if(list!=null && !list.isEmpty()){
	for(Expense e :list){
%>
<tr>
<td><%=e.getAmount() %></td>
<td><%=e.getCategory() %></td>
<td><%=e.getDescription() %></td>
<td><%=e.getExpenseDate() %></td>
<td>
<a href="DeleteExpense?id=<%=e.getExpenseId() %>">Delete</a>
</td>
</tr>
<% } 
	}else{
		%>
	<tr>
	<td colspan="5" style="color:red">No expenses found</td>
	</tr>
	<%
	}
	%>
</table>
<a href="dashboard.jsp">Back</a>
</body>
</html>