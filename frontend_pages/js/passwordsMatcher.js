/**
 * Created by Олег on 02.11.2016.
 */

function passwordsMatcher() {
    var pass = $("#password").val();
    var pass_rep = $("#repeated_password").val();

    if (pass !== pass_rep) {
        if ( !$("#repeated_password").parent().hasClass("has-error")) {
            $("#repeated_password").parent().append('\<label class="control-label" id ="repeated_password_warning" for="repeated_password">Пароли не совпадают</label>');
            $("#repeated_password").parent().addClass("has-error");
        }
        return false;
    } else {

        $("#passwords_not_matching_warning").remove();
        $("#repeated_password").parent().removeClass("has-error");
        return true;
    }
};

