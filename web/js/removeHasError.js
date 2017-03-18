/**
 * Created by Олег on 18.03.2017.
 */
function removeHasError (fieldid){
    var textfield = $("#"+fieldid);
    textfield.parent().removeClass("has-error");
    $("#" + fieldid + "_warning").remove();
};