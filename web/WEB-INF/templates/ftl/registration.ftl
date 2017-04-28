<#ftl encoding='utf-8'>

<#include 'base.ftl' >

<#macro header_custom_imports>
<link rel="stylesheet" href="/static/css/registration.css">
<script type="text/javascript" src="/static/js/bootstrap-formhelpers-phone.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-formhelpers-phone.format.js"></script>
</#macro>

<#macro body>
<div class="col-md-12">
    <div class="registration-block">
        <h1>Регистрация:</h1>
        <#if error?has_content>
            <div class="alert alert-danger">
                Form has errors!
            </div>
        </#if>
        <@formTags.form method="POST" class="registration-form" modelAttribute="form">
            <div class="form-group">
                <label class="forms-label" for="email">E-mail</label>
                <@formTags.input path="email" cssClass="form-control" />
                <@formTags.errors path="email" />
            </div>
            <div class="form-group">
                <label class="forms-label" for="surname">Фамилия</label>
                <input class="form-control" type="text" id="surname" name="surname" value="${form.surname!""}"/>
            </div>
            <div class="form-group">
                <label class="forms-label" for="name">Имя</label>
                <input class="form-control" type="text" id="name" name="name" value="${form.name!""}"/>
            </div>
            <div class="form-group">
                <label class="forms-label" for="phone">Телефон</label>
                <input class="input-medium bfh-phone form-control" type="text"
                       data-format="+7 (ddd) ddd-dd-dd" id="phone" name="phone" value="${form.phone!""}"/>
            </div>
            <div class="form-group">
                <label class="forms-label" for="password">Пароль</label>
                <@formTags.password path="password" cssClass="form-control" />
            </div>
            <div class="form-group">
                <label class="forms-label" for="password_confirmed">Повторите пароль</label>
                <input class="form-control" type="password" id="password_confirmed" name="password_confirmed"/>
            </div>
            <div class="btn-submit-registration">
                <button type="submit" class="btn-registration">Зарегистрироваться</button>
            </div>
            <@formTags.errors path="*"/>
        </@formTags.form>
    </div>
</div>
</#macro>

<@display "Регистрация | PITA" />