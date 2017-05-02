<#ftl encoding='utf-8'>
<#include 'base.ftl'>
<#macro header_custom_imports>
<script type="text/javascript" src="/static/js/moment.js"></script>
<script type="text/javascript" src="/static/js/moment-with-locales.min.js"></script>
<link rel="stylesheet" href="/static/css/group_of_interests.css">
</#macro>
<#macro body>
<div class="undercover">
    <div class="col-lg-12 group-main-block">
        <h2 class="group-label-text">${community.name}</h2>
    </div>
    <div class="group_container">
        <div class="row">
            <div class="col-lg-8">
                <div class="col-lg-12 group-info">
                    <#switch community.type>
                        <#case "GROUP">
                            <h4>О группе</h4>
                            <#break>
                        <#case "EVENT">
                            <h4>О событии</h4>
                            <#break>
                        <#case "COURSE">
                            <h4>О курсе</h4>
                            <#break>
                    </#switch>
                    <hr>
                    <p>${community.description}</p>
                </div>
                <#switch community.type>
                    <#case "GROUP">
                        <div class="col-lg-12 group-tags">
                            <h4>Интересы</h4>
                            <#list community.interests as interest>
                                <div class="group-tag">${interest}</div>
                            </#list>
                        </div>
                        <#break >
                    <#case "EVENT">
                        <h5 class="col-lg-12 group-info-header">Дата</h5>
                        <div class="col-lg-12 group-timetable">
                        ${community.date}
                        </div>
                        <h5 class="col-lg-12 group-info-header">Место проведения</h5>
                        <div class="col-lg-12 group-timetable">
                        ${community.place}
                        </div>
                        <h5 class="col-lg-12 group-info-header">Количество свободных мест</h5>
                        <div class="col-lg-12 group-timetable">
                            <#if community.maxMembers==-1>
                                Неограниченно
                            <#else>
                                Зарегистрированно ${community.members?size} из ${community.maxMembers}
                            </#if>
                        </div>
                        <#break >
                    <#case "COURSE">
                        <h5 class="col-lg-12 group-info-header">Преподаватели</h5>
                        <div class="col-lg-12 group-teacher">
                            <#list community.teachers as teacher>
                                <a href="/profile?id=${teacher.id}">
                                    <div class="group-teacher-profile">
                                    <#--TODO teacher's avatar-->
                                        <img src="http://placehold.it/50x50"/>
                                        <b class="group-teacher-profile-name">${teacher.name}</b>
                                    </div>
                                </a>
                            </#list>
                        </div>
                        <h5 class="col-lg-12 group-info-header">Расписание</h5>
                        <div class="col-lg-12 group-timetable">
                        ${community.schedule!""}
                        </div>
                        <#break >
                </#switch>
            </div>
            <div class="col-lg-4 group-block">
                <form method="post">
                    <div class="group-image">
                        <img src="${community.imageLink!"/static/img/avatar_example.png"}">
                    </div>
                    <#if community.members?seq_contains(current_user)>
                        <input type="hidden" name="action" value="leave">
                        <button type="submit" class="button-enter-group">Покинуть группу</button>
                    <#elseif community.admins?seq_contains(current_user)>
                        <input type="hidden" name="action" value="leave">
                        <button type="submit"
                                class="button-enter-group"
                                <#if community.admins <= 1> disabled="disabled" </#if>
                                style="display:inline; margin: 230px 0 0 87px">Покинуть группу
                        </button>
                        <a href="#"><span class="glyphicon glyphicon-edit group-modify-icon"></span></a>
                    <#else>
                        <input type="hidden" name="action" value="join">
                        <button type="submit" class="button-enter-group">Вступить в группу</button>
                    </#if>
                </form>
                <div class="col-lg-12 group-admins">
                    <#list community.admins as admin>
                        <a class="" href="/profile?id=${admin.id}">
                            <div class="row group-admin-profile">
                            <#--TODO admin's avatar-->
                                <img class="col-lg-5 group-admin-image" src="http://placehold.it/50x50"/>
                                <div class="col-lg-7 group-admin-info">
                                    <b class="group-admin-profile-name">${admin.name}</b>
                                <#--TODO admin's role in this group-->
                                    <b class="group-admin-role">*роль*</b>
                                </div>
                            </div>
                        </a>
                    </#list>
                </div>
            </div>
        </div>
        <hr class="group-line"/>
        <#if community.members?seq_contains(current_user)>
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
                    <#if community.wall?has_content>
                        <#list community.wall as post>
                            <div class="col-lg-12 group-post">
                                <a class="" href="/profile?id=${post.author.id}">
                                    <div class="row group-user-profile">
                                    <#--TODO user's avatar-->
                                        <img class="col-lg-5 group-user-image" src="http://placehold.it/50x50"/>
                                        <div class="col-lg-7 group-user-info">
                                            <b class="group-user-profile-name">${post.author.name}</b>
                                            <b id="post-creation-time" data-date="${post.timestamp}" class="group-user-time"></b>
                                        <#--TODO test this script-->
                                            <script>
                                                $(document).ready(function () {
                                                            moment.locale('ru');
                                                            var then = $('#post-creation-time'),
                                                                    date = moment(new Date(then.attr('data-date')))
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
                                <#if post.comments?size gt 3>
                                    <div class="col-lg-12 comments-button-helper" id="${post.id}">
                                        <a class="col-lg-12 comments-button" id="${post.id} button">Показать все комментарии</a>
                                    </div>
                                </#if>
                                <div class="col-lg-12 group-comment-label">Комментарии:</div>
                                <#list post.comments as comment>
                                    <div id="${comment.id} comments" <#if comment?index < post.comments?size - 3> style="display: none;" </#if> class="col-lg-12 group-comment">
                                        <a class="" href="/profile?id=${comment.author.id}">
                                            <div class="row group-user-profile">
                                            <#--TODO user's avatar-->
                                                <img class="col-lg-5 group-user-image" src="http://placehold.it/50x50"/>
                                                <div class="col-lg-7 group-user-info">
                                                    <b class="group-user-profile-name" style="margin-top: 17px;">${comment.author.name}</b>
                                                </div>
                                            </div>
                                        </a>
                                        <div class="col-lg-11 group-comment-text">
                                        ${comment.text}
                                        </div>
                                        <span id="comment-creation-time" data-date="${comment.timestamp}" class="col-lg-11 group-user-time" style="float: right; width: 91.66666667%; padding-left: 15px; padding-right: 15px"></span>
                                        <script>
                                            $(document).ready(function () {
                                                        moment.locale('ru');
                                                        var then = $('#comment-creation-time'),
                                                                date = moment(new Date(then.attr('data-date')))
                                                        ),
                                                    update = function () {
                                                        then.html(date.fromNow());
                                                    };

                                            update();
                                            setInterval(update, 60000);
                                            })
                                        </script>
                                    </div>
                                    <script>
                                        function slideComments() {
                                            $('#${post.id}').click(function () {
                                                var comments = $('#${comment.id} comments');
                                                if(${comment?index} < ${post.comments?size - 3}){
                                                    if (!comments.is(':visible')) {
                                                        comments.slideDown('fast', function () {
                                                            $('#${post.id} button').text('Скрыть комментарии');
                                                        });
                                                    } else {
                                                        comments.slideUp('fast', function () {
                                                            $('#${post.id} button').text('Показать все комментарии');
                                                        });
                                                    }
                                                }
                                            });
                                        }
                                        $(document).ready(slideComments);
                                    </script>
                                </#list>
                            </#if>
                            <div class="col-lg-12 group-comment-create">
                                <form method="post" action="/group/${community.id}/wall/${post.id}/comments/new">
                                    <textarea class="group-create-comment-text" id="create-comment"
                                              placeholder="Введите свой комментарий&hellip;"
                                              name="text"></textarea>
                                    <button class="group-create-comment-submit" type="submit">ОТПРАВИТЬ</button>
                                    <div class="group-create-post-image-upload">
                                    <#--TODO input image button with preview of image-->
                                        <label for="file-input2">
                                            <span class="glyphicon glyphicon-camera group-create-post-image-upload-icon"></span>
                                        </label>
                                        <input id="file-input2" type="file" name="image">
                                    </div>

                                </form>
                            </div>
                        </#list>
                    </#if>
                </div>
                <div class="col-lg-4">
                    <div class="col-lg-12 group-members">
                        <div class="group-members-label">Участники</div>
                        <#list 0..(community.members?size/3) as i>
                            <div class="row" <#if i gt 0>
                                 style="margin-top: 7px"
                            </#if>>
                                <#list 1..3 as j>
                                    <#if (community.members?size>=(i+1)*j)>
                                        <#assign user = community.members[(i+1)*j-1]>
                                        <#if user?has_content>
                                            <a class="col-lg-4 group-member" href="#">
                                                <div class="col-lg-12 group-user-image-member">
                                                    <img src="http://placehold.it/50x50"/>
                                                </div>
                                                <b class="col-lg-12 group-user-profile-name-member">${user.name}</b>
                                            </a>
                                        </#if>
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
    </div>
</div>
</#macro>

<@display community.name/>