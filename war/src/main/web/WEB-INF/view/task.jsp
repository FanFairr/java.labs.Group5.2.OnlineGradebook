<%@ page import="model.Task" %>
<%@ page import="model.Person" %>
<%@ page import="java.util.List" %><%--
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
    <%
        Task task = (Task) request.getAttribute("task");
        Person person = (Person) request.getSession().getAttribute("person");
    %>
    <h1 align="center"><%=task.getName()%></h1><br/>
    <%
        if ("student".equals(person.getStatus())) { %>
            <h3 style="padding-left: 30px;"><%=task.getContent()%></h3>
        <% } else {
            boolean b = (boolean) request.getAttribute("teacherInfo");
            List<Person> personList = (List<Person>) request.getAttribute("students");
            if (b) {
        %>
            <select>
                <%
                    for (Person person1 : personList) { %>
                       <option><%=person1.getId() + " " + person1.getName()%></option>
                    <%}
                %>
            </select>
            <input type="text"/>
            <button>Update</button>
        <% } else { %>
                <h3 style="padding-left: 30px;"><%=task.getContent()%></h3>
        <% }
        }%>
    <a href="mainPage" style="padding-left: 45%;"><input type="button" value="Main Page"/></a>
</body>
</html>
