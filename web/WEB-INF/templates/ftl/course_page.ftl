<#include 'base.ftl' >

<#macro body>
<p>
    <#if error?has_content>
        <p>${error}</p>
    <#else>
    <p>Name: ${course.name}</p>
    <p>Description:${course.description}</p>
            <!--  Not image in model -->
    <p>Creator: <a href="/profile?id=${course.creator.id}">${course.creator.name}<a></p>
    <p>Admins:<#if course.admins?has_content >
                    <#list course.admins as a>
                        <tr>
                            <td><a href="/profile?id=${a.id}">${a.name}<a></td>
                        </tr>
                    </#list>
                <#else>
                <p>Not admins</p>
                </#if>
    </p>
        <#if course.schedule?has_content>
        <p>Schedule: ${course.schedule}</p>
        <#else>
        Not schedule
        </#if>
    <p> Teachers:
        <#if course.theachers?has_content >
            <#list course.teachers as t>
                <tr>
                    <td><a href="/profile?id=${t.id}">${t.name}<a></td>
                </tr>
            </#list>
        <#else>
        <p>Not teachers</p>
        </#if>
    </p>

        <#if course.members?seq_contains(current_user)>
        <div class="row">
            <div class="col-lg-8">
                <div class="col-lg-12 group-create-post">
                    <form method="post" action="./wall/new" enctype="multipart/form-data">
                        <div class="col-lg-12 group-create-post-label">
                            Добавить запись
                        </div>
                        <div class="col-lg-12">
                        <textarea class="group-create-post-text" type="text" id="create-post"
                                  name="text"></textarea>
                        </div>
                        <div class="col-lg-12">
                            <div class="row">
                                <div class="col-lg-6">
                                    <button type="submit" class="group-create-post-submit">ОТПРАВИТЬ</button>
                                </div>
                                <div class="col-lg-6">
                                    <div class="group-create-post-image-upload">
                                    <#--TODO input image button with preview of image-->
                                        <label for="file-input">
                                            <span class="glyphicon glyphicon-camera group-create-post-image-upload-icon"></span>
                                        </label>
                                        <input id="file-input" name="image" type="file">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <#if course.wall?has_content>
                    <#list course.wall as post>
                        <div class="col-lg-12 group-post">
                            <a class="" href="/profile?id=${post.author.id}">
                                <div class="row group-user-profile">
                                <#--TODO user's avatar-->
                                    <img class="col-lg-5 group-user-image" src="http://placehold.it/50x50"/>
                                    <div class="col-lg-7 group-user-info">
                                        <b class="group-user-profile-name">${post.author.name}</b>
                                        <b id="post-creation-time" class="group-user-time"></b>
                                    <#--TODO test this script-->
                                        <script>
                                            $(document).ready(function () {
                                                        var then = $('#post-creation-time'),
                                                                date = moment(${post.timestamp})
                                                        ),
                                                    update = function () {
                                                        then.html(date.fromNow());
                                                    };

                                            update();
                                            setInterval(update, 60000);
                                            })
                                        </script>
                                    </div>
                                </div>
                            </a>
                            <div class="col-lg-12 group-post-text">
                            ${post.text}
                            </div>
                        </div>
                        <#if post.comments?has_content>
                            <#list post.comments as comment>
                                <div class="col-lg-12 group-comment">
                                    <a class="" href="/profile?id=${comment.author.id}">
                                        <div class="row group-user-profile">
                                        <#--TODO user's avatar-->
                                            <img class="col-lg-5 group-user-image" src="http://placehold.it/50x50"/>
                                            <div class="col-lg-7 group-user-info">
                                                <b class="group-user-profile-name">${comment.author.name}</b>
                                                <b id="comment-creation-time" class="group-user-time"></b>
                                                <script>
                                                    $(document).ready(function () {
                                                                var then = $('#comment-creation-time'),
                                                                        date = moment(${comment.timestamp})
                                                                ),
                                                            update = function () {
                                                                then.html(date.fromNow());
                                                            };

                                                    update();
                                                    setInterval(update, 60000);
                                                    })
                                                </script>
                                            </div>
                                        </div>
                                    </a>
                                    <div class="col-lg-12 group-comment-text">
                                    ${comment.text}
                                    </div>
                                </div>
                            </#list>
                        </#if>
                        <div class="col-lg-12 group-comment-create">
                    <textarea class="group-create-comment-text" type="text" id="create-comment"
                              placeholder="Введите свой комментарий&hellip;"
                              name="create-comment"></textarea>
                            <button class="group-create-comment-submit" type="submit">ОТПРАВИТЬ</button>
                            <div class="group-create-post-image-upload">
                            <#--TODO input image button with preview of image-->
                                <label for="file-input2">
                                    <span class="glyphicon glyphicon-camera group-create-post-image-upload-icon"></span>
                                </label>
                                <input id="file-input2" type="file">
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
            <div class="col-lg-4">
                <div class="col-lg-12 group-members">
                    <div class="group-members-label">Участники</div>
                    <#list course.members as m>
                        <div class="row">
                            <a class="col-lg-4 group-member" href="/profile?id=${m.id}">
                                <div class="col-lg-12 group-user-image-member">
                                    <#--TODO image-->
                                    <img src="http://placehold.it/50x50"/>
                                </div>
                                <b class="col-lg-12 group-user-profile-name-member">${m.name}</b>
                            </a>

                        </div>
                    </#list>
                </div>
            </div>
        </div>
        <#else>
        <h3 class="group-posts-error">Для просмотра полной информации требуется вступить в группу.</h3>
        </#if>
    </#if>


</p>
</#macro>

<@body></@body>