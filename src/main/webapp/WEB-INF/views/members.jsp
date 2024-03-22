<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: sajaebin
  Date: 3/21/24
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>회원 목록</h1>
<table border="1">
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%
        List<Member> members = (List<Member>) request.getAttribute("members");
        for (Member member : members) {
    %>
    <tr>
        <td><%= member.getId() %></td>
        <td><%= member.getUsername() %></td>
        <td><%= member.getAge() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
