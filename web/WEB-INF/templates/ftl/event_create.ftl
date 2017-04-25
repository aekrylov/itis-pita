<#ftl encoding='utf-8'>
<#include 'base.ftl' >

<#macro header_custom_imports>
<script type="text/javascript" src="/static/js/moment-with-locales.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="/static/js/bootstrap-datetimepicker.ru.js" charset="UTF-8"></script>

<script type="text/javascript" src="/static/js/simpleTagsSelectizer.js"></script>
<script type="text/javascript" src="/static/js/removeHasError.js"></script>
<script type="text/javascript" src="/static/js/checkform.js"></script>

<script type="text/javascript" src="/static/formhelper/js/bootstrap-formhelpers.js"></script>
<script src="/static/bootstrap-fileinputs/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
<!-- sortable.min.js is only needed if you wish to sort / rearrange files in initial preview.
     This must be loaded before fileinput.min.js -->
<script src="/static/bootstrap-fileinputs/js/plugins/sortable.min.js" type="text/javascript"></script>
<!-- purify.min.js is only needed if you wish to purify HTML content in your preview for HTML files.
     This must be loaded before fileinput.min.js -->
<script src="/static/bootstrap-fileinputs/js/plugins/purify.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/static/bootstrap-fileinputs/js/locales/fa.js"></script>
<!-- the main fileinput plugin file -->
<script type="text/javascript" src="/static/bootstrap-fileinputs/js/fileinput.min.js"></script>

<link href="/static/bootstrap-fileinputs/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
<link href="/static/selectize/css/selectize.default.css" media="all" rel="stylesheet" type="text/css"/>
<link href="/static/formhelper/css/bootstrap-formhelpers.css" media="all" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="/static/css/bootstrap-datetimepicker.min.css" media="screen"/>
</#macro>

<#macro body>
<div class="undercover">
    <div class="row">
        <div class="col-md-offset-1 col-md-9">
            <h1 class="page-header"> Создание события </h1>
        </div>
    </div>
    <form method="post" action="/events/create" enctype="multipart/form-data" onsubmit="return checkform([
            'name', 'place', 'date'])">
        <div class="row">
            <div class="col-xs-offset-1 col-md-7">
                <div class="form-group">
                    <label class="control-label" for="name">Название</label>
                    <input class="form-control" id="name" name="name" onfocus="removeHasError('name')"/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="description">Описание</label>
                    <textarea class="form-control" id="description" name="description"></textarea>
                </div>
                <div class="form-group">
                    <label class="control-label" for="place">Место события</label>
                    <input class="form-control" id="place" name="place" onfocus="removeHasError('place')"/>
                </div>
                <div class="form-group">
                    <#-- TODO incorrect date format -->
                    <label class="control-label" for="date">Дата и время проведения</label>
                    <input type="text" class="form-control" id="date" name="date" onfocus="removeHasError('date')" readonly/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="capacity">Количество участников</label>
                    <input type="number" class="form-control bfh-number" id="capacity" name="capacity"
                           data-min=0 data-max=1000>
                </div>
                <div class="form-group">
                    <button class="btn-warning btn btn-block" type="submit">Создать</button>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label class=" control-label">Изображение</label>
                    <input id="image" name="image" type="file" class="file-loading" accept="image/*">
                    <script>
                        $("#image").fileinput({
                            theme: "fa",
                            allowedFileExtensions: ["jpg", "jpeg", "png", "gif"],
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

<script type="text/javascript">
    $(function () {
        $('#date').datetimepicker({
            format: "dd mm yyyy - hh:ii",
            startDate: moment().format("YYYY-MM-DD HH:mm"),
            autoclose: true,
            todayBtn: true,
            pickerPosition: "top-left"
        });
    });
</script>
</#macro>

<@display "Создать событие"></@display>