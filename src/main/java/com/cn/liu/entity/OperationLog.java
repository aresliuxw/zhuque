package com.cn.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * description: 操作日志实体类
 * author: lxw
 * time: 2020/8/25 16:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationLog {

    private String operId;
    private String operModule; // 操作模块
    private String operType;  // 操作类型
    private String operDesc;  // 操作说明
    private String operMethod;
    private String operRequParam;
    private String operRespParam;

}
