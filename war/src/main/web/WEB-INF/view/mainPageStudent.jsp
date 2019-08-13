<%@ page import="model.Person" %><%--
  Created by IntelliJ IDEA.
  User: Drus
  Date: 13.08.2019
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Main Page</title>
</head>
<body>
    <%
        Person person = (Person) request.getSession().getAttribute("person");
    %>
    <h1 align="center">Hello <%=person.getName()%></h1><br/>
    <input type="button" value="Subject" class="button"/>
    <input type="button" value="Marks" class="button"/>
</body>
</html>
