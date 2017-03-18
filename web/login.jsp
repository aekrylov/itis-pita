<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <style>
        <%@include file='css/bootstrap.css' %>
        <%@include file='css/style.css' %>
    </style>
    <title>Вход | PITA</title>
</head>
<body>
<div class="col-md-12 header">
    <img src="http://placehold.it/260x50">
</div>
<div class="col-md-12">
    <div class="login-block">
        <h1>Вход:</h1>
        <input type="text" value="" placeholder="E-mail" id="email" />
        <input type="password" value="" placeholder="Пароль" id="password_hash" />
        <div class="form-group">
            <label class="login-checkbox">
                <input type="checkbox" value="remember-me" id="checkbox"> Запомнить
            </label>
            <button type="submit" class="btn-login" >Войти</button>
        </div>
        <div class="form-group bottom-links-login">
            <a href="#" id="forgot-pass">Забыли пароль?</a> | <a href="#" id="signup"> Регистрация</a>
        </div>
    </div>
</div>
</body>
</html>