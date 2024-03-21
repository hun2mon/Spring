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
	
	th,td{
		padding: 5px 10px;
	}
</style>
</head>
<body>
	<h1>로그인</h1>
	<hr>
	<form action="login" method="post">
		<table>
			<tr>
				<th>ID</th>
				<td><input type="text" name = id></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="text" name = pw></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="로그인">
					<input type="button" value= "회원가입">
				</th>
			</tr>
		</table>
	</form>
</body>
<script>
	$('input[type = button]').click(function () {
		location.href = 'joinForm';
	})
	
	var msg = '${msg}'
	if (msg != '') {
		alert(msg);
	}
</script>
</html>