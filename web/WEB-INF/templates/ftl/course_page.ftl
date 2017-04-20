<#include 'base.ftl' >

<#macro body>
<p>
    <#if error?has_content>
        <p>${error}</p>
    <#else>
    <p>Name: ${course.getName()}</p>
    <p>Description:${course.getDescription()}</p>
            <!--  Not image in model -->
    <p>Creator: <a href="/profile?id=${course.getCreator().getId()}">${course.getCreator().getName()}<a></p>
    <p>Admins:<#if course.getAdmins?has_content >
                    <#list course.getAdmins() as a>
                        <tr>
                            <td><a href="/profile?id=${a.getId()}">${a.getName()}<a></td>
                        </tr>
                    </#list>
                <#else>
                <p>Not admins</p>
                </#if>
    </p>
    <p>Schedule: ${course.getSchedule()}</p>
    <p> Teachers:
        <#list course.getTeachers() as t>
            <tr>
                <td><a href="/profile?id=${t.getId()}">${t.getName()}<a></td>
            </tr>
        </#list>
    </p>
        <#if participant == true>
        <p> Members:
            <#if course.getMembers?has_content >
                <#list course.getMembers() as m>
                <tr>
                    <td><a href="/profile?id=${m.getId()}">${m.getName()}<a></td>
                </tr>
                </#list>
            <#else>
                <p>Not members</p>
            </#if>

        </p>
        </#if>
    </#if>

</p>
</#macro>

<@body></@body>