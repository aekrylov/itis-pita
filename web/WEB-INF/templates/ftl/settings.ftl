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
    <script src="http://malsup.github.com/jquery.form.js"></script>
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
                <div id="alert_container">

                </div>
                <#if passwordError?has_content>
                    <#if passwordError.hasFieldErrors("old_password")>
                        <div class="alert alert-danger" role="alert">Неверный текущий пароль.</div>
                    <#else>
                        <#if passwordError.hasFieldErrors("new_password")>
                            <div class="alert alert-danger" role="alert">Новый пароль должен содержать от 8 до 30
                                символов.
                            </div>
                        <#else>
                            <div class="alert alert-danger" role="alert">Пароли не совпадают.</div>
                        </#if>
                    </#if>
                <#else>
                    <#if PasswordChangedSuccess?has_content>
                        <div class="alert alert-success" role="alert">Пароль изменен.</div>
                    </#if>
                </#if>

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
                        <input class="selectized" name="interests"
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
                            <@formTags.form method="POST" action="/ajax/profile/edit/change_password" modelAttribute="passwordForm"
                            id="passwordChangeForm"
                            cssClass="form-horizontal"
                            <#--onsubmit="return checkform(['password', 'old_password', 'repeated_password']) && passwordsMatcher()"-->>
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Смена пароля</h4>
                                </div>
                                <div class="modal-body">

                                    <div class="form-group">
                                        <label for="old_password" class="col-xs-3 control-label">Старый
                                            пароль</label>
                                        <div class="col-xs-6">
                                            <@formTags.password path="old_password" cssClass="form-control" id="old_password"/>
                                            <@formTags.errors path="old_password" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="new_password" class="col-xs-3 control-label">Новый
                                            пароль</label>
                                        <div class="col-xs-6">
                                            <@formTags.password path="new_password" cssClass="form-control" id="new_password"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password_confirmed" class="col-xs-3 control-label">Повторите
                                            пароль</label>
                                        <div class="col-xs-6">
                                            <input class="form-control" type="password" id="password_confirmed"
                                                   name="password_confirmed"/>
                                        </div>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена
                                    </button>
                                    <button class="btn-success btn" type="submit">Изменить пароль</button>
                                </div>
                                <script>
                                    <#-- script ajax-post form
                                    rewrited default onsubmit handlers, need to define their in options obj-->
                                    $(document).ready(function () {
                                        <#-- create options with pre-submit handlers and success callback-->
                                        var formToSend = $('#passwordChangeForm');
                                        var errorsMap = new Map();                                        
                                        errorsMap.set('new_password', "Новый пароль должен содержать от 8 до 30 символов.");
                                        errorsMap.set('old_password', "Старый пароль введен неверно.");
                                        var options = {
                                            beforeSubmit: function(arr, $form, options) {
                                                return checkform(['new_password', 'old_password', 'password_confirmed'])
                                                        && passwordsMatcher($('#new_password'),$('#password_confirmed'));
                                            },
                                            success: function(obj) {
                                                console.log('success');
                                <#--//TODO FIXME - wrong checker -->
                                                if (typeof obj.error_fields === 'undefined' || obj.error_fields.length === 0) {
                                                    //all right
                                                    console.log('OK');
                                                    formToSend.resetForm();
                                                    $('#change_password_modal').modal('toggle');
                                                } else {
                                                    //get name of wrong field
                                                    for (var i = 0; i < error_fields.length; i++) {
                                                        console.log('wrong');
                                                        var field = formToSend.children('input[name="' + error_fields[i] + '"]');
                                                        if ( !field.parent().hasClass("has-error")) {
                                                            console.log('appending warinings');
                                                            field.parent().append('\<label class="control-label" id ="'+field.attr('id')+'\_warning" for="'+field.id + '\ ">' + errorsMap.get(error_fields[i]) + '\</label>');
                                                            field.parent().addClass("has-error");
                                                        }
                                                    <#-- TODO FIXME teachers list - focus handler working strange + to add improved scripts to frontend-pages -->
                                                        //field.focus(function(){removeHasError(field.attr('id'))});
                                                    }
                                                }
                                            }
                                        };
                                        <#-- bind 'passwordChangeForm' and provide a simple callback function-->
                                        formToSend.ajaxForm(options);
                                    });
                                </script>
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
                {value: '${i.name}', text: '${i.name}'},
            </#list>
        </#if>];


    $.onload = simpleTagsSelectizer('interests', items);
</script>
</#macro>
<@display "Настройки" />