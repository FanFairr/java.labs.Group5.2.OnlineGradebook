<%@ page import="model.Person" %>
<%@ page import="model.Subject" %>
<%@ page import="java.util.Map" %>
<%--
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
    <style><%@include file="css/mainPageStudent.css"%></style>
    <script>
        function logout() {
            document.location.href = "logout";
        }
    </script>
</head>
<body>
    <%
        Person person = (Person) request.getSession().getAttribute("person");
    %>
    <h1 align="center">Hello <%=person.getName()%></h1>
    <input type="button" value="Logout" onclick="logout()"><br/>
    <%
        Map<Subject, String> subjects = (Map<Subject, String>) request.getAttribute("subjects");
        Map<Subject, String> studentSubjects = (Map<Subject, String>) request.getAttribute("studentSubjects");

        for (Subject subject : subjects.keySet()) {
    %>      <a href="subject?id=<%=subject.getId()%>">
                <div class="subjectDiv">
                    Subject name - <%=subject.getName()%><br/>
                    Content: <%=subject.getContent()%><br/>
                    Teacher - <%=subjects.get(subject)%><br/>
    <%
                    if (studentSubjects.containsKey(subject)) {%>
                        You are subscribed to this subject
                    <% } else { %>
                        You are not subscribed to this subject
                    <% }
    %>
                </div>
            </a>
    <%
        }
    %>
</body>
</html>
