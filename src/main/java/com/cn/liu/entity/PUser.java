package com.cn.liu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * description:
 * author: lxw
 * time: 2020/8/3 18:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PUser extends CommonVO implements Serializable {
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

    //创建人id
    private String createUserId;

    private Long createUser;

    //验证码
    private String verifyCode;


}
