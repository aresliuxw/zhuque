package com.cn.liu.entity.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * description: 角色关联菜单实体类
 * author: lxw
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PRoleMenuVo {

    //角色id
    private Long roleId;

    //菜单id列表
    private List<Long> menuIdList;

}
