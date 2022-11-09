package com.cn.liu.entity.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class PUserVo extends CommonVo {

    //用户账号
    private String account;

    //密码1
//    private String pwd1;

    //密码2
//    private String pwd2;

    private String pwd;

    //昵称
    private String nickName;

    //性别
    private String sex;

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

    //修改时间
    private Date updateTime;

    //创建人账号 如zhangsan
    private String createUserId;


}
