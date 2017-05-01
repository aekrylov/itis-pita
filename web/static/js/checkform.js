/**
 * Created by Олег on 31.10.2016.
 */
//args: array of id's
function checkform(fields, label_is_underfield) {
    label_is_underfield = label_is_underfield == null? true : label_is_underfield;
    var flag = true;
    for (var i = 0; i < fields.length; i++) {
        var elem = $("#" + fields[i]);
        //set remove error handler
        elem.focus( function(){removeHasError($(this).attr('id'))});
        if (elem.val() === '') {
            //remove old notification about empty login
            if (elem.val() !== '' && elem.parent().children().is($("#" + elem.attr('id') + "_warning"))){
                $("#" + fields[i] + "_warning").remove();
                elem.parent().removeClass("has-error");

            }
            //add notification about empty login
            if (elem.val() === '' && !elem.parent().children().is($("#" + elem.attr('id') + "_warning"))){
                if (label_is_underfield){
                    elem.parent().append('\<label class="control-label" id ="' + elem.attr('id') + '\_warning" for="input_login">Обязательное поле</label>');
                } else {
                    $('\<label class="control-label" id ="' + elem.attr('id') + '\_warning" for="input_login"> - обязательное поле</label>').insertBefore(elem);
                }
                elem.parent().addClass("has-error");
            }

            flag = false;
        }
    }
    return flag;

};