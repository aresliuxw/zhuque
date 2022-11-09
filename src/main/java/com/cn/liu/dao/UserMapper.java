package com.cn.liu.dao;

import com.cn.liu.entity.PUser;
import com.cn.liu.entity.Prole;
import com.cn.liu.entity.params.PUserRoleVo;
import com.cn.liu.entity.params.PUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * description:
 * author: lxw
 * time: 2020/8/3 10:46
 */
@Mapper
public interface UserMapper {

//    List<PUser> getUserData(@Param("pagelimit") Integer pagelimit, @Param("pagesize") Integer pagesize);

    List<PUser> getAllUserData();

    /**
     * 根据account获取用户
     * @param account
     * @return
     */
    PUser getUserByAccount(@Param("account") String account);

    /**
     * 新增用户
     * @return
     */
    int addUser(@Param("user") PUserVo user);

    /**
     * 删除用户
     * @return
     */
    int delUser(@Param("userId") Long userId);

    /**
     * 修改用户信息
     * @return
     */
    int updUser(@Param("user") PUser user);

    /**
     * 删除用户角色
     * @return
     */
    int delUserRole(@Param("userId") Long userId);

    /**
     * 检验用户是否已存在
     * @return
     */
    List<PUser> chkUserExist(@Param("user") PUserVo user);

    /**
     * 检验手机号是否已存在
     * @return
     */
    List<PUser> chkPhoneExist(@Param("user") PUserVo user);

    /**
     * 获取用户角色
     * @return
     */
    List<Prole> getUserRole(@Param("userRole") PUserRoleVo userRole);

    /**
     * 获取用户列表
     * @return
     */
    List<PUser> getUsers(@Param("user") PUserVo user);

    /**
     * 根据账户获取用户信息
     * @return
     */
    PUser getUserInfo(@Param("userAccount") String userAccount);

    /**
     * 删除角色菜单
     * @return
     */
    int deleteUserRoles(@Param("param") PUserRoleVo param);

    /**
     * 用户分配角色
     * @return
     */
    int addUserRoles(@Param("param") PUserRoleVo param);

}
