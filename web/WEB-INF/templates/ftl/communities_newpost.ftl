<#include 'base.ftl'>

<#macro body>
        <form method="post">
            <p>Text:</p>
            <textarea name="text">

            </textarea>

            <p>Video (optional):</p>
            <input name="videoLink" type="text">

            <input type="submit" value="create">
        </form>
</#macro>

<@display "New post" />