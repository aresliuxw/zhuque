package com.cn.liu.dao;

import com.cn.liu.entity.Pmenu;
import com.cn.liu.entity.Prole;
import com.cn.liu.entity.params.PRoleMenuVo;
import com.cn.liu.entity.params.PRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * description:
 * author: lxw
 */
@Mapper
public interface RoleMpper {

    /**
     * 新增角色
     * @return
     */
    void addRole(@Param("param") Prole param);

    /**
     * 删除角色
     * @return
     */
    void delRole(@Param("roleCode") String roleCode);

    /**
     * 删除角色权限
     * @return
     */
    void delRoleAuth(@Param("roleId") Long roleId);

    /**
     * 获取角色列表
     * @return
     */
    List<Prole> getRoleList(@Param("param") PRoleVo param);

    /**
     * 根据权限id获取子集权限
     * @return
     */
    List<Pmenu> getSubAuths(@Param("menuId") String menuId, @Param("roleId") String roleId);

    /**
     * 删除角色菜单
     * @return
     */
    int deleteRoleMenus(@Param("param") PRoleMenuVo param);

    /**
     * 保存角色权限
     * @return
     */
    int saveRoleAuths(@Param("param") PRoleMenuVo param);


}
