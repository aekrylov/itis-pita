<#include 'base.ftl' >

<#macro body>
        <form method="post" action="/labs/create">
            <input name="name"/>
            <textarea name="description"></textarea>

            <input name="image" type="file"/>

            <#-- TODO teachers list -->

            <input type="submit">
        </form>
</#macro>

<@display "Создать лабораторию"></@display>