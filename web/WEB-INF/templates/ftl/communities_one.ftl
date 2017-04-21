<#ftl encoding='utf-8'>
<#include 'base.ftl'>
<#macro header_custom_imports>
<script type="text/javascript" src="/static/js/moment-with-locales.min.js"></script>
<link rel="stylesheet" href="/static/css/group_of_interests.css">
</#macro>
<#macro body>
<div class="undercover">
    <div class="col-lg-12 group-main-block">
        <h2 class="group-label-text">${group.name}</h2>
    </div>
    <div class="group_container">
        <div class="row">
            <div class="col-lg-8">
                <div class="col-lg-12 group-info">
                    <p>${group.description}</p>
                </div>
                <div class="row">
                    <div class="col-lg-12 group-tags">
                        <#list group.interests as interest>
                            <div class="group-tag">${interest}</div>
                        </#list>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 group-block">
                <div class="group-image">
                    <img src="${group.imageLink}">
                </div>
                <#if group.members?seq_contains(current_user)>
                    <button class="button-enter-group">Покинуть группу</button>
                <#elseif group.admins?seq_contains(current_user)>
                <button class="button-enter-group" style="display:inline; margin: 230px 0 0 87px">Покинуть группу</button>
                <a href="#"><span class="glyphicon glyphicon-edit group-modify-icon"></span></a>
                <#else>
                    <button class="button-enter-group">Вступить в группу</button>
                </#if>
                <div class="col-lg-12 group-admins">
                    <#list group.admins as admin>
                        <a class="" href="/profile?id=${admin.id}">
                            <div class="row group-admin-profile">
                            <#--TODO admin's avatar-->
                                <img class="col-lg-5 group-admin-image" src="http://placehold.it/50x50"/>
                                <div class="col-lg-7 group-admin-info">
                                    <b class="group-admin-profile-name">${admin.name}</b>
                                <#--TODO admin's role in this group-->
                                    <b class="group-admin-role">(добавить)</b>
                                </div>
                            </div>
                        </a>
                    </#list>
                </div>
            </div>
        </div>
        <hr class="group-line"/>
        <#if group.members?seq_contains(current_user)>
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
                    <#if group.wall?has_content>
                        <#list group.wall as post>
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
                            <div class="row">
                            <#--TODO list of members generation-->
                                <a class="col-lg-4 group-member" href="#">
                                    <div class="col-lg-12 group-user-image-member">
                                        <img src="http://placehold.it/50x50"/>
                                    </div>
                                    <b class="col-lg-12 group-user-profile-name-member">Имя</b>
                                </a>
                                <a class="col-lg-4 group-member" href="#">
                                    <div class="col-lg-12 group-user-image-member">
                                        <img src="http://placehold.it/50x50"/>
                                    </div>
                                    <b class="col-lg-12 group-user-profile-name-member">Имя</b>
                                </a>
                                <a class="col-lg-4 group-member" href="#">
                                    <div class="col-lg-12 group-user-image-member">
                                        <img src="http://placehold.it/50x50"/>
                                    </div>
                                    <b class="col-lg-12 group-user-profile-name-member">Имя</b>
                                </a>
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

<@display group.name/>