package org.zxy.repository.back.entity;

import lombok.Data;

@Data
public class Menu {
    private Integer menuId;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 可访问资源
     */
    private String code;

    /**
     * 资源描述
     */
    private String desc;
}