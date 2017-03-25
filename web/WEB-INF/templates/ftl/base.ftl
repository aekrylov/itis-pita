<#ftl encoding='utf-8'>
<#macro display title="Название компании">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${title}</title>

    <link href="/static/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/common.css" rel="stylesheet">

    <script type="text/javascript" src="/static/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/static/js/jquery/rateyo/jquery.rateyo.min.js"></script>
    <script type="text/javascript" src="/static/js/jquery/fotorama/fotorama.js"></script>
    <script type="text/javascript" src="/static/js/jquery/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="/static/js/bootstrap.js"></script>

</head>

<body>
<div class="page">
    <!--header start-->
    <header>

    </header>
    <nav class="navbar pita-header">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">
                            <img class="img-responsive header-logo"  alt="Brand" src="/static/img/pita_logo_wide_light.png">
                        </a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="/messages"><span class="glyphicon glyphicon-envelope"
                                                          aria-hidden="true"></span>
                                Сообщения</a></li>
                            <li><a href="/feed"><span class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span>
                                Лента новостей</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="divider-vertical"><a href="/info"><span id="icon_span"
                                                                               class="glyphicon glyphicon-bell"
                                                                               aria-hidden="true"></span></a>
                            </li>
                            <li>
                                <a id="avatar-link"  class="navbar-brand" href="/profile">
                                    <img href="/profile"  class="img-responsive  avatar" height="40" width="40" src="/static/img/avatar_example.png"/>
                                </a>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Александр <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                </ul>

                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </nav>
    <!--End of header -->

    <div class="container">
        <@body>
            Dummy body
        </@body>
    </div>
    <div class="push"></div>

</div>
<footer>
<#-- include 'footer.ftl'??? -->
</footer>
</body>
</html>
</#macro>