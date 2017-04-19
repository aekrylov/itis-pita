all_groups_items = [
<#list all_groups as ag>
{id:${ag.id}, name:${ag.name}, avatar:${ag.imageLink!""},
tags:[
    <#list ag.interests as i>
    {text:${i.name}},
    </#list>],
members:${ag.members?size}},
</#list>
];

my_groups_items = [
<#list my_groups as mg>
{id:${ag.id}, name:${mg.name}, avatar:${mg.imageLink!""},
tags:[
    <#list mg.interests as i>
    {text:${i.name}},
    </#list>],
members:${mg.members?size}},
</#list>
];

all_courses_items = [
<#list all_courses as ac>
{id:${ag.id}, name:${ac.name}, avatar:${ac.imageLink!""},
tags:[
    <#list ac.interests as i>
    {text:${i.name}},
    </#list>],
members:${ac.members?size}},
</#list>
];

my_courses_items = [
<#list my_courses as mc>
{id:${ag.id}, name:${mc.name}, avatar:${mc.imageLink!""},
interests:[
    <#list mc.interests as i>
    {text:${i.name}},
    </#list>],
members:${mc.members?size}},
</#list>
];

all_labs_items = [
<#list all_labs as al>
{id:${ag.id}, name:${al.name}, avatar:${al.imageLink!""},
tags:[
    <#list al.interests as i>
    {text:${i.name}},
    </#list>],
members:${al.members?size}},
</#list>
];

my_labs_items = [
<#list my_labs as ml>
{id:${ag.id}, name:${ml.name}, avatar:${ml.imageLink!""},
tags:[
    <#list ml.interests as i>
    {text:${i.name}},
    </#list>],
members:${ml.members?size}},
</#list>
];

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