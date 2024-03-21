<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href = "resources/common.css" type="text/css">
<style>

</style>
</head>
<body>
	<form action="update" method="post">
		<table>
			<tr>
				<th>글번호</th>
				<td><input type="text" name="idx" value="${bbs.idx }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="${bbs.subject }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="user_name" value="${bbs.user_name }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content">${bbs.content }</textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<button>수정</button>
				</th>
			</tr>
		</table>
	</form>
</body>
<script>

</script>
</html>