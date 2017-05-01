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
        <@formTags.form method="POST" class="registration-form" modelAttribute="form">
            <#assign email_error><@formTags.errors path="email"/></#assign>
            <div class="form-group">
                <label class="forms-label" <#if email_error?has_content>style="color: red;" </#if> for="email">E-mail</label>
                <@formTags.input path="email" cssClass="form-control" />
                <@formTags.errors path="email" cssClass="form-text text-muted error-message"/>
            </div>
            <#assign surname_error><@formTags.errors path="surname"/></#assign>
            <div class="form-group">
                <label class="forms-label" <#if surname_error?has_content>style="color: red;" </#if> for="surname">Фамилия</label>
                <input class="form-control" type="text" id="surname" name="surname" value="${form.surname!""}"/>
                <@formTags.errors path="surname" cssClass="form-text text-muted error-message error-message"/>
            </div>
            <#assign name_error><@formTags.errors path="name"/></#assign>
            <div class="form-group">
                <label class="forms-label" <#if name_error?has_content>style="color: red;"</#if> for="name">Имя</label>
                <input class="form-control" type="text" id="name" name="name" value="${form.name!""}"/>
                <@formTags.errors path="name" cssClass="form-text text-muted error-message error-message"/>
            </div>
            <#assign phone_error><@formTags.errors path="phone"/></#assign>
            <div class="form-group">
                <label class="forms-label" <#if phone_error?has_content>style="color: red;"</#if> for="phone">Телефон</label>
                <input class="input-medium bfh-phone form-control" type="text"
                       data-format="+7 (ddd) ddd-dd-dd" id="phone" name="phone" value="${form.phone!""}"/>
                <@formTags.errors path="phone" cssClass="form-text text-muted error-message error-message"/>
            </div>
            <#assign password_error><@formTags.errors path="password"/></#assign>
            <div class="form-group">
                <label class="forms-label" <#if password_error?has_content>style="color: red;"</#if> for="password">Пароль</label>
                <@formTags.password path="password" cssClass="form-control" />
                <@formTags.errors path="password" cssClass="form-text text-muted error-message error-message"/>
            </div>
            <#assign password_confirmed_error><@formTags.errors path="password_confirmed"/></#assign>
            <div class="form-group">
                <label class="forms-label" <#if password_confirmed_error?has_content>style="color: red;"</#if> for="password_confirmed">Повторите пароль</label>
                <input class="form-control" type="password" id="password_confirmed" name="password_confirmed"/>
                <@formTags.errors path="password_confirmed" cssClass="form-text text-muted error-message error-message"/>
            </div>
            <div class="btn-submit-registration">
                <button type="submit" class="btn-registration">Зарегистрироваться</button>
            </div>
        </@formTags.form>
    </div>
</div>
</#macro>

<@display "Регистрация | PITA" />