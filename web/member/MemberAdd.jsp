<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 등록</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<h1>회원 등록</h1>
	<form action="add.do" method="POST">
		<p>이름: <input type="text" name="name"></p>
		<p>이메일: <input type="email" name="email"></p>
		<p>암호: <input type="password" name="password"></p>
		<input type="submit" value="추가" />
		<button type="reset" >취소</button>
	</form>
	<jsp:include page="/tail.jsp" />
</body>
</html>