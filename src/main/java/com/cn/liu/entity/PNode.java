package com.cn.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Desc: 树节点实体
 * @Author: lxw
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PNode implements Serializable {

    private static final long serialVersionUID = 1400921581443847154L;

    private String id;

    private String title;

    private String parentId;

    private Boolean checked;

    private Boolean leaf;

    private Boolean spread = true;

    private List<PNode> children;



}
