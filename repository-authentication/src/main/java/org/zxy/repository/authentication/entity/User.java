package org.zxy.repository.authentication.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer userId;

    private String username;

    private String password;

    private Integer enabled;

    private Date createTime;

    private String phone;
}

