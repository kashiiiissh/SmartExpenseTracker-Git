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
<title>Monthly Report</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Monthly Report</h2>
<h3>Total Spent: ₹ <%= request.getAttribute("total") %></h3>
<a href="dashboard.jsp">Back</a>
</body>
</html>