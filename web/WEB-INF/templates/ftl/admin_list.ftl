<#ftl encoding='utf-8'>
<#include 'base.ftl' >

<#macro body>
    <table class="table">
        
        <thead>
            <#list fields as field>
            <th>${field}</th>
            </#list>
            <th>Edit</th>
            <th>Delete</th>
        </thead>
        <tbody>
            <#list entries as entry>
            <tr>
                <#list fields as field>
                <td>${(entry[field]!"")?is_sequence?then(entry[field]?join(", "), entry[field]!"")}</td>
                </#list>
                <td><a href="edit?id=${entry.id}">Edit</a> </td>
                <td><a href="delete?id=${entry.id}">Delete</a> </td>
            </tr>
            </#list>

        </tbody>
    </table>
</#macro>

<@display "admin"></@display>