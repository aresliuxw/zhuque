
layui.use(['form','layer','table','jquery'], function() {
    var table = layui.table,
        layer = layui.layer,
        form = layui.form,
        $ = layui.jquery;

    table.render({
        elem: '#userrole-table'
        ,height: 'full-150' //高度最大化减去差值
        ,url: '/user/getUserRole'
        ,method: 'post'
        ,contentType: 'application/json'
        ,cellMinWidth: 200 //全局定义常规单元格的最小宽度
        ,page: false //不开启分页
        ,limit: Number.MAX_VALUE // 数据表格默认全部显示
        ,where: {
            id: $('#userId').val(),
            roleName: $('#roleName').val()
        }
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'roleId', title: 'roleId'}
            ,{field: 'roleName', title: '角色名称', width:'35%', sort: true}
            ,{field: 'roleDesc', title: '描述', width:'35%', sort: true}
        ]]
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": 0, //解析接口状态
                "msg": res.msg, //解析提示文本
                "data": res.data //解析数据列表
            };
        }
        ,done: function(res, curr, count){
            $("[data-field='roleId']").css('display','none');//隐藏id列
            $.each(res.data, function (i,item) {
                if (item.related === '1') {//复选框动态勾选
                    $(".layui-table tr[data-index="+ i +"] input[type='checkbox']").prop("checked",true)
                }
            });
            form.render('checkbox');
        }

    });

    //查询
    $('#userrole-search-btn').click(function(){
        table.reload('userrole-table', {
            where: {
                id: $('#userId').val(),
                roleName: $('#roleName').val()
            }
        });
    });

    //保存
    $('#userrole-save-btn').click(function(){
        var roleIdList = [];
        var selectdata = layui.table.checkStatus('userrole-table').data;   // table1为表格id
        $.each(selectdata, function (i,item) {
            roleIdList.push(parseInt(item.roleId));
        });
        var param = {
            userId: $('#userId').val(),
            roleIdList: roleIdList
        };
        $.ajax({
            type: "post",
            url: "/user/addUserRoles",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(param),
            success: function(res){
                layer.msg(res.msg);
                table.reload('userrole-table', {
                    where: {
                        id: $('#userId').val(),
                        roleName: $('#roleName').val()
                    }
                });
            }
        });
    });

});
