<%--
  Created by IntelliJ IDEA.
  User: sajaebin
  Date: 3/21/24
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%
  MemberRepository memberRepository = MemberRepository.getInstance();
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));
  Member member = new Member(username, age);

  memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>username=<%= member.getUsername() %></li>
    <li>age=<%= member.getAge() %></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
