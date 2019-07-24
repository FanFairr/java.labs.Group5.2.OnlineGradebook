<%@ page import="model.Person" %><%--
  Created by IntelliJ IDEA.
  User: Drus
  Date: 18.07.2019
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello friend</title>
</head>
<body>
    <% Person person = (Person) request.getSession().getAttribute("user"); %>
    Hello <%=person.getName()%> <br/>

</body>
</html>
