package org.zxy.repository.back.service;

import org.zxy.repository.back.entity.Role;
import org.zxy.repository.common.exception.AjaxResponse;

public interface RoleService{

    AjaxResponse deleteByPrimaryKey(Integer roleId);

    /**
     * 添加角色的同时分配权限
     */
    AjaxResponse insertSelective(Role record);

    AjaxResponse updateByPrimaryKeySelective(Role record);

    /**
     * 根据角色id查询拥有的资源列表
     * @param roleId 角色id
     * @return 角色信息以及该角色的资源列表
     */
    AjaxResponse selectRoleWithMenus(Integer roleId);

    AjaxResponse selectAll(Integer pageNum,Integer pageSize);

}
