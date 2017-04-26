<#ftl encoding='utf-8'>
<#include 'base.ftl' >
<#macro header_custom_imports>

<link href="/static/bootstrap-fileinputs/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
<link href="/static/selectize/css/selectize.default.css" media="all" rel="stylesheet" type="text/css"/>
<link href="/static/formhelper/css/bootstrap-formhelpers.css" media="all" rel="stylesheet" type="text/css"/>
<link href="/static/css/communities-create.css" media="all" rel="stylesheet" type="text/css"/>
<link href="/static/css/bootstrap-datetimepicker.min.css" media="all" rel="stylesheet" type="text/css"/>


<!--for uploading pic-->
<script src="/static/bootstrap-fileinputs/js/plugins/purify.min.js" type="text/javascript"></script>
<script src="/static/bootstrap-fileinputs/js/locales/fa.js"></script>
<!-- the main fileinput plugin file -->
<script src="/static/bootstrap-fileinputs/js/fileinput.min.js"></script>
<!--for tags-->
<script src="/static/selectize/js/standalone/selectize.js"></script>
<script src="/static/js/simpleTagsSelectizer.js"></script>
<!--for forms-->

<script src="/static/js/moment-with-locales.min.js"></script>
<script src="/static/js/checkform.js"></script>
<script src="/static/js/removeHasError.js"></script>
<script src="/static/formhelper/js/bootstrap-formhelpers.js"></script>
<script src="/static/formhelper/js/bootstrap-datetimepicker.min.js"></script>
</#macro>

