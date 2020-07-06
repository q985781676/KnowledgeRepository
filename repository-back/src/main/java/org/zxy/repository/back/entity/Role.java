package org.zxy.repository.back.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    /**
     *
     */
    private Integer roleId;

    /**
    * 角色名称
    */
    private String roleName;

    /**
    * 角色描述
    */
    private String roleDesc;

    /**
    * 角色排序
    */
    private Integer sort;

    /**
     * 拥有的权限列表
     */
    private transient List<Menu> menus;
}