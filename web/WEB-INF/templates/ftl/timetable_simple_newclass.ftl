<#include 'base.ftl'>
<#macro body>
    <@formTags.form modelAttribute="simpleForm" method="post">
    subject
        <@formTags.input path="subject" />
    groups
        <@formTags.select path="groups" multiple="true">
                <option>11-501</option>
                <option>11-502</option>
        </@formTags.select>
    teacher id (62)
        <@formTags.input path="teacherId" />
    timestart
        <@formTags.select path="timeStart">
            <@formTags.options items=lessonTimes />
        </@formTags.select>
    place
    <@formTags.input path="place" />
    day of week
    <@formTags.checkboxes path="daysOfWeek" items=daysOfWeek/>
    <input type="submit">
    </@formTags.form>
</#macro>

<@display "Расписание - новое" />