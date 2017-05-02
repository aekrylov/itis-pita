/**
 * Created by Олег on 01.05.2017.
 */
/**
 * function which prepaired form to send ajax for change pass on settings.ftl
 * need follow imports: jquery.form.js removeHasError.js, checkform.js, passwordsMatcher.js
 * */
function changePassPrepare() {
//<#-- create options with pre-submit handlers and success callback-->
    var formToSend = $('#passwordChangeForm');
    var errorsMap = new Map();
    errorsMap.set('new_password', "Новый пароль должен содержать от 8 до 30 символов.");
    errorsMap.set('old_password', "Старый пароль введен неверно.");
    var options = {
        beforeSubmit: function(arr, $form, options) {
            return checkform(['new_password', 'old_password', 'password_confirmed'])
                && passwordsMatcher($('#new_password'),$('#password_confirmed'));
        },
        success: function(obj) {
            var errors_fields = obj.errors_fields;
            if (typeof errors_fields === 'undefined' || errors_fields.length === 0) {
                //all right - remove values from modal, add info, remove all errors warnings.
                formToSend.resetForm();
                $('#change_password_modal').modal('toggle');
                $('#alert_container').html('\<div class="alert alert-success" id="alert_container_warning" role="alert">Пароль изменен.</div>');
                formToSend.find('input').map(function(){removeHasError(this.id)});

            } else {
                //get name of wrong field
                for (var i = 0; i < errors_fields.length; i++) {
                    var field = formToSend.find('input[name="' + errors_fields[i] + '"]');
                    if ( !field.parent().hasClass("has-error")) {
                        field.parent().append('\<label class="control-label"  style="text-align: left;" id ="'+field.attr('id')+'\_warning" for="'+field.id + '\ ">' + errorsMap.get(errors_fields[i]) + '\</label>');
                        field.parent().addClass("has-error");
                    }
                    field.focus(function(){removeHasError($(this).attr('id'))});
                }
            }
        }
    };
//<#-- bind 'passwordChangeForm' and provide a simple callback function-->
    formToSend.ajaxForm(options);
}