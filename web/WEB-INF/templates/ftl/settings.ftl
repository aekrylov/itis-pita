<#ftl encoding='utf-8'>
<#include 'base.ftl'>
<#macro header_custom_imports>
<head>
    <meta charset="UTF-8">
    <title>Настройки</title>
    <link href="/static/formhelper/css/bootstrap-formhelpers.css" rel="stylesheet" media="screen">
    <link href="/static/selectize/css/selectize.default.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="/static/css/profile.css" media="all" rel="stylesheet" type="text/css"/>

    <script src="/static/formhelper/js/bootstrap-formhelpers.js"></script>
    <script src="/static/formhelper/js/bootstrap-formhelper-phone.js"></script>
    <script src="/static/js/passwordsMatcher.js"></script>
    <script src="/static/js/ajax-email-checker.js"></script>
    <script src="/static/js/checkform.js"></script>
    <script src="/static/js/removeHasError.js"></script>
    <script src="/static/selectize/js/standalone/selectize.js"></script>
    <script src="/static/js/simpleTagsSelectizer.js"></script>
</head>
</#macro>
<#macro body>
<div class="row ">
    <div class="col-md-offset-3 col-md-6 undercover">
        <div class="row">
            <div class="col-md-12 green-header">
                <div class="row">
                    <div class="col-md-8">
                        <h2>Настройки</h2>
                    </div>
                </div>

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <br/>

                <form role="form" action="/profile/edit" method="POST"
                      onsubmit="return checkform(['email']) && checkEmail()">
                    <div class="form-group">
                        <label class="control-label">ФИО</label>
                        <p class="form-control form-control-static">${form.name}</p>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Обо мне</label>
                        <textarea class="form-control" rows="3" name="about_me"
                                  id="about_me">${form.about_me!""}</textarea>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Интересы</label>
                        <input class="selectized"  name="interests"
                                  id="interests" value="${form.interests!""}"/>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label">Номер телефона</label>
                        <input type="text" class="form-control bfh-phone" data-format="+7 (ddd) ddd-dddd"
                               value="${form.phone}" name="phone">
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label">Email</label>
                        <input type="email" class="form-control" name="email"
                               id="email" maxlength="50" oninput="checkEmail()"
                               value="${form.email}"/>
                    </div>
                    <div class="form-group">
                        <button class="btn-warning btn btn-block" type="submit">Сохранить</button>
                    </div>
                </form>


                <a class="btn btn-primary btn-block" data-toggle="modal"
                   data-target="#change_password_modal"> Сменить
                    пароль</a>

                <div class="modal fade" id="change_password_modal" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <@formTags.form method="POST" action="/profile/edit/change_password" class="form-horizontal" modelAttribute="passwordForm">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                </div>
                                <div class="modal-body">

                                    <div class="form-group">
                                        <label for="old_password" class="col-xs-3 control-label">Старый
                                            пароль</label>
                                        <div class="col-xs-6">
                                            <@formTags.input path="old_password" cssClass="form-control" />
                                            <@formTags.errors path="old_password" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="new_password" class="col-xs-3 control-label">Новый
                                            пароль</label>
                                        <div class="col-xs-6">
                                            <@formTags.password path="new_password" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password_confirmed" class="col-xs-3 control-label">Повторите
                                            пароль</label>
                                        <div class="col-xs-6">
                                            <input class="form-control" type="password" id="password_confirmed" name="password_confirmed"/>
                                        </div>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена
                                    </button>
                                    <button class="btn-success btn" type="submit">Изменить пароль</button>
                                </div>
                                <@formTags.errors path="*"/>
                            </@formTags.form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</#macro>
<#macro footer_custom_imports>
<script type="application/javascript">
    //see groupTagsSelectizer for more information about items
    items = [
        <#if all_interests??>
            <#list all_interests as i>
                { value: '${i.name}', text: '${i.name}' },
            </#list>
        </#if>
    ];


    $.onload = simpleTagsSelectizer('interests', items);
</script>
</#macro>
<@display "Настройки" />