<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오전 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>food2 - food 글쓰기 폼 화면</h1>
<form action="/member/register" method="post">
    <div>
        <input type="text" name="name" placeholder="이름 입력 해주세요.">
    </div>
    <div>
        <input type="text" name="password" placeholder="password를 입력 해주세요.">
    </div>
    <div>
        <input type="date" name="dueDate">
    </div>
    <button type="reset">초기화</button>
    <button type="submit">등록</button>
</form>
</body>
</html>
