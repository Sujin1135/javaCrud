<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<h1>로그인</h1>
	<form action="login.do" method="POST">
		<p>이메일: <input type="email" name="email" /></p>
		<p>비밀번호: <input type="password" name="password" /></p>
		<input type="submit" value="확인" />
		<button type="reset" value="취소" >취소</button>
 	</form>
 	<jsp:include page="/tail.jsp" />
</body>
</html>