package com.cn.liu.dao;

import com.cn.liu.entity.PUser;
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
public interface DemoMpper {

    List<PUser> getByConditions(@Param("userVOS") List<PUser> userVOS);

    List<PUser> getAllUsers(@Param("table") String table);

    int insertUser(@Param("user") PUser user);



}
