/**
 * Created by Олег on 02.11.2016.
 */
//two password and repeated password matcher
function passwordsMatcher(password, repeated) {
    var pass;
    var pass_rep;
    if (typeof password === 'undefined') {
        //set default
        pass = $("#password");
    } else {
        pass = password;
    }
    if (typeof password === 'undefined') {
        //set default
        pass_rep = $("#repeated_password");
    } else {
        pass_rep = repeated;
    }

    if (pass.val() !== pass_rep.val()) {
        if ( !pass_rep.parent().hasClass("has-error")) {
            pass_rep.parent().append('\<label class="control-label" id ="'+pass_rep.attr('id')+'\_warning" for="'+pass_rep.attr('id') + '\ ">Пароли не совпадают</label>');
            pass_rep.parent().addClass("has-error");
        }
        return false;
    } else {

        $('#'+pass_rep.attr('id')+'\_warning').remove();
        pass_rep.parent().removeClass("has-error");
        return true;
    }
};

