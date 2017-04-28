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
                <#if error?has_content>
                    <div class="alert alert-danger" role="alert">Пароль должен содержать от 8 до 30 символов</div>
                </#if>
                <#if PasswordChangedSuccess?has_content>
                    <#if PasswordChangedSuccess>
                        <div class="alert alert-success" role="alert">Пароль изменен.</div>
                    <#else>
                        <div class="alert alert-danger" role="alert">Неверный текущий пароль.</div>
                    </#if>
                </#if>
                <form role="form" action="/profile/edit" method="POST"
                      onsubmit="return checkform(['email']) && checkEmail()">
                    <div class="form-group">
                        <label class="control-label">ФИО</label>
                        <p class="form-control form-control-static"><#if name?has_content>${name}</#if></p>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Обо мне</label>
                        <textarea class="form-control" rows="3" name="about_me"
                                  id="about_me"><#if info?has_content>${info}</#if></textarea>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Интересы</label>
                        <input class="selectized"  name="interests"
                                  id="interests" value="<#if interset?has_content>${interests}</#if>"/>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label">Номер телефона</label>
                        <input type="text" class="form-control bfh-phone" data-format="+7 (ddd) ddd-dddd"
                               value="<#if phone_number?has_content>${phone_number}</#if>">
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label">Email</label>
                        <input type="email" class="form-control" name="email"
                               id="email" maxlength="50" oninput="checkEmail()"
                               value="<#if email?has_content>${email}</#if>"/>
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
                            <form class="form-horizontal" action="/profile/edit/password" method="POST"
                                  onsubmit="return checkform(['password', 'old_password', 'repeated_password']) && passwordsMatcher()">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Смена пароля</h4>
                                </div>
                                <div class="modal-body">

                                    <div class="form-group">
                                        <label for="password" class="col-xs-3 control-label">Старый
                                            пароль</label>

                                        <div class="col-xs-6">
                                            <input type="password" class="form-control" name="old_password"
                                                   id="old_password" maxlength="50"
                                                   oninput="removeHasError('old_password')"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-xs-3 control-label">Новый
                                            пароль</label>

                                        <div class="col-xs-6">
                                            <input type="password" class="form-control" name="password"
                                                   id="password" maxlength="50"
                                                   oninput="removeHasError('password')"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="repeated_password" class="col-xs-3 control-label">Повторите
                                            пароль</label>

                                        <div class="col-xs-6">
                                            <input type="password" class="form-control" name="password"
                                                   id="repeated_password" maxlength="50"
                                                   oninput="removeHasError('repeated_password')"/>
                                        </div>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена
                                    </button>
                                    <button class="btn-success btn" type="submit">Изменить пароль</button>
                                </div>
                            </form>
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