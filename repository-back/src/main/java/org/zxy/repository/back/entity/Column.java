package org.zxy.repository.back.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Column {
    /**
    * 类别编号
    */
    private Integer columnId;

    /**
    * 类别名称
    */
    private String columnName;

    /**
    * 类别描述
    */
    private String columnDesc;

    /**
    * 类别封面
    */
    private String surface;

    /**
    * 创建时间
    */
    private Date createTime;
}