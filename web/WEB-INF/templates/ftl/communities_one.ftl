<#include 'base.ftl'>

<#macro body>
        <ul>
            <li>Name: ${group.name}</li>
            <li>Admins:
                <ul>
                <#list group.admins as admin>
                    <li>${admin.email} </li>
                </#list>
                </ul>
            </li>
            <li>Members: ${group.members?size}</li>
        </ul>

<#if group.members?seq_contains(current_user)>
        <h2>Wall</h2>
<ul>
    <#list group.wall as post>
            <li>${post.text}</li>
    </#list>
</ul>
</#if>
</#macro>

<@display group.name/>