package com.cn.liu.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderSequenceMapper {

//    @Select("SELECT LAST_INSERT_ID()")
    Long getCurrentSequence();

    // 合并初始化与更新操作
//    @Update("INSERT INTO order_sequence (biz_date, current_sequence) " +
//            "VALUES (#{bizDate}, LAST_INSERT_ID(1)) " +
//            "ON DUPLICATE KEY UPDATE current_sequence = LAST_INSERT_ID(current_sequence + 1)")
    void upsertSequence(String bizDate);
}
