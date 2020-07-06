package org.zxy.repository.front.service;

import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.front.entity.User;
public interface UserService{

    /**
     * 根据token中的用户名返回用户信息
     */
    AjaxResponse selectOneByUsername();

    AjaxResponse insertSelective(User record);

    AjaxResponse selectByPrimaryKey(Integer userId);

    AjaxResponse updateByPrimaryKeySelective(User record);

}
