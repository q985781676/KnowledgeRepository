package org.zxy.repository.back.entity;

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
    * (发布评论的)用户编号
    */
    private String userId;

    /**
    * 发布时间
    */
    private Date publishTime;

    private String content;
}