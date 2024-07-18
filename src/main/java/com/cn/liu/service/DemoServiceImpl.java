package com.cn.liu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cn.liu.dao.DemoMpper;
import com.cn.liu.dao.MenuMpper;
import com.cn.liu.entity.*;
import com.cn.liu.service.menu.MenuServiceInf;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    @Override
    public void exportUsers() {
        String[] tableArr = {"p_user", "p_user2"};
        for (String table : tableArr) {
            List<PUser> allUsers = demoMpper.getAllUsers(table);
            // 覆盖模式，也就是说如果文件已经存在，那么之前的内容《会被清除》
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\44674\\Desktop\\555.txt"));) {
                List<List<PUser>> partitions = Lists.partition(allUsers, 300);
                for (List<PUser> partition : partitions) {
                    for (PUser user : partition) {
                        String sql = "update " + table + " set sex = 0 where id = " + user.getId() + ";\n";
                        writer.write(sql);
                        // 插入新行
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // 追加模式，如果你想在文件末尾追加数据而不是覆盖，可以修改FileWriter构造函数如下：
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\44674\\Desktop\\666.txt", true));) {
                List<List<PUser>> partitions = Lists.partition(allUsers, 300);
                for (List<PUser> partition : partitions) {
                    for (PUser user : partition) {
                        String sql = "update " + table + " set sex = 0 where id = " + user.getId() + ";\n";
                        writer.write(sql);
                        // 插入新行
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
