<#ftl encoding='utf-8'>
<#include 'base.ftl' >

<#macro body>
    <form action="edit" method="post">
        <#list fields as field>
            <label>
                ${field}
                <input name="${field}" value="${entry[field]!""}">
            </label>
        </#list>
        <input type="submit">
    </form>
</#macro>
<@display "admin"></@display>