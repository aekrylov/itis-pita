<#include 'base.ftl'>
<#macro body>
    <ul>
        <#list urls as url>
            <li><a href="${url}">${url}</a></li>
        </#list>
    </ul>
</#macro>

<@display "admin main"></@display>