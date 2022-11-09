package com.cn.liu.entity.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class PWritingsVo extends CommonVo {

    //标题
    private String title;

    //创建人(账号或昵称)
    private String creater;


}
