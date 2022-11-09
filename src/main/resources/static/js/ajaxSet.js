
layui.use(['jquery'], function() {
    var $ = layui.jquery;

    $.ajaxSetup( {
        //给所有ajax请求头设置token
        beforeSend: function(jqXHR, settings) {
            var token = common.getCookie("x-w-token");
            /*header里加请求头参数*/
            jqXHR.setRequestHeader('x-w-token', token)
        },

        //设置ajax请求结束后的执行动作
        complete : function(XMLHttpRequest, textStatus) {
            // 通过XMLHttpRequest取响应头中信息,判断是否是重定向
            var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");
            if (redirect === "REDIRECT") {
                var msg = XMLHttpRequest.getResponseHeader("MSG");
                if(msg === 'SESSIONEXPIRED'){
                    msg = '会话已过期,请重新登录';
                }
                layer.confirm(msg, {icon: 3, title:'提示'}, function(index){
                    window.top.location.href= XMLHttpRequest.getResponseHeader("PATH");//取出路径，重定向
                    layer.close(index);
                });
            }
        }
    });
});
