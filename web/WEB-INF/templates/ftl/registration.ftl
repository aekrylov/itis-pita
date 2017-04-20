<#include 'base.ftl'>

<#macro body>

    <@formTags.form method="POST" class="form-signin" modelAttribute="form">
        <h2 class="form-signin-heading">Create your account</h2>
        <@formTags.input path="email" cssClass="form-control" placeholder="Username" />
        <@formTags.errors path="email" />
        <input type="text" class="form-control" name="name" placeholder="Name" value="${form.name!""}" />
        <input type="text" class="form-control" name="surname" placeholder="Surname" value="${form.surname!""}" />
        <input type="text" class="form-control" name="phone" placeholder="Phone" value="${form.phone!""}" />

        <@formTags.password path="password" cssClass="form-control" placeholder="Password" />

        <input type="password" class="form-control" name="password_confirmed"
               placeholder="Confirm your password" />

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    <#-- TODO how errors are retrieved -->
        <@formTags.errors path="*"/>
    </@formTags.form>
    <#if error?has_content>
        Form has errors
    </#if>
</#macro>

<@display "Регистрация" />