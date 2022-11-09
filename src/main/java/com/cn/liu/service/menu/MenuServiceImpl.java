package com.cn.liu.service.menu;

import com.cn.liu.dao.MenuMpper;
import com.cn.liu.entity.PUser;
import com.cn.liu.entity.Pmenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * description:
 * author: lxw
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuServiceInf {

    @Autowired
    MenuMpper menuMpper;

    /**
     * 获取用户菜单
     * @return
     */
    @Override
    public List<Pmenu> getUserMenus(PUser currentUser) {
        List<Pmenu> menusList = menuMpper.getSubMenus("0",String.valueOf(currentUser.getId()));
        List<Pmenu> list = getChildren(menusList,String.valueOf(currentUser.getId()));
        return list;
    }

    //递归树形结构菜单
    private List<Pmenu> getChildren(List<Pmenu> menusList, String userId){
        for(Pmenu o : menusList){
            List<Pmenu> list = menuMpper.getSubMenus(o.getId(),userId);
            if(!CollectionUtils.isEmpty(list)){
                o.setChildren(list);
                getChildren(list,userId);
            }
        }
        return menusList;
    }
}
