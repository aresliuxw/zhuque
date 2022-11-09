var form = layui.form;
var layer = layui.layer;
var layedit = layui.layedit;
var laydate = layui.laydate;
var $ = layui.$;


var loginIns = {
    init: function(){
        loginIns.login();
        loginIns.getvCode();
        //切换验证码
        $('#verifyimg').click(function(){
            loginIns.getvCode();
        });
    },

    login: function(){
        $('#loginBtn').click(function(){
            var param = {
                account : $('#account').val(),
                pwd : $('#pwd').val(),
                verifyCode : $('#verifyCode').val()
            };
            $.ajax({
                type: "post",
                url: "/loginSubmit",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(param),
                success: function(res){
                    if(res != null & res.code == '200'){
                        var token = common.getCookie("x-w-token");//登录成功后获取token
                        window.location.href = "/page/index?xw=" + token;//跳转首页
                    }else{
                        layer.msg(res.msg);
                    }
                }
            });
        });
    },

    /**
     * 获取验证码
     * 将验证码写到login.html页面的id = verifyimg 的地方
     */
    getvCode: function () {
        var url = "/verifyCode?timestamp=" + new Date().getTime();
        $('#verifyimg').attr('src',url);
    }

};

$(function(){
    loginIns.init();
});