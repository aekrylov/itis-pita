<#ftl encoding='utf-8'>
<#include 'base.ftl' >


<#macro header_custom_imports>
<link href="/static/selectize/css/selectize.default.css" media="all" rel="stylesheet" type="text/css"/>
<script src="/static/selectize/js/standalone/selectize.js"></script>
</#macro>


<#macro body>
<div class="undercover">
    <div class="row">
        <div class="col-md-offset-1 col-md-9">
            <h1 class="page-header"> Создание группы по интересам</h1>
        </div>
    </div>
    <!-- /.row -->
    <form role="form" class="" action="/groups/create" method="POST" enctype="multipart/form-data" onsubmit="return checkform([
            'group_name', 'group_info', 'group_tags'
        ], false)">
        <div class="row">
            <div class="col-xs-offset-1 col-md-5">

                <div class="form-group">
                    <label class="control-label" for="group_name">Название группы</label>
                    <input onfocus="removeHasError('group_name')" type="text" minlength="3" class="form-control"
                           name="name"
                           id="group_name"
                           placeholder="..введите название группы"/>

                </div>

                <div class="form-group" onclick="removeHasError('group_tags')">
                    <label class=" control-label">Темы группы</label>
                    <input type="text" id="group_tags" class="selectized" name="interests"
                           tabindex="-1" style="display: none;">
                </div>


            </div>
            <div class="col-md-5">
                <div class="form-group">
                    <label class=" control-label">Информация о группе</label>
                    <textarea class="form-control" style="resize: none"
                              onfocus="removeHasError('group_info')" rows="5" name="description"
                              id="group_info"></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="form-group">
                    <button class="btn-warning btn btn-block" type="submit">Создать</button>
                </div>
            </div>
        </div>

    </form>
</div>
</#macro>


<#macro footer_custom_imports>
<script src="/static/js/checkform.js"></script>
<script src="/static/js/removeHasError.js"></script>
<script src="/static/js/simpleTagsSelectizer.js"></script>
<script type="application/javascript">
    //see groupTagsSelectizer for more information about items
    items = [
            <#if interests??>
                <#list interests as i>
                    { value: '${i.name}', text: '${i.name}' },
                </#list>
            </#if>



    ];


    $.onload = simpleTagsSelectizer('group_tags', items);
</script>
</#macro>

<@display "Создать группу"></@display>
