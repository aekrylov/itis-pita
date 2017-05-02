<#include 'base.ftl'>
<#macro body>
    <@formTags.form modelAttribute="createForm" method="post">
        <@formTags.input path="subjectId" />
        <@formTags.input path="teacherId" />
        <@formTags.select path="academicGroups" multiple="true">
            <option>11-501</option>
            <option>11-502</option>
        </@formTags.select>
        <input type="submit">
    </@formTags.form>
</#macro>
<@display "Расписание - новый" />