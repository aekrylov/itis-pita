/**
 * Created by Олег on 29.03.2017.
 */
/**
items: array of objects like
 items = [
 {value:'inf', text:'informatic'},
 {value:'java', text:'java language'},
 {value:'.net', text:'.net framework'},
 {value:'itis-music', text:'itis-music'},
 ];
 Value and text: input VALUE will be like <input type="text" value="itis-music,java,inf" ...>,
 but showed selected tags will be like set of tags with TEXT: itis-music|x ,  java language|x, informatic|x ...
 value and text can match, this script allow create new tags with  matching value and text.
* */
function simpleTagsSelectizer(inputId, items) {
    $('#'+inputId).selectize({
        options: items,
        plugins: ['remove_button'],
        delimiter: ',',
        persist: false,
        create: function(input) {
            return {
                value: input,
                text: input
            }
        }
    });
}