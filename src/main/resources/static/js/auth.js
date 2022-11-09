
layui.use(['tree', 'layer', 'jquery'], function() {
    var tree = layui.tree,
        layer = layui.layer,
        $ = layui.jquery;

    //加载权限树
    $.ajax({
        type: "post",
        url: "/role/getRoleAuths",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({roleId : $('#roleId').val()}),
        success: function(res){
            if(res != null && res.code == '200'){
                tree.render({
                    elem: '#auth-tree'
                    ,id: 'tree1'
                    ,showCheckbox: true
                    ,showLine: false
                    ,data: res.data
                });
            } else {
                layer.msg(res.msg);
            }
        }
    });

    //保存
    $('#auth-save-btn').click(function(){
        var selectdata = tree.getChecked('tree1');
        var menuIdList = getCheckedId(selectdata).split(",");
        var param = {
            roleId: parseInt($('#roleId').val()),
            menuIdList: menuIdList
        };
        $.ajax({
            type: "post",
            url: "/role/saveRoleAuths",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(param),
            success: function(res){
                layer.msg(res.msg);
            }
        });
    });

    function getCheckedId (data) {
        console.log(data);
        var id = "";
        $.each(data, function (index, item) {
            if (!item.leaf) {
                var i = getCheckedId(item.children);
                if (i != "") {
                    id = id + "," + i;
                }
            } else {
                if (id != "") {
                    id = id + "," + item.id;
                } else {
                    id = item.id;
                }
            }
        });
        return id;
    }

});
