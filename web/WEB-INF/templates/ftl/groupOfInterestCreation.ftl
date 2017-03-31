<html>
<head>
    <meta charset="utf-8">
</head>

<body>
<form role="form" class="" action="/groups/create" method="POST" enctype="multipart/form-data" onsubmit="return checkform([
            'group_name', 'group_info', 'group_tags'
        ])">
    <div class="row">
        <div class="col-xs-offset-1 col-md-5">

            <div class="form-group">
                <label class="control-label" for="group_name">Name</label>
                <input onfocus="removeHasError('group_name')" type="text" minlength="3" class="form-control"
                       name="group_name"
                       id="group_name"
                       placeholder="..введите название группы"/>

            </div>

            <div class="form-group" onclick="removeHasError('group_tags')">
                <label class=" control-label">Interests</label>
                <input type="text" id="group_tags" class="selectized"
                       tabindex="-1" style="display: none;">
            </div>


        </div>
        <div class="col-md-5">
            <div class="form-group">
                <label class=" control-label">Information about group</label>
                        <textarea class="form-control" style="resize: none"
                                  onfocus="removeHasError('group_info')" rows="5" name="group_info"
                                  id="group_info"></textarea>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="form-group">
                <button class="btn-warning btn btn-block" type="submit">Создать</button>
            </div>
        </div>
    </div>

</form>
<script src="/js/checkform.js"></script>
<script src="/js/removeHasError.js"></script>
<script src="/js/simpleTagsSelectizer.js"></script>
<script type="application/javascript">
    //see groupTagsSelectizer for more information about items
    items = [
        {value:'inf', text:'informatic'},
        {value:'java', text:'java language'},
        {value:'.net', text:'.net framework'},
        {value:'itis-music', text:'itis-music'}
    ];
    $.onload = simpleTagsSelectizer('group_tags',items);
</script>
</body>
</html>