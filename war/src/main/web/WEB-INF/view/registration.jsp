<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Drus
  Date: 18.07.2019
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <script><%@include file="js/registration.js"%></script>
</head>
<body>
<div style="padding-left: 40%;">
    Login: <input type="text" id="login"/><br/>
    Password: <input type="password" id="password"/><br/>
    Name: <input type="text" id="name"/><br/>
    Email: <input type="text" id="email"/><br/>
    ${validate}<br/>
    <input type="button" onclick="reg()" value="Check"/>
</div>
<a href="login" style="padding-left: 40%;"><button>Login</button></a>
</body>
</html>
