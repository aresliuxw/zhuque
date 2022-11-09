package com.cn.liu.constant;

/**
 * @Desc: 系统返回结果常量
 * @Author: lxw
 * @Date: 2021-01-09 14:52
 **/
public class ResultCode {

    /**成功*/
    public static final String SUCCESS = "200";

    /**操作失败*/
    public static final String OPERATE_FAIL = "900";

    /**用户不存在*/
    public static final String USER_NOT_EXIST = "901";

    /**用户名和密码不匹配*/
    public static final String USER_PASSWORD_NOT_MATCH = "902";

    /**账号已存在*/
    public static final String ACCOUNT_EXIST = "903";

    /**参数为空*/
    public static final String PARAM_NULL = "904";

    /**手机号已存在*/
    public static final String PHONE_EXIST = "905";

    /**会话过期*/
    public static final String TOKEN_EXPIRED = "997";

    /**系统异常*/
    public static final String SYSTEM_ERR = "998";



}
