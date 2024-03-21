<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
	table, th, td {
	 border: 1px solid black;
	 border-collapse: collapse;
	}
	
	th,td {
	padding: 5px 10px;
	}
</style>
</head>
<body>
	<form action="list">
		<table>
			<tr>
				<th>ID</th>
				<td><input type="text" name="id" value="admin"></td>
			</tr>
			<tr>
				<th>NAME</th>
				<td></td>
			</tr>
			<tr>
				<th><input type="submit" value="전송"></th>
			</tr>
		</table>
	</form>
</body>
<script>

</script>
</html>