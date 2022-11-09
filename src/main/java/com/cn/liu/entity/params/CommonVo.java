package com.cn.liu.entity.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc:
 * @Author: lxw
 * @Date: 2021-03-07 14:53
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CommonVo implements Serializable {

    //主键id
    private Long id;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    //开始时间
    private Date startDate;

    //结束时间
    private Date endDate;

}
