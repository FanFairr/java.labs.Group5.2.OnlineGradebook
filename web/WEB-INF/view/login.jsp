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
    <title>Login</title>
</head>
<body>
<spring:form modelAttribute="user" method="post" action="/java_labs_Group5_2_OnlineGradebook_war_exploded/checkLogin" cssStyle="padding-left: 40%;">
    <spring:input path="login"/><br/>
    <spring:password path="password"/><br/>
    ${validate}<br/>
    <spring:button>Check</spring:button>
</spring:form>
<a href="/java_labs_Group5_2_OnlineGradebook_war_exploded/registration" style="padding-left: 40%;"><button>Registration</button></a>
</body>
</html>
