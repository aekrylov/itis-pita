<#ftl encoding='utf-8'>
<#include 'base.ftl' >
<#macro header_custom_imports>
<link href="/static/selectize/css/selectize.default.css" media="all" rel="stylesheet" type="text/css"/>
<link href="/static/css/communities-list.css" media="all" rel="stylesheet" type="text/css"/>
<script src="/static/selectize/js/standalone/selectize.js"></script>
<script src="/static/search/jsrender.js"></script>
</#macro>



<#macro body>
<div class="row ">
    <div class="col-md-offset-1 col-md-10 undercover">
        <div class="row">
            <div class="col-md-12 green-header">
                <h1>СООБЩЕСТВА</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-9">
                <div class="row work-block">
                    <div class="col-md-12">


                        <div class="input-group stylish-input-group">
                            <input type="text" name="search" class="form-control"
                                   placeholder="Поиск групп" id="search_input"
                                   oninput="standartSearch()">
                            <span class="input-group-addon"><button type="submit"><span
                                    class="glyphicon glyphicon-search"></span></button></span>
                        </div>
                        <div class="row">
                            <div class="col-md-1">
                                <div class="tags-label-container">
                                    <label>Теги:</label>
                                </div>
                            </div>


                            <div class="col-md-11">
                                <div class="form-group">
                                    <input type="text" id="search_tags_input" class="selectized"
                                           tabindex="-1" style="display: none;" onchange="standartSearch()">
                                </div>
                            </div>
                        </div>


                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">


                        <ul class="nav nav-tabs no-border-tabs" role="tablist">
                            <li role="presentation" class="active"
                                onmouseenter="$('.tab-content>.work-block + .work-block').css('border-top-left-radius', '0px')"
                                onmouseleave="$('.tab-content>.work-block + .work-block').css('border-top-left-radius', '5px')"
                            ><a href="#groups" aria-controls="groups" role="tab" data-toggle="tab" onclick="changeSearchConfig(undefined, scopes.groups)">Группы</a>
                            </li>
                            <li role="presentation"><a href="#courses" aria-controls="courses" role="tab"
                                                       data-toggle="tab" onclick="changeSearchConfig(undefined, scopes.courses)">Курсы</a></li>
                            <li role="presentation"><a href="#labs" aria-controls="labs" role="tab"
                                                       data-toggle="tab" onclick="changeSearchConfig(undefined, scopes.labs)">Лаборатории</a></li>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active work-block first-tab" id="groups">
                                <br/>
                                <ul class="media-list list" id="groups_list">

                                </ul>
                            </div>


                            <div role="tabpanel" class="tab-pane work-block" id="courses">
                                <br/>
                                <ul class="media-list" id="courses_list">

                                </ul>
                            </div>


                            <div role="tabpanel" class="tab-pane work-block" id="labs">
                                <br/>
                                <ul class="media-list" id="labs_list">

                                </ul>
                            </div>


                        </div>


                    </div>
                </div>

            </div>
            <div class="col-md-3">
                <ul class="nav nav-pills switch-pills nav-stacked work-block" role="tablist">
                    <li role="presentation" class="active">
                        <a aria-controls="home" role="tab" data-toggle="tab" onclick="changeSearchConfig(types.my, undefined)">Мои сообщества</a>
                    </li>
                    <li role="presentation">
                        <a aria-controls="profile" onclick="changeSearchConfig(types.all, undefined)" role="tab" data-toggle="tab">Глобальный
                            поиск</a>
                    </li>
                </ul>
                <div class="btn-group-vertical" role="group">
                    <a class="btn btn-block firm-btn" href="/groups/create">Создать группу</a>
                    <#switch current_user.role>
                        <#case "ROLE_DEAN">
                        <#case "ROLE_SUPERUSER">
                            <a class="btn btn-block firm-btn" href="/labs/create">Создать лабораторию</a>
                            <a class="btn btn-block firm-btn" href="/courses/create">Создать курс</a>
                            <#break>
                    </#switch>

                </div>

            </div>
        </div>
    </div>
</div>
</#macro>


<#macro footer_custom_imports>
<script src="/static/js/simpleTagsSelectizer.js"></script>
<script type="application/javascript">
    //see simpleTagsSelectizer for more information about items
    tags_items = [
        <#list tags as tag>
            {value:'${tag.name}', text:'${tag.name}'},
        </#list>
    ];
    var selectizable = simpleTagsSelectizer('search_tags_input', tags_items, false)[0].selectize;
