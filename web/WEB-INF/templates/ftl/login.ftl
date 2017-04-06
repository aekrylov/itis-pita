<#ftl encoding='utf-8'>
<#include 'base_anon.ftl' >
<#macro header_custom_imports>
    <link rel="stylesheet" href="/static/css/style.css">
</#macro>

<#macro body>
<div class="col-md-12">
    <div class="login-block">
        <h1>Вход на сайт:</h1>
        <form method="post" class="login-form" action="/login">
            <div class="form-group">
                <label class="forms-label" for="email">E-mail</label>
                <input class="form-control" type="email" id="email" name="email"/>
            </div>
            <div class="form-group">
                <label class="forms-label" for="password">Пароль</label>
                <input class="form-control" type="password" id="password" name="password"/>
            </div>
            <div class="form-group form-group-helper">
                <label class="login-checkbox">
                    <input type="checkbox" value="remember-me" id="checkbox"> Запомнить
                </label>
                <button type="submit" class="btn-login">Войти</button>
            </div>
        </form>
        <div class="form-group form-group-helper bottom-links-login">
            <a href="#" id="forgot-pass">Забыли пароль?</a> | <a href="#" id="signup"> Регистрация</a>
        </div>
    </div>
</div>
</#macro>
<@display "Вход | PITA"></@display>