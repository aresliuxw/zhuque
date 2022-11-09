package com.cn.liu.service.user;

import com.cn.liu.entity.PUser;
import com.cn.liu.entity.Prole;
import com.cn.liu.entity.Result;
import com.cn.liu.entity.params.PUserRoleVo;
import com.cn.liu.entity.params.PUserVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * description:
 * author: lxw
 */
public interface UserServiceInf {

    /**
     * 验证手机号
     * @return
     */
    List<PUser> chkPhoneExist(PUserVo user);

    /**
     * 验证账户
     * @return
     */
    List<PUser> chkUserExist(PUserVo user);

    /**
     * 新增用户
     * @return
     */
    Result addUser(PUserVo user);

    /**
     * 删除用户
     * @return
     */
    Result delUser(PUser user);

    /**
     * 修改用户信息
     * @return
     */
    Result updUser(PUser user);

    /**
     * 获取用户角色
     * @return
     */
//    PageInfo<Prole> getUserRole(PUserRoleVo userRole);
    List<Prole> getUserRole(PUserRoleVo userRole);

    /**
     * 获取用户列表
     * @return
     */
    PageInfo<PUser> getUsers(PUserVo user);

    /**
     * 根据账户获取用户信息
     * @return
     */
    PUser getUserInfo(String userAccount);

    /**
     * 用户分配角色
     * @return
     */
    Result addUserRoles(PUserRoleVo param);


}
