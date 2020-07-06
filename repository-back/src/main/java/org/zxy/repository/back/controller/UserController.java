package org.zxy.repository.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.back.entity.User;
import org.zxy.repository.back.service.UserService;

/**
 * @author 98578
 * @create 2020-07-05 15:39
 */
@RequestMapping("/user")
@RestController
public class UserController {

  @Autowired private UserService userService;

  /** 获取用户信息 - 根据token信息 */
  @RequestMapping("/info")
  public AjaxResponse info() {
    return userService.selectOneByUsername();
  }

  /** 获取用户信息 - 根据传入id */
  @RequestMapping("/info/{id}")
  public AjaxResponse infoById(@PathVariable("id") Integer id) {
    return userService.selectByPrimaryKey(id);
  }

  /** 用户注册 */
  @RequestMapping("/save")
  public AjaxResponse save(@RequestBody User user) {
    return userService.insertSelective(user);
  }

  /** 更新用户信息 */
  @RequestMapping("/update")
  public AjaxResponse update(@RequestBody User user) {
    return userService.updateByPrimaryKeySelective(user);
  }

  @RequestMapping("/list")
  public AjaxResponse selectAllByUsernameOrderByCreateTime(
      @RequestParam(value = "username", defaultValue = "", required = false) String username,
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    return userService.selectAllByUsernameOrderByCreateTime(username, pageNum, pageSize);
  }

  @RequestMapping("/delete/{userId}")
  public AjaxResponse deleteByUserId(@PathVariable Integer userId){
    return userService.deleteByUserId(userId);
  }
}
