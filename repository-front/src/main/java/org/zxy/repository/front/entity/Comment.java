package org.zxy.repository.front.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Comment {
    private Integer id;

    /**
    * 文章(问题)编号
    */
    private Integer itemId;

    /**
    * (发布评论的)用户
    */
    private User user;

    /**
    * 发布时间
    */
    private Date publishTime;

    private String content;
}