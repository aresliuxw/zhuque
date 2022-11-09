package com.cn.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: bootdemo
 * @description: 异常日志实体类
 * @author: lxw
 * @create: 2020-08-25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionLog {

    private String excId;

    private String excRequParam;

    private String operMethod;

    private String excName;

    private String excMessage;

}
