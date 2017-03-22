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

</#macro>

<@display "Hello"></@display>