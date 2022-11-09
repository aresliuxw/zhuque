
layui.use(['form', 'layer', 'layedit', 'laydate','element','jquery'], function() {
    var form = layui.form,
        layer = layui.layer,
        element = layui.element,//导航的hover效果、二级菜单等功能，需要依赖element模块
        $ = layui.jquery;

    //导航栏监听
    element.on('nav(index-nav)', function(elem) {
        // var id = elem.attr('data-id');
        var url = elem.attr('menu-url');
        // var text = elem.attr('data-text');
        // layer.msg('menu-url:'+url);
        // window.location.href = url;
        $("#iframeMainId").attr("src",url);
    });

    //加载菜单
    /*$.ajax({
        type: "post",
        url: "/system/menu/getUserMenus",
        dataType: "json",
        contentType: "application/json",
        // data: JSON.stringify(param),
        success: function(res){
            if(res != null && res.code == '200'){
                var html = "<li class=\"layui-nav-item layui-this\"><a href=\"javascript:;\" menu-url='/page/indexpage'>首页</a></li>";
                $.each(res.data, function (i,menu) {
                    html += "<li class=\"layui-nav-item\">";
                    if (menu.children != null && menu.children.length > 0) {//有子级菜单
                        html += "<a href=\"javascript:;\" menu-url='" + menu.menuUrl +"'>" + menu.menuName + "<span class=\"layui-nav-more\"></span></a>";
                        html += "<dl class=\"layui-nav-child\">";
                        $.each(menu.children, function (j,children) {
                            html += "<dd><a href=\"javascript:;\" menu-url='" + children.menuUrl +"'>" + children.menuName + "</a></dd>";
                        });
                        html += "</dl>";
                    } else {//无子级菜单 不需要layui-nav-more属性
                        html += "<a href=\"javascript:;\"  menu-url='" + menu.menuUrl +"'>" + menu.menuName + "</span></a>";
                    }
                    html += "</li>";
                });
                $('#index-nav-ul').html(html);
                element.render('nav(index-nav)');//重新渲染nav
            }else{
                layer.msg('暂无权限');
            }
        }
    });*/

    //我的信息
    $('#index-myinfo').click(function(){
        $("#iframeMainId").attr("src","/page/userInfo");
    });

    //安全退出
    $('#index-logout').click(function(){
        var param = {
            account : $('#index-user').attr('name')
        };
        $.ajax({
            type: "post",
            url: "/logout",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(param),
            success: function(res){
                if(res != null & res.code == '200'){
                    window.location.href = "/page/login";//退出成功后跳转登录页
                }else{
                    layer.msg(res.msg);
                }
            }
        });
    });


});
