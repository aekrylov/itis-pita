<#include 'base.ftl' >

<#macro body>
<p>list of courses sholud be here</p>
<#list courses as course>
    <p>${course.name}</p>

    <#list course.teachers as teacher>
        <li>${teacher.name}</li>
    </#list>
</#list>
</#macro>

<@body></@body>