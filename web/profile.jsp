<%--
  Created by IntelliJ IDEA.
  User: taa
  Date: 18.03.17
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:choose>
  <c:when test="${empty error}">
    <h1>Имя: ${user.name} </h1>
    <h1>Телефон: ${user.phone} </h1>
    <h1>Почта: ${user.email} </h1>
  </c:when>
  <c:otherwise>
    ${error}
  </c:otherwise>
</c:choose>




</body>
</html>
