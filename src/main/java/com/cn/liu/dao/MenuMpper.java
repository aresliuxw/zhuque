package com.cn.liu.dao;

import com.cn.liu.entity.Pmenu;
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
public interface MenuMpper {

    /**
     * 根据菜单id获取子集菜单
     * @return
     */
    List<Pmenu> getSubMenus(@Param("menuId") String menuId, @Param("userId") String userId);


}
