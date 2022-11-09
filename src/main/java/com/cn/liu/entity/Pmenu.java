package com.cn.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Desc: 菜单实体
 * @Author: lxw
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pmenu {

    private String id;

    private String menuCode;

    private String menuName;

    private String menuUrl;

    private String menuOrder;

    private String enable;

    private String parentId;

    private int menuLvl;

    private String leaf;

    //该菜单是否与当前角色关联 1是 0否
    private String related;

    private List<Pmenu> children;



}
