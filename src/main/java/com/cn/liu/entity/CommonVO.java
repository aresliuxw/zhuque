package com.cn.liu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long updateUser;

    private Long createDept;

}
