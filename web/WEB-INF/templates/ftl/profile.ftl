<#ftl encoding='utf-8'>
<#include 'base.ftl' >

<#macro header_custom_imports>

<link href="/static/selectize/css/selectize.default.css" media="all" rel="stylesheet" type="text/css"/>
<link href="/static/css/profile.css" media="all" rel="stylesheet" type="text/css"/>

</#macro>

<#macro body>
<div class="row ">
    <div class="col-md-offset-1 col-md-10 undercover">
        <div class="row">
            <div class="col-md-12 green-header">
                <div class="row">
                    <div class="col-md-8">
                        <h2 style="text-align: right">${user.name}</h2>
                    </div>
                </div>

            </div>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="row work-block">
                    <div class="col-md-12">
                        <h4>Инфо</h4>
                        <hr class="full-grey-hr"/>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Телефон:</label>
                                <div class="col-sm-9">
                                    <p class="form-control-static">${user.phone}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Email:</label>
                                <div class="col-sm-9">
                                    <p class="form-control-static">${user.email}</p>
                                </div>
                            </div>
                            <#if user.academicGroup??>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Группа:</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static"> ${user.academicGroup.name}</p>
                                    </div>
                                </div>
                            </#if>
                            <#if user.lab??>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Лаборатория:</label>
                                    <div class="col-sm-9">
                                        <a style="display: block" href="#" class="form-control-static">${user.lab.name}</a>
                                    </div>
                                </div>
                            </#if>
                        </div>

                    </div>
                </div>
                <div class="row work-block">
                    <div class="col-md-12">
                        <h4>Интересы</h4>
                        <hr class="full-grey-hr"/>
                        <div class="items">

                            <div class="custom-tag" data-value="inf" >tag</div>
                            <div class="custom-tag" data-value="inf" >tag</div>
                            <div class="custom-tag" data-value="inf" >tag</div>
                            <div class="custom-tag" data-value="inf" >tag</div>
                            <div class="custom-tag" data-value="inf" >tag</div>
                            <div class="custom-tag" data-value="inf" >tagwithverylonglonglongname</div>
                            <div class="custom-tag" data-value="inf" >tagwithverylonglonglongname</div>
                            <div class="custom-tag" data-value="inf" >tagwithverylonglonglongname</div>
                            <div class="custom-tag" data-value="inf" >tagwithverylonglonglongname</div>


                        </div>


                    </div>
                </div>

            </div>
            <div class="col-md-4 pull-left">


                <div class="main-avatar-round" id="main-avatar"
                     style="background-image: url('/static/img/avatar_example.png')"></div>


                <div class="row">
                    <div class="col-xs-offset-1 col-md-10 ">
                        <button class="btn btn-block firm-btn">Написать сообщение</button>

                        <div class="panel panel-default work-block">
                            <div class="panel-heading" style="text-align: center">Контакты</div>
                            <div class="panel-body chart-panel">
                                <div class="row">
                                    <div class="col-md-4"  align="center">
                                        <div class="thumb-round" id="main-avatar"
                                             style="background-image: url('/static/img/avatar_example.png')"></div>
                                        <a href="#" class="underlabel"><p>Иван Иванов</p></a>
                                    </div>
                                    <div class="col-md-4 " align="center">
                                        <div class="thumb-round" id="main-avatar"
                                             style="background-image: url('/static/img/avatar_example.png')"></div>
                                        <a href="#" class="underlabel"><p>Иван Иванов</p></a>
                                    </div>
                                    <div class="col-md-4" align="center">
                                        <div class="thumb-round" id="main-avatar"
                                             style="background-image: url('/static/img/avatar_example.png')"></div>
                                        <a href="#" class="underlabel"><p>Иван Иванов</p></a>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4" align="center">
                                        <div class="thumb-round" id="main-avatar"
                                             style="background-image: url('/static/img/avatar_example.png')"></div>
                                        <a href="#" class="underlabel"><p>Иван Иванов</p></a>
                                    </div>
                                    <div class="col-md-4 " align="center">
                                        <div class="thumb-round" id="main-avatar"
                                             style="background-image: url('/static/img/avatar_example.png')"></div>
                                        <a href="#" class="underlabel"><p>Иван Иванов</p></a>
                                    </div>
                                    <div class="col-md-4" align="center">
                                        <div class="thumb-round" id="main-avatar"
                                             style="background-image: url('/static/img/avatar_example.png')"></div>
                                        <a  href="#" class="underlabel"><p>Иван Иванов</p></a>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>


            </div>
        </div>
    </div>
</div>
<!-- /.row -->
</#macro>


<#macro footer_custom_imports>
<script type="application/javascript">

    var cw = $('#main-avatar').width();
    $('#main-avatar').css({'height':cw+'px'});
    var cs = $('.thumb-round').width();
    $('.thumb-round').css({'height':cs+'px'});


</script>


</#macro>

<@display "Профиль" />