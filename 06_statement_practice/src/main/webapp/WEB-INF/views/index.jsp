<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="stmt" method="get">
		<button>테이블 생성</button>
	</form>
	${msg} <!-- model 에서 보내온 msg 값 -->
	
	<form action="insert" method="post">
		ID : <input type="text" name="ID"><br>
		PW : <input type="text" name="PW"><br>
		NAME : <input type="text" name="NAME"><br>
		AGE : <input type="text" name="AGE"><br>
		GENDER : <input type="text" name="GENDER"><br>
		EMAIL : <input type="text" name="EMAIL"><br>
		<input type="submit" value="전송">
	</form>
	${msg} <!-- model 에서 보내온 msg 값 -->
	
</body>
</html>