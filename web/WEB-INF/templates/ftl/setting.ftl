<#ftl encoding='utf-8'>
<#include 'base.ftl'>
<#macro header_custom_imports>
<head>
    <meta charset="UTF-8">
    <title>Настройки</title>
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="/static/formhelper/css/bootstrap-formhelpers.css" rel="stylesheet" media="screen">
</head>
</#macro>
<#macro body>
<body>

<div class="row">
    <div class="col-xs-offset-2 col-xs-8">
        <h1 class="page-header"> Настройки </h1>
    </div>
</div>
<!-- /.row -->


<div class="row">
    <div class="col-xs-offset-2 col-xs-8">
        <form role="form" class="form-horizontal" action="/profile/edit" method="POST"
              onsubmit="return checkform(['email','phone'])">
            <div class="form-group">
                <label class="col-xs-3 control-label">ФИО</label>

                <div class="col-xs-6" style="margin-top: 7px">
                ${name}
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Обо мне</label>
                <div class="col-xs-6">
                        <textarea class="form-control" rows="3" name="about_me" id="about_me"><#if interset?has_content>${info}</#if>
                    </textarea>
                </div>

            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Интересы</label>
                <div class="col-xs-6">
                    <textarea class="form-control" rows="3" name="interests" id="interests"><#if interset?has_content>${interests}</#if></textarea>
                </div>

            </div>
            <div class="form-group">
                <label for="email" class="col-xs-3 control-label">Номер телефона</label>

                <div class="col-xs-6">
                    <input type="text" name="phone" id="phone" class="form-control bfh-phone" data-format="+7 (ddd) ddd-dddd" value="${phone_number}">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-xs-3 control-label">Email</label>

                <div class="col-xs-6">
                    <input type="email" class="form-control" name="email"
                           id="email" maxlength="50" oninput="checkEmail()"
                           placeholder="${email}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-6 col-xs-offset-3">
                    <button class="btn-success btn btn-block" type="submit">Сохранить</button>
                </div>
            </div>
        </form>
        <div class="row">
            <div class="col-xs-offset-3 col-xs-6">
                <a class="btn btn-primary btn-block" data-toggle="modal" data-target="#change_password_modal"> Сменить
                    пароль</a>
            </div>
        </div>
        <div class="modal fade" id="change_password_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form class="form-horizontal" action="/settings" method="POST"
                          onsubmit="return checkform(['password', 'old_password', 'repeated_password']) && passwordsMatcher()">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                        </div>
                        <div class="modal-body">

                            <div class="form-group">
                                <label for="password" class="col-xs-3 control-label">Старый пароль</label>

                                <div class="col-xs-6">
                                    <input type="password" class="form-control" name="old_password"
                                           id="old_password" maxlength="50" oninput="removeHasError('old_password')"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-xs-3 control-label">Новый пароль</label>

                                <div class="col-xs-6">
                                    <input type="password" class="form-control" name="password"
                                           id="password" maxlength="50" oninput="removeHasError('password')"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="repeated_password" class="col-xs-3 control-label">Повторите пароль</label>

                                <div class="col-xs-6">
                                    <input type="password" class="form-control" name="password"
                                           id="repeated_password" maxlength="50" oninput="removeHasError('repeated_password')"/>
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                            <button class="btn-success btn" type="submit">Изменить пароль</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</#macro>
<#macro footer_custom_imports>
<hr>
<script src="/static/js/jquery-3.1.1.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script src="/static/formhelper/js/bootstrap-formhelpers.js"></script>
<script src="/static/formhelper/js/bootstrap-formhelper-phone.js"></script>
<script src="/static/js/passwordsMatcher.js"></script>
<script src="/static/js/checkform.js"></script>
</#macro>
</html>
<@display "Настройки" />