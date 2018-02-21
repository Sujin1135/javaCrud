<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<h1>회원 정보</h1>
	<form action="update.do" method="POST">
		<p>번호: <input type="text" name="no" value="${member.getNo()}" readonly/></p>
		<p>이름: <input type="text" name="name" value="${member.getName()}" /></p>
		<p>이메일: <input type="email" name="email" value="${member.getEmail()}" /></p>
		<p>가입일: <input type="text" value="${member.getCreateDate()}" /></p>
		<input type="submit" value="저장" />
		<button type="reset">취소</button>
	</form>
	<jsp:include page="/tail.jsp" />
</body>
</html>