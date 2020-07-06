package org.zxy.repository.back.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Item {
    /**
    * 文章id
    */
    private Integer itemId;

    /**
    * 标题
    */
    private String title;

    /**
    * 内容
    */
    private String content;

    /**
    * 封面url
    */
    private String surface;

    /**
    * 类型
    */
    private Integer tag;

    /**
    * 发布日期
    */
    private Date publishTime;

    /**
    * 发布用户id
    */
    private Integer userId;

    /**
    * 专栏id
    */
    private Integer columnId;
}