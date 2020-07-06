package org.zxy.repository.front.entity;

import java.util.Date;
import lombok.Data;

@Data
public class User {
    private Integer userId;

    private String username;

    private String password;

    /**
     * 是否被禁用？0：1
     */
    private Integer enabled;


    private Date createTime;

    private String phone;

    /**
    * 城市
    */
    private String city;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 头像
    */
    private String headpicurl;

    /**
    * 0：女，1：男
    */
    private Integer sex;
}