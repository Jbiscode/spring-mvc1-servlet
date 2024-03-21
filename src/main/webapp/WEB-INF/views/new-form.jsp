<%--
  Created by IntelliJ IDEA.
  User: sajaebin
  Date: 3/21/24
  Time: 3:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%--상대경로 사용 [현재URL이 속한 계층 경로에서 /save]--%>
<%--다른 연습에서도 재사용하기 위해서 상대경로로 해둠--%>
<form action="save" method="post">
    username : <input type="text" name="username" />
    age : <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
