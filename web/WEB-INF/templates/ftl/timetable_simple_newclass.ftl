<#include 'base.ftl'>
<#macro header_custom_imports>
<link href="/static/css/timetable.css" rel="stylesheet" type="text/css"/>
<link href="/static/css/select2.css" rel="stylesheet" type="text/css"/>
<script src="/static/js/select2.js"></script>
<script src="/static/js/select2-localization/ru.js"></script>
</#macro>
<#macro body>
<div class="container">
    <div class="row">
        <div class="col-lg-12 timetable-header">
            <div class="timetable-header-text">
                Новый элемент расписания
            </div>
        </div>
        <hr class="col-lg-12 timetable-line">
        <@formTags.form modelAttribute="simpleForm" method="post" onsubmit="return checkform([
            'timetable_name', 'timetable_groups', 'timetable_teacher', 'timetable_time', 'timetable_place'
        ], false)">
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <label class="control-label" for="timetable_name">Название предмета</label>
                        <@formTags.input path="subject" onfocus="removeHasError('timetable_name')" type="text" minlength="3"
                        cssClass="form-control"
                        name="timetable_name"
                        id="timetable_name" />
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="timetable_groups">Группа</label>
                    <@formTags.select path="groups" onfocus="removeHasError('timetable_groups')"
                    ccsClass="form-control"
                    multiple="multiple"
                    name="timetable_groups"
                    id="timetable_groups"
                    required="required">
                        <@formTags.options items=academicGroups />
                    </@formTags.select>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="timetable_teacher">Преподаватель</label>
                        <@formTags.select path="teacherId"
                        onfocus="removeHasError('timetable_teacher')"
                        cssClass="form-control"
                        name="timetable_teacher"
                        id="timetable_teacher"
                        required="required">
                            <@formTags.options items=teachers />
                        </@formTags.select>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="timetable_time">Время</label>
                        <@formTags.select path="timeStart"
                        onfocus="removeHasError('timetable_time')"
                        cssClass="form-control"
                        name="timetable_time"
                        id="timetable_time"
                        required="required">
                            <@formTags.options items=lessonTimes />
                        </@formTags.select>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="timetable_place">Расположение</label>
                        <@formTags.input path="place"
                        cssClass="form-control"
                        onfocus="removeHasError('timetable_place')"
                        name="timetable_place"
                        id="timetable_place"/>
                    </div>
                </div>
                <div class="col-lg-6">
                    <label class="control-label">День недели</label>
                    <div class="row">
                        <div class="col-lg-6 timetable-day">
                            <@formTags.checkboxes path="daysOfWeek" items=daysOfWeek/>
                        </div>
                    </div>
                    <small class="timetable-day-warning">Выберите хотя бы 1 день</small>
                </div>
            </div>
            <input class="timetable-button-submit" type="submit" disabled value="Сохранить">
        </@formTags.form>
    </div>
</div>
<script src="/static/js/checkform.js"></script>
<script src="/static/js/removeHasError.js"></script>
<script>
    var checkboxes = $("input[type='checkbox']"),
            submitButt = $("input[type='submit']");
    checkboxes.click(function() {
        submitButt.attr("disabled", !checkboxes.is(":checked"));
        if (!checkboxes.is(":checked")){
            $(".timetable-day-warning").text("Выберите хотя бы 1 день");
        }
        else {
            $(".timetable-day-warning").text("");
        }
    });
</script>
<script type="text/javascript">
    $("#timetable_groups").select2({
        language: "ru"
    });
</script>
</#macro>

<@display "Расписание - новое" />