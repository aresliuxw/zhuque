
layui.use(['layer','jquery'], function() {
    var layer = layui.layer,
        $ = layui.jquery;


    //加载个人信息
    $.ajax({
        type: "post",
        url: "/user/getCurrUserInfo",
        dataType: "json",
        contentType: "application/json",
        success: function(res){
            if (res != null) {
                $('#user-info-headpic').attr('src','../' + res.headPic);
                $('#user-info-nickname').text(res.nickName);
                $('#user-info-age').text(res.age);
                $('#user-info-sex').text(res.sexTxt);
                $('#user-info-phone').text(res.phone);
                $('#user-info-email').text(res.email);
            }
        }
    });

    //点击更换头像
    $('#user-info-headpic').click(function(){
        $("#headpic-input-file").click();
    });
    $("#headpic-input-file").change(function () {
        var fileSourcePath = $(this).val();//图片源路径
        var size = $("#headpic-input-file")[0].files[0].size;
        if(parseInt(size/1024) > 500){
            layer.msg("图片大小:"+parseInt(size/1024)+"KB,超过了500KB");
        } else {
            var formData = new FormData();
            formData.append('file', $("#headpic-input-file")[0].files[0]);
            console.log(fileSourcePath);
            $.ajax({
                url: '/user/upload/headpic',
                type: 'post',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (res) {
                    layer.msg(res.msg);
                    $('#user-info-headpic').attr('src',res.data.echoPath);//上传成功后图片回显
                },
                error: function (res) {
                    layer.msg(res.msg);
                }
            });
        }
    });

});
