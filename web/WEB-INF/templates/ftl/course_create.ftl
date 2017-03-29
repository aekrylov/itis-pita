<#include 'base.ftl' >

<#macro body>
<form method="post" action="/course/create" enctype="multipart/form-data">
    <input name="name"/>
    <textarea name="description"></textarea>

    <textarea name="schedule"></textarea>

    <input name="image" type="file"/>
    <input name="capacity" type="number">
    <input type="submit">
</form>
</#macro>

<@display "Создать лабораторию"></@display>