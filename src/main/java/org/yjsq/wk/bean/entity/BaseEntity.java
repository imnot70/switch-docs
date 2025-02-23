package org.yjsq.wk.bean.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    public static final Integer STATUS_NORMAL = 0;

    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;

}
