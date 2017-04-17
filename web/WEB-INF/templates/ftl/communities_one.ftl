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
</#macro>

<@display group.name/>