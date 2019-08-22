<%@ page import="model.Person" %><%--
  Created by IntelliJ IDEA.
  User: Drus
  Date: 13.08.2019
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Main Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>

</head>
<body>
    <% Person person = (Person) request.getSession().getAttribute("person");%>
    <h1 align="center">Hello <%=person.getName()%></h1><br/>
    <form>
        <input type="button" value="Add Teacher" id="btn1" style="margin-left: 15%;" onclick="btn1()">
        <input type="button" value="Add subject for teacher" id="btn2" style="margin-left: 15%;">
        <input type="button" value="Add subject" id="btn3" style="margin-left: 15%;">
        <input type="button" value="Add task" id="btn4" style="margin-left: 15%;">
    </form>
    <div id="content"></div>
    <script>
        $(document).ready(function(){

            $('#btn1').click(function(){
                $.ajax({
                    url: "addTeacher",
                    cache: false,
                    success: function(html){
                        $("#content").html(html);
                    }
                });
            });

            $('#btn2').click(function(){
                $.ajax({
                    url: "addSubjectForTeacher",
                    cache: false,
                    success: function(html){
                        $("#content").html(html);
                    }
                });
            });

            $('#btn3').click(function(){
                $.ajax({
                    url: "addSubject",
                    cache: false,
                    success: function(html){
                        $("#content").html(html);
                    }
                });
            });

            $('#btn4').click(function(){
                $.ajax({
                    url: "addTask",
                    cache: false,
                    success: function(html){
                        $("#content").html(html);
                    }
                });
            });
        });
    </script>
</body>
</html>
