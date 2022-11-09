package com.cn.liu.entity.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * description:
 * author: lxw
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PUserRoleVo extends CommonVo {

    //用户id
    private Long userId;

    //角色名称
    private String roleName;

    //角色id列表
    private List<Long> roleIdList;

}
