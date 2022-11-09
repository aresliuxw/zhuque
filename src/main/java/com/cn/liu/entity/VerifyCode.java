package com.cn.liu.entity;

import lombok.Data;

/**
 * @Desc: 验证码实体类
 * @Author: lxw
 **/
@Data
public class VerifyCode {

    private String code;

    private byte[] imgBytes;

    private long expireTime;

}
