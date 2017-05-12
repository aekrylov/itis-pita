<#ftl encoding='utf-8'>

<#macro body>
    <#if subjects?has_content>
        <#list subjects as subject>
        ${subject.name}
        </#list>
    <#else>
    not subjects
    </#if>
    <#if scores?has_content>
        <#list scores.entrySet() as entry>
        ${entry.key.name}
            <#list entry.value.entrySet() as entry2>
                ${entry2.value.score}
            </#list>

        </#list>
    <#else>
    not scores
    </#if>
</#macro>