/**
 * Created by Олег on 20.04.2017.
 */
var container = $('#entities_list');
var standartTagsInput = $('#search_tags_input');
var standartTagsInputBlock = $('#tag_bar');
var renderHelpers = {
    isArray : function (obj) {
        return $.isArray(obj);
    }
}
var template = $.templates({markup:"#entityTemplate",allowCode: true, helpers: renderHelpers});
var searchInput = $('#search_input');
var toRenderEntity = entity;
var types = {
    my: new Object(), all: new Object()
};
var scopes = {
    courses: new Object(), labs: new Object(), groups: new Object()
};
var currentSearchConfig = {
    type: types.my,
    input: searchInput,
    tags_input : standartTagsInput,
    scope : scopes.groups,
    out : container
};
function renderEntity(entityObj, target) {

    var data = entityObj;
    var tmpl = template;
    var html = tmpl.render(data);
    target.innerHTML = "";
    target.html(html);
}
function tagsFilter(items, tags_source) {
    if (typeof tags_source === 'undefined' || tags_source.val() === null || typeof tags_source.val() === 'undefined' || tags_source.val() === '') {
        return items;
    }
    var values = tags_source.val().split(',');

    var options = {
        shouldSort: true,
        threshold: 0,
        location: 0,
        distance: 100,
        maxPatternLength: 32,
        minMatchCharLength: 1,
        keys: [
            "tags.text"
        ]
    };
    return tagsRecursive(items, values, options);

}
function tagsRecursive(items, tags, options) {

    if (tags.length == 0) {
        return items;
    }
    var lastTag = tags.pop();
    var fuse = new Fuse(tagsRecursive(items, tags, options), options);
    var result = fuse.search(lastTag);
    return result;
}
function searchGroups(items, source, tagsSource) {
    if (source.val() === null || typeof source.val() === 'undefined' || source.val().trim() == '') {
        if (tagsSource !== null && typeof tagsSource !== 'undefined') {
            return tagsFilter(items, tagsSource);
        } else {
            return items;
        }
    } else {
        if (tagsSource !== null && typeof tagsSource !== 'undefined') {
            var options = {
                shouldSort: true,
                threshold: 0.2,
                location: 0,
                distance: 100,
                maxPatternLength: 32,
                minMatchCharLength: 1,
                keys: [
                    "name"
                ]
            };
            var fuse = new Fuse(tagsFilter(items, tagsSource), options);
            var result = fuse.search(source.val());
            return result;
        } else {
            var fuse = new Fuse(items, options);
            var result = fuse.search(source.val());
            return result;
        }
    }
}
function searchAndRender(querySource, tagsSource, items, container) {
    var items_to_render = items;//searchGroups(items, querySource, tagsSource);
    renderEntity(items_to_render, container);
    return false;
}

function changeSearchConfig(type,scope) {

    if (typeof type !== 'undefined'){
        currentSearchConfig.type = type;
    }
    if (typeof scope !== 'undefined'){
        currentSearchConfig.scope = scope;
    }
    standartSearch();
}
function standartSearch() {
    searchAndRender(currentSearchConfig.input,currentSearchConfig.tags_input, toRenderEntity, container);
}
standartSearch();

function addTag(tagObj) {
    var tagText = tagObj.innerHTML;
    selectizable.addItem(tagText, false);
}
$('.editable').editable();