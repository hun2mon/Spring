<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
	table,th,td{
		border: 1px solid black;
		border-collapse: collapse;
	}
	th, td{
		padding: 5ps 10px;
		text-align: center;
	}
</style>
</head>
<body>
<table>
		<tr>
			<th>ID</th>
			<td>${member.id }</td>
		</tr>
		<tr>
			<th>PW</th>
			<td>${member.pw }</td>
		</tr>
		<tr>
			<th>NAME</th>
			<td>${member.name }</td>
		</tr>
		<tr>
			<th>AGE</th>
			<td>${member.age }</td>
		</tr>
		<tr>
			<th>GENDER</th>
			<td>${member.gender }</td>
		</tr>
		<tr>
			<th>EMAIL</th>
			<td>${member.email }</td>
		</tr>
		<tr>
			<th colspan="2"><a href="list">회원 목록</a></th>
		</tr>
	</table>
</body>
<script>

</script>
</html>