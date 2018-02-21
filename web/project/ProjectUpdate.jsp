<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018. 2. 21.
  Time: PM 3:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>프로젝트 수정</title>
</head>
<body>
    <h1>프로젝트 수정</h1>
    <form action="update.do" method="POST">
        <div>
            <label for="no">프로젝트 번호</label>
            <input type="text" id="no" name="no" value="${project.getNo()}" readonly>
        </div>
        
        <div>
            <lable for="name">프로젝트명</lable>
            <input type="text" id="name" name="name" value="${project.getName()}">
        </div>
        
        <div>
            <label for="content">설명</label>
            <input type="text" id="content" name="content" value="${project.getContent()}">
        </div>

        <div>
            <label for="startDate">시작일</label>
            <input type="text" id="startDate" name="startDate" value="${project.getStartDate()}">
        </div>

        <div>
            <label for="endDate">종료일</label>
            <input type="text" id="endDate" name="endDate" value="${project.getEndDate()}">
        </div>

        <div>
            <label for="state">상태</label>
            <input type="text" id="state" name="state" value="${project.getState()}">
        </div>

        <div>
            <label for="tags">태그</label>
            <input type="text" id="tags" name="tags" value="${project.getTags()}">
        </div>

        <input type="submit" value="수정">
        <button type="reset">취소</button>
    </form>
</body>
</html>
