<#ftl encoding='utf-8'>
<#include 'base.ftl'>
<#macro body>
    <div class="row">
        <h1> Table</h1>
    </div>
    <#if subjects?has_content>
        <#list subjects as subject>
        ${subject.name}
        </#list>
    <#else>
    not subjects
    </#if>
    <#if scores?has_content>
        <#list scores as entry>
        ${entry.key.name}
            <#list entry.value as entry2>
                ${entry2.value.score}
            </#list>

        </#list>
    <#else>
    not scores
    </#if>
</#macro>

<@display "рейтинг"></@display>