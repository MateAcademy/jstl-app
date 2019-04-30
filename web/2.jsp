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

<h3>Hello <c:out value="${name}"/> after registration <br>
    Ваш пароль: <c:out value="${login}"/> after registration :) <br>
    Согласен ли ты с политикой обработки данных:   <c:out value="${agree}"/> <br>
    Мой первый servlet, метод: "  <c:out value="${method}"/>  "<br>
    session account:  <c:out value="${sessionUser}"/> <br>
    servletContext usera:  <c:out value="${servletContext}"/>   <br>
</h3>

</body>
</html>
