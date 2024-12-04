package com.cn.liu.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class ImportRequest {

    @ExcelProperty(value = "项目")
    private String projectName;

    @ExcelProperty(value = "地址")
    private String address;

    @ExcelProperty(value = "类型")
    private String type;

}
