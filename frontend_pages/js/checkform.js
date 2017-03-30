/**
 * Created by Олег on 31.10.2016.
 */
//args: array of id's
function checkform(fields) {
    var flag = true;
    for (var i = 0; i < fields.length; i++) {
        if ($("#" + fields[i]).val() === '') {
            //remove old notification about empty login
            if ($("#" + fields[i]).val() !== '' && $("#" + fields[i]).parent().children().is($("#" + fields[i] + "_warning"))){
                $("#" + fields[i] + "_warning").remove();
                $("#" + fields[i]).parent().removeClass("has-error");

            }
            //add notification about empty login
            if ($("#" + fields[i]).val() === '' && !$("#" + fields[i]).parent().children().is($("#" + fields[i] + "_warning"))){
                $("#" + fields[i]).parent().append('\<label class="control-label" id ="'+fields[i]+'\_warning" for="input_login">Обязательное поле</label>');
                $("#" + fields[i]).parent().addClass("has-error");
            }

            flag = false;
        }
    }
    return flag;

};