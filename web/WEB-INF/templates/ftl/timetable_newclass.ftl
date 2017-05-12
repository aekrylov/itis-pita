<#include 'base.ftl'>
<#macro body>
    <@formTags.form modelAttribute="form" method="post">
        <@formTags.input path="subject" />
        <@formTags.input path="teacherId" />
        <@formTags.select path="groups" multiple="true">
            <option>11-501</option>
            <option>11-502</option>
        </@formTags.select>

        <#list 0..(form.startTimes?size+1) as i>
            <input name="startTimes[${i}]" type="text" value="${form.startTimes[i]!""}">
            <input name="places[${i}]" type="text" value="${form.places[i]!""}">
        <br>
        </#list>
        <input type="submit">
    </@formTags.form>
</#macro>
<@display "Расписание - новый" />