/**
 * Created by Олег on 20.04.2017.
 */
function renderGroups(items, target) {

    var data = {items: items};
    var tmpl = $.templates("#groupTemplate");
    var html = tmpl.render(data);
    target.innerHTML = "";
    target.html(html);
}
function tagsFilter(items, tags_source) {
    console.log('tags_filter_on');
    console.log(tags_source.val());
    if (typeof tags_source === 'undefined' || tags_source.val() === null || typeof tags_source.val() === 'undefined' || tags_source.val() === '') {
        return items;
    }
    console.log(tags_source);
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
                threshold: 0.8,
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
    var items = searchGroups(items, querySource, tagsSource);
    renderGroups(items, container);
    return false;
}
var currentSearchConfig;
var types = {
    my: new Object(), all: new Object()
}
var scopes = {
    courses: new Object(), labs: new Object(), groups: new Object()
}
var currentSearchConfig = {
    type: types.my,
    input: $('#search_input'),
    tags_input : $('#search_tags_input'),
    scope : scopes.groups,
    out : $('#courses_list')
};

var configs = {
    my_courses : {
        scope_items : my_courses_items,
        out:$('#courses_list')
    },
    my_labs : {
        scope_items : my_labs_items,
        out:$('#labs_list')
    },
    my_groups : {
        scope_items : my_groups_items,
        out:$('#groups_list')
    },
    all_courses : {
        scope_items : all_courses_items,
        out:$('#courses_list')
    },
    all_labs : {
        scope_items : all_labs_items,
        out:$('#labs_list')
    },
    all_groups : {
        scope_items : all_groups_items,
        out:$('#groups_list')
    }


}
function changeSearchConfig(type,scope) {
    console.log(type);
    console.log(scope);
    if (typeof type !== 'undefined'){
        currentSearchConfig.type = type;
    }
    if (typeof scope !== 'undefined'){
        currentSearchConfig.scope = scope;
    }
    standartSearch();
}
function standartSearch() {
    var currentCollectionConfig = configs.all_courses;
    if (currentSearchConfig.type === types.my){
        if (currentSearchConfig.scope === scopes.courses){
            currentCollectionConfig = configs.my_courses;
            console.log('selected my_courses');
        } else {
            if (currentSearchConfig.scope === scopes.groups){
                currentCollectionConfig = configs.my_groups;
                console.log('selected my_groups');
            } else {
                currentCollectionConfig = configs.my_labs;
                console.log('selected my_labs');
            }
        }
    } else {
        if (currentSearchConfig.scope === scopes.courses){
            currentCollectionConfig = configs.all_courses;
            console.log('selected all_courses');
        } else {
            if (currentSearchConfig.scope === scopes.groups){
                currentCollectionConfig = configs.all_groups;
                console.log('selected all_groups');
            } else {
                currentCollectionConfig = configs.all_labs;
                console.log('selected all_labs');
            }
        }
    }
    searchAndRender(currentSearchConfig.input,currentSearchConfig.tags_input, currentCollectionConfig.scope_items, currentCollectionConfig.out);
}
standartSearch();

function addTag(tagObj) {
    var tagText = tagObj.innerHTML;
    selectizable.addItem(tagText, false);
}