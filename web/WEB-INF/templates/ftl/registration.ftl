<body xmlns="http://www.w3.org/1999/html">

<div class="container">

    <form method="POST" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <input type="text" class="form-control" name="email" placeholder="Username" value="${email}" />
        <input type="text" class="form-control" name="name" placeholder="Name" value="${name}" />
        <input type="text" class="form-control" name="surname" placeholder="Surname" value="${surname}" />
        <input type="text" class="form-control" name="phone" placeholder="Phone" value="${phone}" />

        <input type="password" class="form-control" name="password" placeholder="Password" />

        <input type="password" class="form-control" name="password_confirmed"
               placeholder="Confirm your password" />

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form>
    <#if error?has_content>
        ${error}
    </#if>
</div>
</body>