<%@ page import="model.Task" %><%--
  Created by IntelliJ IDEA.
  User: Drus
  Date: 15.08.2019
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task</title>
</head>
<body>
    <% Task task = (Task) request.getAttribute("task"); %>
    <h1 align="center"><%=task.getName()%></h1><br/>
    <h3 style="padding-left: 30px;"><%=task.getContent()%></h3>
    <a href="mainPage" style="padding-left: 45%;"><input type="button" value="Main Page"/></a>
</body>
</html>
