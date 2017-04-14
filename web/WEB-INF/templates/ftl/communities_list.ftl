all_groups = [
<#list all_groups as ag>
{name:${ag.getName}, image_link:${ag.getImageLink},
interests:[
<#list ag.getInterests as i>
    {name:${i.getName}}
</#list>],
size:${ag.getMembers.size}},
</#list>
];

my_groups = [
<#list my_groups as mg>
{name:${mg.getName}, image_link:${mg.getImageLink},
interests:[
    <#list mg.getInterests as i>
    {name:${i.getName}}
    </#list>],
size:${mg.getMembers.size}},
</#list>
];

all_courses = [
<#list all_courses as ac>
{name:${ac.getName}, image_link:${ac.getImageLink},
interests:[
    <#list ac.getInterests as i>
    {name:${i.getName}}
    </#list>],
size:${ac.getMembers.size}},
</#list>
];

my_courses = [
<#list my_courses as mc>
{name:${mc.getName}, image_link:${mc.getImageLink},
interests:[
    <#list mc.getInterests as i>
    {name:${i.getName}}
    </#list>],
size:${mc.getMembers.size}},
</#list>
];

all_labs = [
<#list all_labs as al>
{name:${al.getName}, image_link:${al.getImageLink},
interests:[
    <#list al.getInterests as i>
    {name:${i.getName}}
    </#list>],
size:${al.getMembers.size}},
</#list>
];

my_labs = [
<#list my_labs as ml>
{name:${ml.getName}, image_link:${ml.getImageLink},
interests:[
    <#list ml.getInterests as i>
    {name:${i.getName}}
    </#list>],
size:${ml.getMembers.size}},
</#list>
];

<#list all_groups as ag>
    <p>${ag.name}, ${ag.image_link}, ${ag.size}</p>
    <#list ag.interests as i>
        <p>${i.name}</p>
    </#list>
</#list>

<#list my_groups as mg>
<p>${mg.name}, ${mg.image_link}, ${mg.size}</p>
    <#list mg.interests as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list all_courses as ac>
<p>${ac.name}, ${ac.image_link}, ${ac.size}</p>
    <#list ac.interests as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list my_courses as mc>
<p>${mc.name}, ${mc.image_link}, ${mc.size}</p>
    <#list mc.interests as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list all_labs as al>
<p>${al.name}, ${al.image_link}, ${al.size}</p>
    <#list al.interests as i>
    <p>${i.name}</p>
    </#list>
</#list>

<#list my_labs as ml>
<p>${ml.name}, ${ml.image_link}, ${ml.size}</p>
    <#list ml.interests as i>
    <p>${i.name}</p>
    </#list>
</#list>