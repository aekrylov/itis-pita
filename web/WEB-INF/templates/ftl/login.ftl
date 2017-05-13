<#ftl encoding='utf-8'>
<#include 'base.ftl' >
<#macro header_custom_imports>
    <link rel="stylesheet" href="/static/css/style.css">
</#macro>

<#macro body>
<div class="col-md-12">
    <div class="login-block">
        <h1>Вход на сайт:</h1>
        <@formTags.form method="POST" class="login-form" modelAttribute="form" onsubmit="return checkform([
            'email', 'password'])">
            <#assign email_error><@formTags.errors path="email"/></#assign>
            <div class="form-group" onclick="removeHasError('email')">>
                <label class="forms-label" <#if email_error?has_content>style="color: red;" </#if> for="email">E-mail</label>
                <input class="form-control" type="email" id="email" name="email"/>
                <@formTags.errors path="email" cssClass="form-text text-muted error-message"/>
            </div>
            <#assign password_error><@formTags.errors path="password"/></#assign>
            <div class="form-group" onclick="removeHasError('password')">>
                <label class="forms-label" <#if password_error?has_content>style="color: red;"</#if> for="password">Пароль</label>
                <input class="form-control" type="password" id="password" name="password"/>
                <@formTags.errors path="password" cssClass="form-text text-muted error-message error-message"/>
            </div>
            <div class="form-group form-group-helper">
                <label class="login-checkbox">
                    <input type="checkbox" value="remember-me" id="checkbox"> Запомнить
                </label>
                <button type="submit" class="btn-login">Войти</button>
            </div>
        </@formTags.form>
        <div class="form-group form-group-helper bottom-links-login">
            <a href="#" id="forgot-pass">Забыли пароль?</a> | <a href="/registration" id="signup"> Регистрация</a>
        </div>
    </div>
</div>
</#macro>
<@display "Вход | PITA"></@display>