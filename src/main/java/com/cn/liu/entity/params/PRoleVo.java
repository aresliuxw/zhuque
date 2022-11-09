package com.cn.liu.entity.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * description: 角色实体类
 * author: lxw
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PRoleVo extends CommonVo {

    //角色名称
    private String roleName;

}
