<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("session_user")==null){
	response.sendRedirect("login.jsp");
	return;
}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Expense</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
<h2>Add Expense</h2>
<form action="AddExpense" method="post">
Amount:<input type="number" step="0.01" name="amount" required><br><br>
Category:<input type="text" name="category" required><br><br>
Description:<input type="text" name="description" required><br><br>
Date:<input type="date" name="date" required><br><br>
<button type="submit">Save</button>
</form>
</div>
<div class="navbar">
<a href="dashboard.jsp">Back</a>
</div>
</body>
</html>