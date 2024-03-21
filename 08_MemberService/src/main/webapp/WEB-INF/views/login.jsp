<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
	table, td, th{
		border: 1px solid black;
		border-collapse: collapse;
	}
	
	th, td{
		padding: 5px 10px;
	}
</style>
</head>
<body>
	<h2>회원관리 시스템 로그인</h2>
	<form action="login" method="post">
		<table>
			<tr>
				<th>ID</th>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="password" name="pw"></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="로그인">
					<input type="button" value="회원가입">
				</th>
			</tr>
		</table>
	</form>
</body>
<script>
	$('input[type = "button"]').on('click',function(){
		console.log('click');
		// spring 에서는 jsp 이동도 controller 를 통해야 한다.
		location.href = 'joinForm';
	});
	
	if ('${msg}' == '성공') {
	    alert('회원가입에 성공하였습니다.');
	}
	
	
	
</script>
</html>