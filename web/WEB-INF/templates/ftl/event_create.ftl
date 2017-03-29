<#include 'base.ftl' >

<#macro body>
<script type="text/javascript" src="static/js/event-creation/moment-with-locales.min.js"></script>
<script type="text/javascript" src="static/js/event-creation/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="static/css/event-creation/bootstrap-datetimepicker.min.css" />

<script type="text/javascript">
    $(function () {
        $('#date').datetimepicker({
            language: 'ru',
            minDate: moment()
        });
    });
</script>
<form method="post" action="/events/create" enctype="multipart/form-data">
    <div class="form-group">
        <label for="name">Название</label>
        <input class="form-control" id="name" name="name" required/>
    </div>
    <div class="form-group">
        <label for="description">Описание события</label>
        <textarea class="form-control" id="description" name="description"></textarea>
    </div>
    <div class="form-group">
        <label for="place">Место события</label>
        <input class="form-control" id="place" name="place" required/>
    </div>
    <div class="form-group">
        <label for="date">Дата проведения</label>
        <input type="text" class="form-control" id="date" name="date" required/>
    </div>
    <div class="form-group">
        <label for="members">Количество участников</label>
        <input class="form-control" id="members" pattern="^[ 0-9]+$" name="members"/>
    </div>
    <div class="form-group">
        <label for="image">Изображение</label>
        <input id="image" name="image" type="file">
    </div>
    <button style="float:right; margin-right:20%" type="submit" class="btn btn-primary">Создать</button>
</form>
</#macro>

<@display "Создать событие"></@display>