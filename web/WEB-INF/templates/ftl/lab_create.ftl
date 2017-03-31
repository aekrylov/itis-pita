<#include 'base.ftl' >
<link href="../../../css/creating_lab.css" rel="stylesheet" type="text/css"/>
<link href="../../../css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>

<#macro body>
        <!--<form method="post" action="/labs/create" enctype="multipart/form-data">
            <input name="name"/>
            <textarea name="description"></textarea>

            <input name="image" type="file"/>

            <#-- TODO teachers list -->

            <input type="submit">
        </form>-->
<div class="undercover container">
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <h1 class="page-header"> Создание новой лаборатории </h1>
        </div>
    </div>
    <!-- /.row -->
    <form method="post" action="/labs/create" enctype="multipart/form-data" onsubmit="return checkform([
            'lab_name', 'description', 'lab_img'])">
        <div class="row">
            <div class="col-xs-offset-2 col-md-8">
                <div class="form-group">
                    <label class="control-label" for="lab_name">Название лаборатории</label>
                    <input required type="text" minlength="3" class="form-control" name="name"
                           id="lab_name"
                           placeholder="..введите название курса"/>
                </div>
                <div class="form-group">
                    <label class="control-label" for="description">Информация о лаборатории</label>
                    <textarea class="form-control" style="resize: none" required rows="6" name="description"
                              id="description"></textarea>
                </div>
                <div class="form-group">
                    <label class="control-label">Логотип</label>
                    <input required name="image" type="file" id="lab_img"/>
                    <script>
                        $("#lab_img").fileinput({
                            theme: "fa",
                            allowedFileExtensions: ["jpg", "png", "gif"],
                            minImageWidth: 50,
                            minImageHeight: 50,
                            showUpload: false
                        });
                    </script>
                </div>
                <div class="form-group">
                    <label class="control-label">Выберите преподавателей</label>
                    <div class="checkbox">
                        <label><input type="checkbox" value="">ФИО преподавателя 1</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" value="">ФИО преподавателя 2</label>
                    </div>
                </div>
                <div class="form-group">
                    <input class="btn-warning btn btn-block" type="submit" placeholder="Создать"/>
                </div>
            </div>
        </div>
    </form>
</div>
</#macro>

<@display "Создать лабораторию"></@display>