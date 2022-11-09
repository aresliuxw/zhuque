
layui.use(['form', 'carousel', 'layer', 'laydate','jquery'], function() {
    var form = layui.form,
        carousel = layui.carousel,
        layer = layui.layer,
        $ = layui.jquery;

    carousel.render({
        elem: '#test1'
        ,width: '90%'
        ,height: '500px'
        ,interval: 1800
    });


});
