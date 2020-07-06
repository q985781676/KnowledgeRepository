package org.zxy.repository.front.entity;

import java.util.Date;
import java.util.List;

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
    * 发布用户
    */
    private User user;

    /**
    * 专栏
    */
    private Column column;

    /**
     * 评论数
     */
    private transient Integer commentNum;

    /**
     * 评论列表
     */
    private transient List<Comment> comments;
}