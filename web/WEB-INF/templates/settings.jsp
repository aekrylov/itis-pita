<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 18.03.2017
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify</title>
</head>
<body>
${name}
<form action="/modify" method="post">
    <input type="text" name="phone_number" value=${phone_number} >
    <input type="text" name="email" value=${email} >
    <input type="file" name="avatar" >
    <input type="text" name="interests" value=${interests} >
    <input type="text" name="info" value=${info} >
    <input type="submit" value="Modify" />
</form>

<form action="/change_password" method="post">
    <input type="password" name="old_password" >
    <input type="password" name="password" >
    <input type="password" name="repeated_password" >
    <input type="submit" value="Change password" />
</form>
</body>
</html>
