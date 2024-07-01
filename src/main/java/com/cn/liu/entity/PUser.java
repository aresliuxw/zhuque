package com.cn.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * description:
 * author: lxw
 * time: 2020/8/3 18:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PUser implements Serializable {
    private static final long serialVersionUID = 1L;

    public PUser(String account, String nickName) {
        this.account = account;
        this.nickName = nickName;
    }

    //主键id(用户id)
    private Long id;

    //用户账号
    private String account;

    //密码
    private String pwd;

    //昵称
    private String nickName;

    //性别
    private String sex;

    //性别文本
    private String sexTxt;

    //年龄
    private Integer age;

    //出生日期
    private Date birthDate;

    //手机
    private String phone;

    //邮箱
    private String email;

    //头像地址
    private String headPic;

    //创建时间
    private Date createTime;

    //创建人id
    private String createUserId;

    //验证码
    private String verifyCode;


}
