<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018. 2. 19.
  Time: PM 3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>프로젝트 추가</title>
</head>
<body>
<jsp:include page="/header.jsp" />
<h1>프로젝트 추가</h1>
<form action="add.do" method="POST">
    <ul>
        <li>
            <label for="name">제목</label>
                <input type="text" id="name" name="name">
        </li>
        <li>
            <label for="content">내용</label>
            <textarea id="content"
                      name='content' rows="5" cols="40"></textarea>
        </li>
        <li>
            <label for="sdate">시작일</label>
            <input id="sdate"
                   type='text' name='startDate' placeholder="예)2013-01-01">
        </li>
        <li>
            <label for="edate">종료일</label>
            <input id="edate"
                   type='text' name='endDate' placeholder="예)2013-01-01">
        </li>
        <li>
            <label for="tags">태그</label>
            <input id="tags"
                   type='text' name='tags' placeholder="예)태그1 태그2 태그3" size="50">
        </li>
    </ul>
    <input type="submit" value="확인" />
    <button type="reset">취소</button>
</form>
<jsp:include page="/tail.jsp" />
</body>
</html>
