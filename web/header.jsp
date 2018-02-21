<%@page import="spms.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">
SPMS(Simple Project Management System)
	<span style="float:right;">
	<c:choose>
		<c:when test="${sessionScope.member.name != null}">
			${sessionScope.member.getName()}
			<a style="color:white;"
			   href="<%=request.getContextPath()%>/auth/logout.do">로그아웃</a>
		</c:when>

		<c:when test="${sessionScope.member.getName() == null}">
		<a style="color:white;"
			href="<%=request.getContextPath()%>/auth/login.do">로그인</a>
		</c:when>
	</c:choose>
	</span>
</div>