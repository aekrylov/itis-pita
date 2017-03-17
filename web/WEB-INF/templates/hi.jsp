<%--
  Created by IntelliJ IDEA.
  User: anth
  Date: 3/17/17
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Hi!</title>
</head>
<body>

<table>
    <thead>
    <th>Id</th>
    <th>email</th>
    </thead>
    <tbody>
    <tr>
        <td>${user.id}</td>
        <td>${user.email}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
