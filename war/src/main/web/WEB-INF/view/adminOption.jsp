<%@ page import="model.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Subject" %><%--
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
    <script><%@include file="js/adminOption.js" %></script>
</head>
<body>
    <%
        String option = (String) request.getAttribute("option");

        if ("addTeacher".equals(option)) {                      %>
            <p align="center"><h3>Select a student who will be a teacher.</h3></p>
            <select id="student">
        <%  List<Person> personList = (List<Person>) request.getAttribute("students");
            for (Person person : personList) {                  %>
                    <option><%=person.getId() + " " + person.getName()%></option>
        <%      }                                               %>
            </select>
            <input type="button" value="Add Teacher" onclick="addTeacher()" style="margin-left: 10px;">









        <% } else if ("addSubjectForTeacher".equals(option)) {
           List<Person> personList = (List<Person>) request.getAttribute("teachers");
           List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
        %>
            <p align="center"><h3>Choose the name of the teacher and the subject that he will teach.</h3></p>
            Teachers - <select id="teachers">
                <% for (Person person : personList) { %>
                    <option><%=person.getId() + " " + person.getName()%></option>
                <% } %>
            </select>
            Subjects - <select id="subjects">
                <% for (Subject subject : subjects) { %>
                    <option><%=subject.getId() + " " + subject.getName()%></option>
                <% } %>
            </select>
            <input type="button" value="Add subject for teacher" onclick="addSubjectForTeacher()" style="margin-left: 10px;">







        <% } else if ("addSubject".equals(option)) {            %>
            <p align="center"><h3>Enter the name of the subject and its contents.</h3></p>
            Subject name - <input type="text" id="subjectName" style="margin-top: 10px;"><br/>
            Subject content - <textarea id="subjectContent" cols="30" rows="10" style="margin-top: 10px;"></textarea><br/>
            <input type="button" value="Add subject" onclick="newSubject()" style="margin-top: 10px;">








        <% } else if ("addTask".equals(option)) {
            List<Subject> subjects = (List<Subject>) request.getAttribute("subjects"); %>
            <p align="center"><h3>Enter the subject and describe the new task.</h3></p>
            Subjects - <select id="subjects">
                <% for (Subject subject : subjects) { %>
                <option><%=subject.getName()%></option>
                <% } %>
            </select><br/>
            Task name - <input type="text" id="taskName" style="margin-top: 10px;"><br/>
            Task content - <textarea id="taskContent" cols="30" rows="10" style="margin-top: 10px;"></textarea><br/>
            Max mark - <input type="text" id="maxMark" style="margin-top: 10px;"><br/>
            <input type="button" value="Add task" onclick="newTask()" style="margin-top: 10px; ">

        <% }
    %>
</body>
</html>
