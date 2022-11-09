package com.cn.liu.controller;


import com.cn.liu.entity.PUser;
import com.cn.liu.service.user.UserServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * html动态页面跳转controller
 */
@Controller
@CrossOrigin
@RequestMapping("/page")
public class PageController {

    @Autowired
    UserServiceInf userService;


    /**
     * 首页
     * @return
     */
    @GetMapping("/index")
    public ModelAndView toIndexPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/index");
        return modelAndView;
    }

    /**
     * 访问首页页面
     * @return
     */
    @GetMapping("/indexpage")
    public String toindexpagePage(){
        return "page/indexpage";
    }


    /**
     * 访问用户信息页面
     * @return
     */
    @GetMapping("/userInfo")
    public String toUserInfoPage(){
        return "page/userInfo";
    }

    /**
     * 跳转用户管理页面
     * @return
     */
    @GetMapping("/user")
    public String toAuthPage(){
        return "page/user";
    }

    /**
     * 跳转用户角色页面
     * @param id 用户id
     * @return
     */
    @GetMapping("/userrole")
    public ModelAndView toUserRolePage(@RequestParam("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/userrole");
        modelAndView.addObject("userId", id);
        return modelAndView;
    }

    /**
     * 跳转角色权限页面
     * @param roleId
     * @return
     */
    @GetMapping("/auth")
    public ModelAndView toAuthPage(@RequestParam("roleId") Long roleId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/auth");
        modelAndView.addObject("roleId", roleId);
        return modelAndView;
    }

    /**
     * 访问角色管理页面
     * @return
     */
    @GetMapping("/role")
    public String toRolePage(){
        return "page/role";
    }


}

