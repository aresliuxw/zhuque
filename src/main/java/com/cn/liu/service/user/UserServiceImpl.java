package com.cn.liu.service.user;

import com.alibaba.fastjson.JSON;
import com.cn.liu.constant.ResultCode;
import com.cn.liu.dao.UserMapper;
import com.cn.liu.entity.PUser;
import com.cn.liu.entity.Prole;
import com.cn.liu.entity.Result;
import com.cn.liu.entity.params.PUserRoleVo;
import com.cn.liu.entity.params.PUserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * description:
 * author: lxw
 */
@Service
@Slf4j
public class UserServiceImpl implements UserServiceInf {

    @Autowired
    UserMapper userMpper;


    /**验证手机号*/
    @Override
    public List<PUser> chkPhoneExist(PUserVo user) {
        List<PUser> phoneList = userMpper.chkPhoneExist(user);
        return phoneList;
    }

    /**验证账户*/
    @Override
    public List<PUser> chkUserExist(PUserVo user) {
        List<PUser> usrList = userMpper.chkUserExist(user);
        return usrList;
    }

    /**
     * 新增用户
     * @return
     */
    @Override
    public Result addUser(PUserVo user) {
        try {
            List<PUser> phoneList = this.chkPhoneExist(user);
            if(!CollectionUtils.isEmpty(phoneList)){
                return Result.builder().code(ResultCode.PHONE_EXIST).msg("手机号码已存在").build();
            }
            List<PUser> usrList = this.chkUserExist(user);
            if(!CollectionUtils.isEmpty(usrList)){
                return Result.builder().code(ResultCode.ACCOUNT_EXIST).msg("账号已存在").build();
            }
            //TODO 密码 先RSA解密 再md5密码
//            String pwd1 = user.getPwd1();
            userMpper.addUser(user);
            return Result.builder().code(ResultCode.SUCCESS).msg("添加成功").build();
        } catch (Exception e) {
            log.error("新增用户失败:",e);
            return Result.builder().code(ResultCode.SYSTEM_ERR).msg("新增用户失败").build();
        }
    }

    /**
     * 删除用户
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delUser(PUser user) {
        try {
            userMpper.delUser(user.getId());
            userMpper.delUserRole(user.getId());//同时删除关联角色
            return Result.builder().code(ResultCode.SUCCESS).msg("删除成功").build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//必须
            log.error("删除用户失败:",e);
            return Result.builder().code(ResultCode.SYSTEM_ERR).msg("删除用户失败").build();
        }
    }

    /**
     * 修改用户信息
     * @return
     */
    @Override
    public Result updUser(PUser user) {
        int i = userMpper.updUser(user);
        if (i > 0) {
            return Result.builder().code(ResultCode.SUCCESS).msg("修改成功").build();
        } else {
            return Result.builder().code(ResultCode.OPERATE_FAIL).msg("修改失败").build();
        }
    }

    /**
     * 获取用户角色
     * @return
     */
    @Override
    /*public PageInfo<Prole> getUserRole(PUserRoleVo userRole) {
        PageHelper.startPage(userRole.getPageNum(),userRole.getPageSize());
        List<Prole> userRoleList = userMpper.getUserRole(userRole);
        PageInfo<Prole> pageInfo = new PageInfo<>(userRoleList);
        return pageInfo;
    }*/
    public List<Prole> getUserRole(PUserRoleVo userRole) {
        List<Prole> userRoleList = userMpper.getUserRole(userRole);
        return userRoleList;
    }

    /**
     * 获取用户列表
     * @return
     */
    @Override
    public PageInfo<PUser> getUsers(PUserVo user) {
        PageHelper.startPage(user.getPageNum(),user.getPageSize());
        List<PUser> userList = userMpper.getUsers(user);
        PageInfo<PUser> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    /**
     * 根据账户获取用户信息
     * @return
     */
    @Override
    public PUser getUserInfo(String userAccount) {
        PUser user = userMpper.getUserInfo(userAccount);
        return user;
    }

    /**
     * 用户分配角色
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addUserRoles(PUserRoleVo param) {
        try {
            //先删除
            userMpper.deleteUserRoles(param);
            //再新增
            userMpper.addUserRoles(param);
            return Result.builder().code(ResultCode.SUCCESS).msg("操作成功").build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("系统异常",e);
            return Result.builder().code(ResultCode.SYSTEM_ERR).msg("系统异常").build();
        }
    }


}
