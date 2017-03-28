<#include 'base.ftl' >

<#macro body>
<form method="post" action="/events/create" enctype="multipart/form-data">
    <input id="name" name="name" required/>
    <textarea id="description" name="description"></textarea>
    <input id="place" name="place" required/>
    <input id="date" name="date" required/>
    <input id="members" name="members"/>
    <#-- число участников -->
    <input  id="image" name="image" type="file">
    <input type="submit">
</form>
</#macro>

<@display "Создать событие"></@display>