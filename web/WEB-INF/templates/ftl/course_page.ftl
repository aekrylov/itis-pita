<#include 'base.ftl' >
<#macro header_custom_imports>
<script type="text/javascript" src="/static/js/moment-with-locales.min.js"></script>
<link rel="stylesheet" href="/static/css/course_page.css">
</#macro>
<#macro body>
<div class="undercover">
    <#if error?has_content>
    <p>${error}</p>
    <#else>
        <div class="col-lg-12 group-main-block">
            <h2 class="group-label-text">${course.name}</h2>
        </div>
        <div class="row">
            <div class="col-lg-8">
                <div class="col-lg-12 group-info">
                    <p>${course.description}</p>
                </div>
                <h5 class="col-lg-12 group-teacher-label">Преподаватели</h5>
                <div class="col-lg-12 group-teacher">
                    <#list course.teachers as teacher>
                        <a href="/profile?id=${teacher.id}">
                            <div class="group-teacher-profile">
                            <#--TODO teacher's avatar-->
                                <img src="http://placehold.it/50x50"/>
                                <b class="group-teacher-profile-name">${teacher.name}</b>
                            </div>
                        </a>
                    </#list>
                </div>
                <h5 class="col-lg-12 group-timetable-label">Расписание</h5>
                <div class="col-lg-12 group-timetable">
                ${course.schedule}
                </div>
            </div>
            <div class="col-lg-4 group-block">
                <div class="group-image">
                    <img src="http://placehold.it/275x280">
                </div>
                <#if course.members?seq_contains(current_user)>
                    <button class="button-enter-group">Покинуть группу</button>
                <#elseif course.admins?seq_contains(current_user)>
                    <button class="button-enter-group" style="display:inline; margin: 230px 0 0 87px">Покинуть группу
                    </button>
                    <a href="#"><span class="glyphicon glyphicon-edit group-modify-icon"></span></a>
                <#else>
                    <button class="button-enter-group">Вступить в группу</button>
                </#if>
                <div class="col-lg-12 group-admins">
                    <#list course.admins as admin>
                        <a class="" href="/profile?id=${admin.id}">
                            <div class="row group-admin-profile">
                            <#--TODO admin's avatar-->
                                <img class="col-lg-5 group-admin-image" src="http://placehold.it/50x50"/>
                                <div class="col-lg-7 group-admin-info">
                                    <b class="group-admin-profile-name">${admin.name}</b>
                                <#--TODO admin's role in this group-->
                                    <b class="group-admin-role">создатель</b>
                                </div>
                            </div>
                        </a>
                    </#list>
                </div>
            </div>
        </div>
        <hr class="group-line"/>
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
                        <#list 1..2 as i>
                            <div class="row" <#if i == 1>
                                 style="margin-bottom: 7px"
                                 </#if>>
                                <#list 1..3 as j>
                                    <#assign user = course.members[i*j]>
                                    <#if user?has_content>
                                        <a class="col-lg-4 group-member" href="#">
                                            <div class="col-lg-12 group-user-image-member">
                                            <#--TODO user's avatar-->
                                                <img src="http://placehold.it/50x50"/>
                                            </div>
                                            <b class="col-lg-12 group-user-profile-name-member">${user.name}</b>
                                        </a>
                                    </#if>
                                </#list>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        <#else>
            <h3 class="group-posts-error">Для просмотра полной информации требуется вступить в группу.</h3>
        </#if>
    </#if>
</div>
</#macro>

<@display course.name/>