
layui.use(['table','jquery'], function() {
    var table = layui.table,
        layer = layui.layer,
        $ = layui.jquery;

    table.render({
        elem: '#role-table'
        ,height: 'full-175' //高度最大化减去差值
        ,url: '/role/getRoleList'
        ,method: 'post'
        ,contentType: 'application/json'
        // ,skin: 'line' //表格风格
        // ,even: true
        ,limit: 10 //每页默认显示的数量  page=1&limit=30
        ,limits:[10,15,20,25,50]
        ,cellMinWidth: 200 //全局定义常规单元格的最小宽度
        ,page: true //开启分页
        ,request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
        // ,where:{roleName : ''}
        ,cols: [[ //表头
            {field: 'roleId', title: '角色编码', width:'10%', sort: true, fixed: 'left'}
            ,{field: 'roleName', title: '角色名称', width:'30%', sort: true}
            ,{field: 'roleDesc', title: '描述', width:'30%'}
            ,{fixed: 'right', align:'center', toolbar: '#roleauth-toolbar'}
        ]]
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": 0, //解析接口状态
                "msg": res.msg, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.list //解析数据列表
            };
        }
        ,done: function(res, curr, count){

        }
    });

    //工具条事件
    table.on('tool(role-filter)', function(obj){
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event;
        if(layEvent === 'assign-auth'){ //弹出角色权限分配页面
            layer.open({
                type: 2,
                title: ['角色权限分配', 'font-size:18px;'],
                area: ['50%', '90%'],
                anim: 0,
                content: '/page/auth?roleId=' + data.roleId
            });
        } else if(layEvent === 'role-edit'){
            layer.open({
                type: 1,
                title: ['编辑角色', 'font-size:18px;'],
                area: ['80%', '90%'],
                anim: 0,
                content: 'edit'
            });
        } else if(layEvent === 'role-del'){
            layer.confirm('确定删除?', {icon: 3, title:'提示'}, function(index){
                $.ajax({
                    type: "post",
                    url: "/role/delRole",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify({roleId : data.roleId}),
                    success: function(res){
                        layer.close(index);
                        layer.msg(res.msg);
                        table.reload('role-table', {
                            where: {
                                roleName: $('#roleName').val()
                            }
                            ,page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                    }
                });
            });
        }
    });

    //查询
    $('#role-search-btn').click(function(){
        table.reload('role-table', {
            where: {
                roleName: $('#roleName').val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    //新增角色
    $('#role-add-btn').click(function(){
        layer.open({
            type: 1,
            title: ['新增角色', 'font-size:18px;'],
            area: ['80%', '90%'],
            anim: 0,
            content: 'add'
        });
    });



    //监听表格行点击
    // table.on('tr', function(obj){
    //     console.log(obj)
    // });

    //监听表格复选框选择
    // table.on('checkbox(test)', function(obj){
    //     console.log(obj)
    // });

    //监听表格单选框选择
    // table.on('radio(test2)', function(obj){
    //     console.log(obj)
    // });

    //监听单元格编辑
    // table.on('edit(test2)', function(obj){
    //     var value = obj.value //得到修改后的值
    //         ,data = obj.data //得到所在行所有键值
    //         ,field = obj.field; //得到字段
    //
    // });


    /*var $ = layui.jquery, active = {
        getCheckData: function(){//获取选中数据
            var checkStatus = table.checkStatus('test')
                ,data = checkStatus.data;
            layer.alert(JSON.stringify(data));
        }
        ,getCheckLength: function(){//获取选中数目
            var checkStatus = table.checkStatus('test')
                ,data = checkStatus.data;
            layer.msg('选中了：'+ data.length + ' 个');
        }
        ,isAll: function(){验证是否全选
            var checkStatus = table.checkStatus('test');
            layer.msg(checkStatus.isAll ? '全选': '未全选')
        }
        ,parseTable: function(){
            table.init('parse-table-demo', {
                limit: 3
            });
        }
        ,add: function(){
            table.addRow('test')
        }
        ,delete: function(){
            layer.confirm('确认删除吗？', function(index){
                table.deleteRow('test')
                layer.close(index);
            });
        }
        ,reload:function () {
            var keyWord=$("#keyWord").val();
            var keyType=$("#key_type option:selected").val();
            table.reload('contenttable',{
                where:{keyWord:keyWord,keyType:keyType}
            });
        }
    };
    $('i').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });*/


    //加载菜单
    // $.ajax({
    //     type: "post",
    //     url: "/system/menu/getUserMenus",
    //     dataType: "json",
    //     contentType: "application/json",
    //     // data: JSON.stringify(param),
    //     success: function(res){
    //
    //     }
    // });

});
