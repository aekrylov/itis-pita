all_groups = [
<#list all_groups as ag>
{name:${ag.name}, imageLink:${ag.imageLink!""},
interests:[
<#list ag.interests as i>
    {name:${i.name}}
</#list>],
size:${ag.members?size}},
</#list>
];

my_groups = [
<#list my_groups as mg>
{name:${mg.name}, imageLink:${mg.imageLink!""},
interests:[
    <#list mg.interests as i>
    {name:${i.name}}
    </#list>],
size:${mg.members?size}},
</#list>
];

all_courses = [
<#list all_courses as ac>
{name:${ac.name}, imageLink:${ac.imageLink!""},
interests:[
    <#list ac.interests as i>
    {name:${i.name}}
    </#list>],
size:${ac.members?size}},
</#list>
];

my_courses = [
<#list my_courses as mc>
{name:${mc.name}, imageLink:${mc.imageLink!""},
interests:[
    <#list mc.interests as i>
    {name:${i.name}}
    </#list>],
size:${mc.members?size}},
</#list>
];

all_labs = [
<#list all_labs as al>
{name:${al.name}, imageLink:${al.imageLink!""},
interests:[
    <#list al.interests as i>
    {name:${i.name}}
    </#list>],
size:${al.members?size}},
</#list>
];

my_labs = [
<#list my_labs as ml>
{name:${ml.name}, imageLink:${ml.imageLink!""},
interests:[
    <#list ml.interests as i>
    {name:${i.name}}
    </#list>],
size:${ml.members?size}},
</#list>
];

<#list all_groups as ag>
    <p>${ag.name}, ${ag.imageLink!""}, ${ag?size}</p>
    <#list ag.interests as i>
        <a href="/group?id=${i.id}">${i.name}</a>
    </#list>
</#list>

<#list my_groups as mg>
<p>${mg.name}, ${mg.imageLink!""}, ${mg?size}</p>
    <#list mg.interests as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list all_courses as ac>
<p>${ac.name}, ${ac.imageLink!""}, ${ac?size}</p>
    <#list ac.interests as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list my_courses as mc>
<p>${mc.name}, ${mc.imageLink!""}, ${mc?size}</p>
    <#list mc.interests as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list all_labs as al>
<p>${al.name}, ${al.imageLink!""}, ${al?size}</p>
    <#list al.interests as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list my_labs as ml>
<p>${ml.name}, ${ml.imageLink!""}, ${ml?size}</p>
    <#list ml.interests as i>
    <p>${i.name}</p>
    </#list>
</#list>