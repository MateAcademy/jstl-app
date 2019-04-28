<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 28.04.2019
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head charset=\"utf-8\">
    <title>Web application Мэйт Академии</title>
</head>
<body style="background-image:url(girl.jpg);  color:#ff6c36; font-weight: normal ">

<h3>Hello <%=request.getAttribute("name")%> after registration <br>
    Ваш пароль: <%=request.getAttribute("login")%> after registration :) <br>
    Согласен ли ты с политикой обработки данных:   <%=request.getAttribute("agree")%> <br>
    Мой первый servlet, метод: " <%=request.getAttribute("method")%> "<br>
    session account:   <%=request.getAttribute("sessionUser")%> <br>
    servletContext usera:  <%=request.getAttribute("servletContext")%>   <br>
</h3>

</body>
</html>
