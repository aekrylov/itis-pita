<#ftl encoding='utf-8'>
<#-- TODO import Spring taglib -->
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

    <script type="text/javascript" src="/static/js/jquery-3.1.1.js"></script>
    <script src="/static/js/bootstrap.js"></script>
    <@header_custom_imports>

    </@header_custom_imports>
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
    <@footer_custom_imports>

    </@footer_custom_imports>
</body>
</html>
</#macro>

<#-- fix error ehn macros not defined -->
<#macro header_custom_imports></#macro>
<#macro footer_custom_imports></#macro>