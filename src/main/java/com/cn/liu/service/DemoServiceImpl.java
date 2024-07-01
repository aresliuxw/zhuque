package com.cn.liu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cn.liu.dao.DemoMpper;
import com.cn.liu.dao.MenuMpper;
import com.cn.liu.entity.*;
import com.cn.liu.service.menu.MenuServiceInf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * description:
 * author: lxw
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoServiceInf {

    @Autowired
    DemoMpper demoMpper;


    @Override
    public List<PUser> getUsers() {
        List<PUser> users = new ArrayList<>();
        PUser u1 = new PUser("lxw","昵称1");
        PUser u2 = new PUser("zs","昵称2");
        users.add(u1);
        users.add(u2);
        List<PUser> byConditions = demoMpper.getByConditions(users);
        return byConditions;
    }


}
