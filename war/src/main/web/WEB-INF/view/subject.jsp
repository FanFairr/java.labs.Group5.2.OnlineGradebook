<%@ page import="model.Subject" %>
<%@ page import="model.Person" %>
<%@ page import="model.Mark" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Task" %><%--
  Created by IntelliJ IDEA.
  User: Drus
  Date: 14.08.2019
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subject</title>
    <style><%@include file="css/subject.css"%></style>
</head>
<body>
    <%
        List<Task> tasks = (List<Task>) request.getAttribute("tasks");
        Map<Person, List<Mark>> map = (Map<Person, List<Mark>>) request.getAttribute("marks");
    %>
    <h1 align="center"><%=tasks.get(0).getSubjectName()%></h1><br/>
    <div>
        <table style="border: 1px solid black;">
            <tr>
                <th>&nbsp;</th>
                <%  for (Task task : tasks) { %>
                        <th><a href=""><%=task.getName()%></a></th>
                <%  }                         %>
            </tr>
            <%  for (Person person : map.keySet()) { %>
                  <tr>
                      <td><%=person.getName()%></td>
                      <%
                          for (Task task : tasks) {
                              double d = 0;
                              for (Mark mark : map.get(person)) {
                                  if (task.getName().equals(mark.getTaskName())) {
                                      d = mark.getMark();
                                      break;
                                  }
                              } %>
                              <td><%=d%></td>
                          <%}
                      %>
                  </tr>
            <% }                                     %>
        </table>
    </div>
</body>
</html>
