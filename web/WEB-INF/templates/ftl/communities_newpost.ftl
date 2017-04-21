<#include 'base.ftl'>

<#macro body>
    <@formTags.form method="post" modelAttribute="form" enctype="multipart/form-data">
        <div class="form-group">
            <@formTags.label path="text">Text: </@formTags.label>
            <@formTags.textarea path="text" cssClass="form-control" cssErrorClass="form-control has-error"></@formTags.textarea>
        </div>
        <div class="form-group">
            <@formTags.label path="videoLink">Video link (optional): </@formTags.label>
            <@formTags.input path="videoLink" cssClass="form-control" />
        </div>
    <div class="form-group">
        <@formTags.input path="image" type="file"/>
    </div>
        <input type="submit" value="create">
    </@formTags.form>
</#macro>

<@display "New post" />