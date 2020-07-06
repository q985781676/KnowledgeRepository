package org.zxy.repository.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.front.entity.User;
import org.zxy.repository.front.service.UserService;

/**
 * @author 98578
 * @create 2020-07-05 15:39
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息 - 根据token信息
     */
    @PreAuthorize("hasAnyAuthority('base','p1')")
    @RequestMapping("/info")
    public AjaxResponse info(){
        return userService.selectOneByUsername();
    }

    /**
     * 获取用户信息 - 根据传入id
     */
    @PreAuthorize("hasAnyAuthority('base','p1')")
    @RequestMapping("/info/{id}")
    public AjaxResponse infoById(@PathVariable("id") Integer id){
        return userService.selectByPrimaryKey(id);
    }
    
    /**
     * 用户注册
     */
    @RequestMapping("/save")
    public AjaxResponse save(@RequestBody User user){
        return userService.insertSelective(user);
    }
    
    /**
     * 更新用户信息
     */
    @PreAuthorize("hasAnyAuthority('base','p1')")
    @RequestMapping("/update")
    public AjaxResponse update(@RequestBody User user){
        return userService.updateByPrimaryKeySelective(user);
    }

}
