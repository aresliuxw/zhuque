package com.cn.liu.service.menu;


import com.cn.liu.entity.PUser;
import com.cn.liu.entity.Pmenu;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * description:
 * author: lxw
 */
public interface MenuServiceInf {

    /**
     * 获取用户菜单
     * @return
     */
    List<Pmenu> getUserMenus(PUser currentUser);


}
