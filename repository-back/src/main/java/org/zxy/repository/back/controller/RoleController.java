package org.zxy.repository.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zxy.repository.back.entity.Role;
import org.zxy.repository.back.service.RoleService;
import org.zxy.repository.common.exception.AjaxResponse;

/**
 * @author 98578
 * @create 2020-07-06 21:19
 */
@RequestMapping("/role")
@RestController
public class RoleController {

  @Autowired private RoleService roleService;

  @RequestMapping("/delete/{roleId}")
  public AjaxResponse delete(@PathVariable Integer roleId) {
    return roleService.deleteByPrimaryKey(roleId);
  }

  /** 添加角色的同时分配权限 */
  @RequestMapping("/save")
  public AjaxResponse save(@RequestBody Role record) {
    return roleService.insertSelective(record);
  }

  @RequestMapping("/update")
  public AjaxResponse update(@RequestBody Role record) {
    return roleService.updateByPrimaryKeySelective(record);
  }

  /**
   * 根据角色id查询拥有的资源列表
   *
   * @param roleId 角色id
   * @return 角色信息以及该角色的资源列表
   */
  @RequestMapping("/info/{roleId}")
  public AjaxResponse selectRoleWithMenus(@PathVariable Integer roleId) {
    return roleService.selectRoleWithMenus(roleId);
  }

  @RequestMapping("/list")
  public AjaxResponse selectAll(
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    return roleService.selectAll(pageNum, pageSize);
  }
}
