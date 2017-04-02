<#ftl encoding='utf-8'>
<#include 'base.ftl' >
<#macro header_custom_imports>

<link href="/static/bootstrap-fileinputs/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
<link href="/static/selectize/css/selectize.default.css" media="all" rel="stylesheet" type="text/css"/>
<link href="/static/formhelper/css/bootstrap-formhelpers.css" media="all" rel="stylesheet" type="text/css"/>

<script src="/static/bootstrap-fileinputs/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
<!-- sortable.min.js is only needed if you wish to sort / rearrange files in initial preview.
     This must be loaded before fileinput.min.js -->
<script src="/static/bootstrap-fileinputs/js/plugins/sortable.min.js" type="text/javascript"></script>
<!-- purify.min.js is only needed if you wish to purify HTML content in your preview for HTML files.
     This must be loaded before fileinput.min.js -->
<script src="/static/bootstrap-fileinputs/js/plugins/purify.min.js" type="text/javascript"></script>
<script src="/static/bootstrap-fileinputs/js/locales/fa.js"></script>
<!-- the main fileinput plugin file -->
<script src="/static/bootstrap-fileinputs/js/fileinput.min.js"></script>
<script src="/static/selectize/js/standalone/selectize.js"></script>
</#macro>

<#macro body>
<div class="undercover">
    <div class="row">
        <div class="col-md-offset-1 col-md-9">
            <h1 class="page-header"> Создание курса </h1>
        </div>
    </div>
    <!-- /.row -->

    <form role="form" class="" action="/courses/create" method="POST" enctype="multipart/form-data" onsubmit="return checkform([
            'name', 'description', 'course_teacher_search', 'schedule'
        ])">
        <div class="row">
            <div class="col-xs-offset-1 col-md-7">

                <div class="form-group">
                    <label class="control-label" for="name">Название курса</label>
                    <input onfocus="removeHasError('name')" type="text" minlength="3" class="form-control"
                           name="name"
                           id="name"
                           placeholder="..введите название курса"/>

                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class=" control-label">Информация о курсе</label>
                            <textarea class="form-control" style="resize: none"
                                      onfocus="removeHasError('description')" rows="6" name="description"
                                      id="description"></textarea>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class=" control-label">Расписание</label>
                            <textarea class="form-control" style="resize: none"
                                      onfocus="removeHasError('schedule')" rows="6" name="description"
                                      id="schedule"></textarea>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group" onclick="removeHasError('course_teacher_search')">
                            <label class=" control-label"><span
                                    class="glyphicon glyphicon-search"></span> Преподаватель </label>

                            <input type="text" id="course_teacher_search" class="selectized"
                                   tabindex="-1" style="display: none;">


                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group"  onclick="removeHasError('capacity')">
                            <label class=" control-label">Количеcтво мест </label>
                            <input type="number" class="form-control bfh-number" id="capacity"  name="capacity"
                                   data-min=1 data-max=1000>
                        </div>
                    </div>
                </div>


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
<script src="/static/js/checkform.js"></script>
<script src="/static/js/simpleTagsSelectizer.js"></script>
<script type="application/javascript">
    //see simpleTagsSelectizer for more information about items
    items = [
        {value:'1', text:'Иванов Иван Иванович'},
        {value:'2', text:'Петров Петр Петрович'},
        {value:'3', text:'Иванов Захар Иванович'},
        {value:'4', text:'Иванов Алексей Иванович'}
    ];
    $.onload = simpleTagsSelectizer('course_teacher_search',items);
</script>
<script src="/static/js/removeHasError.js"></script>
<script src="/static/formhelper/js/bootstrap-formhelpers.js"></script>
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
<@display "Создать курс"></@display>