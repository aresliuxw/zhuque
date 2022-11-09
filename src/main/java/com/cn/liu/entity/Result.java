package com.cn.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Desc: 返回结果
 * @Author: lxw
 * @Date: 2020-12-20 15:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> {

    private String code;

    private String msg;

    private T data;

}
