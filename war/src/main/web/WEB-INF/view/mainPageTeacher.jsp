<%@ page import="model.Person" %>
<%@ page import="model.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: Drus
  Date: 13.08.2019
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page Teacher</title>
    <style><%@include file="css/mainPageStudent.css"%></style>
    <script>
        function logout() {
            document.location.href = "logout";
        }
    </script>
</head>
<body>
    <% Person person = (Person) request.getSession().getAttribute("person"); %>
    <h1 align="center">Hello <%=person.getName()%></h1>
    <input type="button" value="Logout" onclick="logout()"><br/>

    <%
        List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
        Set<Subject> teacherSubjects = (Set<Subject>) request.getAttribute("teacherSubjects");

        for (Subject subject : subjects) {
    %>      <a href="subject?id=<%=subject.getId()%>">
                <div class="subjectDiv">
                    Subject name - <%=subject.getName()%><br/>
                    Content: <%=subject.getContent()%><br/>
                    <%
                        if (teacherSubjects.contains(subject)) {%>
                            You're a lecturer in this subject.
                        <% } else { %>
                            You're not a lecturer in this subject.
                        <% }
                    %>
                </div>
            </a>
    <%
        }
    %>
    <br/><%=request.getAttribute("markInfo") != null ? request.getAttribute("markInfo") : ""%>
</body>
</html>
