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
    <title>Страница пользователя</title>
</head>
<body>
<c:choose>
    <c:when test="${empty error}">
        <h1>Имя: ${user.name} </h1>
        <h1>Телефон: ${user.phone} </h1>
        <h1>Почта: ${user.email} </h1>

        <!-- needed link <a/> to lab page-->
        <h1>Лаба: ${lab_id.name}</h1>

        <h1>Группа: ${academic_group.name}</h1>

    </c:when>
    <c:otherwise>
        ${error}
    </c:otherwise>
</c:choose>

</body>
</html>
<!-- (by Oleg)
needed:
-list of all contacts of user
-link or some other method, which may be applied to "write message button"
-switching "write message" button to another button if user is owner of page
-list of interests
-another fields for another roles
-migration to .ftl
-->
