package com.cn.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Desc: 角色实体
 * @Author: lxw
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prole {

    //id
    private Long id;

    //角色编码
    private String roleCode;

    //角色名称
    private String roleName;

    //描述
    private String roleDesc;

    //角色是否与当前用户关联 1是 0否
    private String related;

}
