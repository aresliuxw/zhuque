
layui.use(['layer','form','table','jquery'], function() {
    var table = layui.table,
        layer = layui.layer,
        form = layui.form,
        $ = layui.jquery;
    var user_add_layer;

    table.render({
        elem: '#user-table'
        ,height: 'full-175' //高度最大化减去差值
        ,url: '/user/getUsers'
        ,method: 'post'
        ,contentType: 'application/json'
        // ,skin: 'line' //表格风格
        // ,even: true
        ,limit: 10 //每页默认显示的数量  page=1&limit=30
        ,limits:[5,10,15,20,25,50]
        ,cellMinWidth: 200 //全局定义常规单元格的最小宽度
        ,page: true //开启分页
        ,request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
        ,toolbar: '#headToolBar'
        ,defaultToolbar: ['filter', 'exports', 'print']
        ,cols: [[ //表头
            {field: 'id', title: 'id'}
            ,{field: 'account', title: '账号', width:'20%', sort: true}
            ,{field: 'nickName', title: '昵称', width:'20%', sort: true}
            ,{field: 'phone', title: '手机', width:'20%'}
            ,{field: 'email', title: '邮箱', width:'20%'}
            ,{fixed: 'right', width:'20%', align:'center', toolbar: '#user-toolbar'}
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
            $("[data-field='id']").css('display','none');//隐藏id列
        }

    });

    //监听行工具条事件
    table.on('tool(user-filter)', function(obj){
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if(layEvent === 'assign-role'){ //弹出用户角色分配页面
            layer.open({
                type:2, //iframe层
                title: ['用户角色分配', 'font-size:18px;'],
                area: ['80%', '90%'],
                anim: 0,
                content: '/page/userrole?id=' + data.id
            });
        } else if(layEvent === 'edit-user') {//编辑用户
            layer.open({
                type: 1,
                title: ['编辑用户', 'font-size:18px;'],
                area: ['60%', '80%'],
                anim: 0,
                content: 'todo...'
            });
        } else if(layEvent === 'del-user') {//删除用户
            layer.confirm('确定删除?', {icon: 3, title:'提示'}, function(index){
                $.ajax({
                    type: "post",
                    url: "/user/delUser",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify({id : data.id}),
                    success: function(res){
                        layer.close(index);
                        layer.msg(res.msg);
                        table.reload('user-table', {
                            where: {
                                nickName: $('#nickName').val(),
                                account: $('#account').val()
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

    //监听头工具栏事件
    table.on('toolbar(user-filter)', function(obj){
        switch(obj.event){
            case 'export-all':
                var token = common.getCookie("x-w-token");//获取token
                window.location.href = "/excel/export/userList?export_xw=" + token;
                break;
            case 'import-users':
                // $("#user-input-file").click();
                $("#importfile").click();//点击file
                break;
        };
    });

    //模拟点击提交
    $("#importfile").change(function () {
        //点击submit按钮 提交文件
        $("#importbtn").click();

        //重载table
        table.reload('user-table', {
            where: {
                nickName: $('#nickName').val(),
                account: $('#account').val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });

        //清空file 否则change事件不起效果
        var finput = document.getElementById('importfile');
        finput.value = '';
    });

    //从Excel导入用户
    /*$("#user-input-file").change(function () {
        var fileSourcePath = $(this).val();//文件源路径
        var size = $("#user-input-file")[0].files[0].size;
        if(parseInt(size/2048) > 10){
            layer.msg("图片大小:"+parseInt(size/2048)+"M,请确保文件不超过10M");
        } else {
            var formData = new FormData();
            formData.append('file', $("#user-input-file")[0].files[0]);
            console.log(fileSourcePath);
            var index = layer.msg('数据导入中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,offset: 'auto', time:100000});
            $.ajax({
                url: '/excel/import/users',
                type: 'post',
                data: formData,
                cache: false,
                contentType: false,// 告诉jQuery不要去设置Content-Type请求头
                processData: false,// 告诉jQuery不要去处理发送的数据
                success: function (res) {
                    layer.close(index);
                    layer.msg(res.msg);
                    if (res.code != '200') {
                        var token = common.getCookie("x-w-token");//获取token
                        //todo 想直接下载失败的用户 但是list没法传 待做....
                        window.location.href = "/excel/export/userListData?export_xw=" + token;
                    }
                },
                error: function (res) {
                    layer.close(index);
                    layer.msg(res.msg);
                }
            });
        }
    });*/

    //查询
    $('#user-search-btn').click(function(){
        table.reload('user-table', {
            where: {
                nickName: $('#nickName').val(),
                account: $('#account').val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    //显示添加用户弹出层
    $('#user-add-btn').click(function(){
        user_add_layer = layer.open({
                                type: 1,//可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                title: ['添加用户', 'font-size:18px;'],
                                area: ['60%', '80%'],
                                anim: 0,
                                content: $('#add-user-div'),
                                success:function(){//解决弹出层 单选无法点击问题
                                    form.render();
                                }
                            });
    });

    //添加用户
    // $('#add-user-sub').click(function(){ //layui弹出层中此写法不起效果
    $(document).on('click', '#add-user-sub', function() {
        var param = {
                account: $('#add-account').val(),
                nickName: $('#add-nickname').val(),
                pwd1: $('#password1').val(),
                pwd2: $('#password2').val(),
                pwd: $('#password2').val(),//todo...
                sex: $('input[name="sex"]:checked').val(),
                phone: $('#add-phone').val(),
                email: $('#add-email').val()
            };
        if ($('#password1').val() != $('#password2').val()) {
            layer.msg('两次密码不一致');
            return;
        } else {
            $.ajax({
                type: "post",
                url: "/user/addUser",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(param),
                success: function(res){
                    layer.msg(res.msg,{time:1000});
                    if (res.code == '200') {
                        clearAddUserLayer();
                        layer.close(user_add_layer);
                        table.reload('user-table', {
                            where: {
                                nickName: $('#nickName').val(),
                                account: $('#account').val()
                            }
                            ,page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                    }
                }
            });
        }
    });

    //清空添加用户弹出层参数
    window.clearAddUserLayer = function() {
        $('#add-account').val('');
        $('#add-nickname').val('');
        $('#password1').val('');
        $('#password2').val(''),
        $('input[name="sex"]').val('0');
        $('#add-phone').val('');
        $('#add-email').val('');
    };




});
