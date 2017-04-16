<#include 'base.ftl'>
<#macro body>
<table>
    <thead>
    <th>Id</th>
    <th>email</th>
    </thead>
    <tbody>
    <tr>
        <td>${user.id}</td>
        <td>${user.email}</td>
    </tr>
    </tbody>
</table>

<ul>
    <#list .data_model?keys as key>
    <li>${key}</li>
    </#list>
</ul>

<@security.authorize access="isAnonymous()">
        anonymous
</@security.authorize>


</#macro>

<@display "Hello"></@display>