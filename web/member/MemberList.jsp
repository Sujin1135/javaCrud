<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<h1>회원 목록</h1>
	<p><a href='add.do'>신규 회원</a></p>

	<c:forEach var="member" items="${members}">
		${member.getNo()},
		<a href='update.do?no=${member.getNo()}'>
		${member.getName()}</a>
		${member.getEmail()}
		${member.getCreateDate()}
		<a href='delete.do?no=${member.getNo()}'>[삭제]</a> <br />
	</c:forEach>

	<jsp:include page="/tail.jsp" />
	
	<%try { %>
	<textarea rows="10" cols="80">
		<c:import url="https://www.daum.net" />
	</textarea>
	<%} catch (Exception e) { %>
		URL을 다시 확인해 보세요.
	<%} %>
</body>
</html>