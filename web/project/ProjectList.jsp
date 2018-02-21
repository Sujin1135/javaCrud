<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018. 2. 21.
  Time: PM 2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>프로젝트 리스트</title>
</head>
<body>
    <h1>프로젝트 리스트</h1>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>프로젝트명</th>
                <th>시작일</th>
                <th>종료일</th>
                <th>상태</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${projects}">
                <tr>
                    <td>${item.getNo()}</td>
                    <td><a href="update.do?no=${item.getNo()}">${item.getName()}</a></td>
                    <td>${item.getStartDate()}</td>
                    <td>${item.getEndDate()}</td>
                    <td>${item.getState()}</td>
                    <td><a href="delete.do?no=${item.getNo()}">삭제</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="add.do">프로젝트 추가</a>
</body>
</html>
