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
        <link rel="stylesheet" href="../css/index.css">
    </head>
    <body>
        <h1>프로젝트 리스트</h1>
        <table>
            <thead>
                <tr>
                    <th>
                        <c:choose>
                            <c:when test="${orderCond == 'PNO_ASC'}">
                                <a href="list.do?orderCond=PNO_DESC">번호↓</a>
                            </c:when>
                            <c:when test="${orderCond == 'PNO_DESC'}">
                                <a href="list.do?orderCond=PNO_ASC">번호↑</a>
                            </c:when>
                            <c:otherwise>
                                <a href="list.do?orderCond=PNO_ASC">번호</a>
                            </c:otherwise>
                        </c:choose>
                    </th>

                    <th>
                        <c:choose>
                            <c:when test="${orderCond == 'TITLE_ASC'}">
                                <a href="list.do?orderCond=TITLE_DESC">제목↓</a>
                            </c:when>
                            <c:when test="${orderCond == 'TITLE_DESC'}">
                                <a href="list.do?orderCond=TITLE_ASC">제목↑</a>
                            </c:when>
                            <c:otherwise>
                                <a href="list.do?orderCond=TITLE_ASC">제목</a>
                            </c:otherwise>
                        </c:choose>
                    </th>

                    <th>
                        <c:choose>
                            <c:when test="${orderCond == 'STARTDATE_ASC'}">
                                <a href="list.do?orderCond=STARTDATE_DESC">시작일↓</a>
                            </c:when>
                            <c:when test="${orderCond == 'STARTDATE_DESC'}">
                                <a href="list.do?orderCond=STARTDATE_ASC">시작일↑</a>
                            </c:when>
                            <c:otherwise>
                                <a href="list.do?orderCond=STARTDATE_ASC">시작일</a>
                            </c:otherwise>
                        </c:choose>
                    </th>

                    <th>
                        <c:choose>
                            <c:when test="${orderCond == 'ENDDATE_ASC'}">
                                <a href="list.do?orderCond=ENDDATE_DESC">종료일↓</a>
                            </c:when>
                            <c:when test="${orderCond == 'ENDDATE_DESC'}">
                                <a href="list.do?orderCond=ENDDATE_ASC">종료일↑</a>
                            </c:when>
                            <c:otherwise>
                                <a href="list.do?orderCond=ENDDATE_ASC">종료일</a>
                            </c:otherwise>
                        </c:choose>
                    </th>

                    <th>
                        <c:choose>
                            <c:when test="${orderCond == 'STATE_ASC'}">
                                <a href="list.do?orderCond=STATE_DESC">상태↓</a>
                            </c:when>
                            <c:when test="${orderCond == 'STATE_DESC'}">
                                <a href="list.do?orderCond=STATE_ASC">상태↑</a>
                            </c:when>
                            <c:otherwise>
                                <a href="list.do?orderCond=STATE_ASC">상태</a>
                            </c:otherwise>
                        </c:choose>
                    </th>
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
