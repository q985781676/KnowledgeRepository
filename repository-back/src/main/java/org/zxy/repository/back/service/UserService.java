package org.zxy.repository.back.service;

import org.zxy.repository.back.entity.User;
import org.zxy.repository.common.exception.AjaxResponse;


public interface UserService {

    /**
     * 根据token中的用户名返回用户信息
     */
    AjaxResponse selectOneByUsername();

    AjaxResponse insertSelective(User record);

    AjaxResponse selectByPrimaryKey(Integer userId);

    AjaxResponse updateByPrimaryKeySelective(User record);

    AjaxResponse selectAllByUsernameOrderByCreateTime(String username,Integer pageNum,Integer pageSize);

    AjaxResponse deleteByUserId(Integer userId);
}