</script>
<script id="groupTemplate" type="text/x-jsrender">
    {{for items}}
	<li class="media">
        <div class="media-left media-middle">
            <a href="/group?id={{:id}}">
                <div class="media-object thumb-round"
                     style="background-image: url('{{:avatar}}');"></div>
            </a>
        </div>
        <div class="media-body">
            <h4 class="media-heading community-title"><a class="link" href="/group?id={{:id}}">{{:name}}</a></h4>

            <div class="items">
            {{for tags}}
                <div class="custom-tag" data-value="inf" onclick = (addTag(this))>{{:text}}</div>
            {{/for}}
            </div>
            <p class="small"><span class="glyphicon glyphicon-user"
                                   aria-hidden="true"></span> {{:members}}</p>
        </div>
    </li>
    {{/for}}
</script>


<script type="application/javascript">


    all_groups_items = [
        <#list all_groups as ag>
            {id:'${ag.id}', name:'${ag.name}', avatar:'${ag.imageLink}',
                tags:[
                    <#list ag.interests as i>
                        {text:'${i.name}'},
                    </#list>],
                members:${ag.members?size}},
        </#list>
    ];

    my_groups_items = [
        <#list my_groups as ag>
            {id:'${ag.id}', name:'${ag.name}', avatar:'${ag.imageLink}',
                tags:[
                    <#list ag.interests as i>
                        {text:'${i.name}'},
                    </#list>],
                members:${ag.members?size}},
        </#list>
    ];

    all_courses_items = [
        <#list all_courses as ag>
            {id:'${ag.id}', name:'${ag.name}', avatar:'${ag.imageLink}',
                tags:[
                    <#list ag.interests as i>
                        {text:'${i.name}'},
                    </#list>],
                members:${ag.members?size}},
        </#list>
    ];

    my_courses_items = [
        <#list my_courses as ag>
            {id:'${ag.id}', name:'${ag.name}', avatar:'${ag.imageLink}',
                tags:[
                    <#list ag.interests as i>
                        {text:'${i.name}'},
                    </#list>],
                members:${ag.members?size}},
        </#list>
    ];

    all_labs_items = [
        <#list all_labs as ag>
            {id:'${ag.id}', name:'${ag.name}', avatar:'${ag.imageLink}',
                tags:[
                    <#list ag.interests as i>
                        {text:'${i.name}'},
                    </#list>],
                members:${ag.members?size}},
        </#list>
    ];

    my_labs_items = [
        <#list my_labs as ag>
            {id:'${ag.id}', name:'${ag.name}', avatar:'${ag.imageLink!""}',
                tags:[
                    <#list ag.interests as i>
                        {text:'${i.name}'},
                    </#list>],
                members:${ag.members?size}},
        </#list>
    ];

</script>
<script src="/static/search/fuse.js"></script>
<script src="/static/search/jsrender.js"></script>
<script src="/static/search/communitiesSearchAndRender.js"></script>
</#macro>
<#--



tags_items = [
<#list tags as tag>
{value:${tag.name}, text:${tag.name}},
</#list>
];

<#list all_groups_items as ag>
<a href="/group?id=${ag.id}">${ag.name}</a>, <p>${ag.avatar!""}, ${ag.members}</p>
    <#list ag.tags as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list my_groups_items as mg>
<a href="/group?id=${ag.id}">${ag.name}</a>, <p>${mg.avatar!""}, ${mg.members}</p>
    <#list mg.tags as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list all_courses_items as ac>
<a href="/group?id=${ag.id}">${ag.name}</a>, <p>${ac.avatar!""}, ${ac.members}</p>
    <#list ac.tags as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list my_courses_items as mc>
<a href="/group?id=${ag.id}">${ag.name}</a>, <p>${mc.avatar!""}, ${mc.members}</p>
    <#list mc.tags as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list all_labs_items as al>
<a href="/group?id=${ag.id}">${ag.name}</a>, <p>${al.avatar!""}, ${al.members}</p>
    <#list al.tags as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list my_labs_items as ml>
<a href="/group?id=${ag.id}">${ag.name}</a>, <p>${ml.avatar!""}, ${ml.members}</p>
    <#list ml.tags as i>
    <p>${i.name}</p>
    </#list>
</#list>

<a href="/groups/create">Создать группу</a>
<#switch user_role>
    <#case "ROLE_DEAN">
    <#case "ROLE_SUPERUSER">
        <a href="/courses/create">Создать группу</a>
        <a href="/labs/create">Создать группу</a>
        <#break>
</#switch>
-->

<@display "Группы" />