<#macro body>
<div class="undercover">
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-12 green-header">
                <div class="col-md-offset-1 col-md-9">
                    <h1> Создание  <#switch type><#case "COURSE">курса<#break><#case "GROUP">группы<#break><#case "LAB">
                        лаборатории<#break><#case "EVENT">события<#break></#switch></h1>
                </div>
            </div>
        </div>
    </div>

    <br/>
    <!-- /.row -->

    <form role="form" class="" method="POST" enctype="multipart/form-data" onsubmit="return checkform([
            'name', 'description',
        <#switch type>
            <#case "COURSE">
                    'shedule',
                    'teacher_search'
                <#break>
            <#case "GROUP">
                    'group_tags'
                <#break>
            <#case "LAB">
                    'teacher_search'
                <#break>
            <#case "EVENT">
                    'date',
                    'place'
                <#break>
        </#switch>
            ])">
        <div class="row">
            <div class="col-xs-offset-1 col-md-7">

                <div class="form-group">
                    <label class="control-label" for="name">Название</label>
                    <input onfocus="removeHasError('name')" type="text" minlength="3" class="form-control"
                           name="name"
                           id="name"
                           value="${form.name!""}"
                           placeholder="..введите название <#switch type><#case "COURSE">курса<#break><#case "GROUP">группы<#break><#case "LAB">лаборатории<#break><#case "EVENT">события<#break></#switch>"/>

                </div>

                <#switch type>
                    <#case "GROUP">
                        <!--simly block fields-->
                        <div class="form-group">
                            <label class=" control-label">Информация</label>
                            <textarea class="form-control" style="resize: none"
                                      onfocus="removeHasError('description')" rows="4" name="description"
                                      id="description">${form.description!""}</textarea>
                        </div>
                        <div class="form-group" onclick="removeHasError('group_tags')">
                            <label class=" control-label">Темы группы</label>
                            <input value="${form.interests!""}" type="text" id="group_tags" class="selectized"
                                   name="interests"
                                   tabindex="-1" style="display: none;">
                        </div>
                        <script type="application/javascript">
                            //see simpleTagsSelectizer for more information about items
                            items = [
                                <#if interests??>
                                    <#list interests as i>
                                        {value: '${i.name}', text: '${i.name}'},
                                    </#list>
                                </#if>];
                            $.onload = simpleTagsSelectizer('group_tags', items);
                        </script>
                        <#break>
                    <#case "LAB">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class=" control-label">Информация</label>
                                    <textarea class="form-control" style="resize: none"
                                              onfocus="removeHasError('description')" rows="4" name="description"
                                              id="description">${form.description!""}</textarea>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group" onclick="removeHasError('teacher_search')">
                                    <label class=" control-label"><span
                                            class="glyphicon glyphicon-search"></span> Преподаватели </label>

                                    <input type="text" id="teacher_search" class="selectized" name="teachers"
                                           tabindex="-1" style="display: none;">
                                    <script type="application/javascript">
                                        //see simpleTagsSelectizer for more information about items
                                        items = [
                                            <#list names as name>
                                                {value: '${name.name}', text: '${name.name}'},
                                            <#else>
                                            </#list>];
                                        $.onload = simpleTagsSelectizer('teacher_search', items);
                                    </script>
                                </div>
                            </div>
                        </div>
                        <#break>
                    <#case "COURSE">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class=" control-label">Информация</label>
                                    <textarea class="form-control" style="resize: none"
                                              onfocus="removeHasError('description')" rows="6" name="description"
                                              id="description">${form.description!""}</textarea>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class=" control-label">Расписание</label>
                                    <textarea class="form-control" style="resize: none"
                                              onfocus="removeHasError('schedule')" rows="6" name="schedule"
                                              id="schedule">${form.schedule!""}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group" onclick="removeHasError('teacher_search')">
                                    <label class=" control-label"><span
                                            class="glyphicon glyphicon-search"></span> Преподаватели </label>

                                    <input type="text" id="teacher_search" class="selectized" name="teachers"
                                           tabindex="-1" style="display: none;">
                                    <script type="application/javascript">
                                        //see simpleTagsSelectizer for more information about items
                                        items = [
                                            <#list names as name>
                                                {value: '${name.name}', text: '${name.name}'},
                                            <#else>
                                            </#list>];
                                        $.onload = simpleTagsSelectizer('teacher_search', items);
                                    </script>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group" onclick="removeHasError('capacity')">
                                    <label class=" control-label">Количеcтво мест </label>
                                    <input type="number" class="form-control bfh-number" id="capacity" name="capacity"
                                           data-min=1 value="${form.capacity!""}" data-max=1000>
                                </div>
                            </div>
                        </div>

                        <#break>
                    <#case "EVENT">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class=" control-label">Информация</label>
                                    <textarea class="form-control" style="resize: none"
                                              onfocus="removeHasError('description')" rows="8" name="description"
                                              maxlength="2000"
                                              id="description">${form.description!""}</textarea>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label" for="place">Место события</label>
                                    <input class="form-control" type="text" value="${form.place!""}" id="place"
                                           name="place" maxlength="100"
                                           onfocus="removeHasError('place')"/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="date">Дата и время проведения</label>
                                    <input type="text" class="form-control" id="date" name="date"
                                           value="${form.date!""}"
                                           onfocus="removeHasError('date')" readonly/>
                                    <script type="application/javascript">
                                        $(function () {
                                            $('#date').datetimepicker({
                                                format: "dd.mm.yyyy - hh:ii",
                                                startDate: moment().format("YYYY-MM-DD HH:mm"),
                                                autoclose: true,
                                                todayBtn: true,
                                                pickerPosition: "top-left",
                                                weekStart: 1
                                            });
                                        });
                                    </script>


                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="capacity">Количество участников</label>
                                    <input type="number" class="form-control bfh-number" id="capacity" name="capacity"
                                           data-min=1 value="${form.capacity!""}" data-max=1000>
                                </div>
                            </div>
                        </div>
                        <#break>
                </#switch>
                <div class="form-group">
                    <button class="btn-warning btn btn-block" type="submit">Создать</button>
                </div>
            </div>

            <div class="col-md-3">
                <div class="form-group">
                    <label class=" control-label">Изображение</label>
                    <input id="image" name="image" type="file"
                           onclick="removeHasError('image')" class="file-loading" accept="image/*">
                    <script>
                        $("#image").fileinput({
                            theme: "fa",
                            allowedFileExtensions: ["jpg", "png", "gif"],
                            minImageWidth: 50,
                            minImageHeight: 50,
                            showUpload: false
                        });
                    </script>
                </div>
            </div>
        </div>
    </form>
</div>


</#macro>

<#macro footer_custom_imports>

</#macro>

<#--<#macro body>
<form method="post" action="/course/create" enctype="multipart/form-data">
    <input name="name"/>
    <textarea name="description"></textarea>

    <textarea name="schedule"></textarea>

    <input name="image" type="file"/>
    <input name="capacity" type="number">
    <input type="submit">
</form>
</#macro>
-->
<@display "Создать сообщество"></@display>