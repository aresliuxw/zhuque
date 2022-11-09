package com.cn.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * description: 文章实体类
 * author: lxw
 * time: 2020/8/3 18:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PWritings {

    //主键id(文章id)
    private Long id;

    //标题
    private String title;

    //内容
    private String content;

    //创建人id
    private Long createrId;

    //创建时间
    private Date createTime;

    //修改人id
    private Long updateId;

    //修改时间
    private Date updateTime;


}
