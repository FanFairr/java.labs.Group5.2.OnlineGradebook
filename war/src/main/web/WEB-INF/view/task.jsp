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
<%
    Task task = (Task) request.getAttribute("task");
    Person person = (Person) request.getSession().getAttribute("person");
%>
<html>
<head>
    <title>Task</title>
    <script>
        function update() {
            let opt = document.getElementById("spisok");
            let str = opt.value;
            let id = str.substr(0, str.indexOf(" "));

            let mark = document.getElementById("mark").value;
            if (mark == '') {
                alert("The mark must be filled");
            } else if (mark > <%=task.getMax_mark()%> || mark < 0) {
                alert("The mark should be within 0 to <%=task.getMax_mark()%>")
            } else {
                document.location.href = "mark?studentId=" + id + "&mark=" + mark + "&teacherId=<%=person.getId()%>&taskId=<%=task.getId()%>";
            }
        }
    </script>
</head>
<body>
    <h1 align="center"><%=task.getName()%></h1><br/>
    <%
        if ("student".equals(person.getStatus())) { %>
            <h3 style="padding-left: 30px;"><%=task.getContent()%></h3>
        <% } else {
            boolean b = (boolean) request.getAttribute("personInfo");
            List<Person> personList = (List<Person>) request.getAttribute("students");
            if (b) {
        %>
            <select id="spisok">
                <%
                    for (Person person1 : personList) { %>
                       <option><%=person1.getId() + " " + person1.getName()%></option>
                    <%}
                %>
            </select>
            <input id="mark" type="text"/>
            <button onclick="update()">Update</button>
        <% } else { %>
                <h3 style="padding-left: 30px;"><%=task.getContent()%></h3>
        <% }
        }%>
    <a href="mainPage" style="padding-left: 45%;"><input type="button" value="Main Page"/></a>
</body>
</html>
