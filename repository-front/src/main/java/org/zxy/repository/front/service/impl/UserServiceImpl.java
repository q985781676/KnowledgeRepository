package org.zxy.repository.front.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.common.exception.CustomException;
import org.zxy.repository.common.exception.CustomExceptionType;
import org.zxy.repository.front.mapper.UserMapper;
import org.zxy.repository.front.entity.User;
import org.zxy.repository.front.service.UserService;

import java.util.Date;

/** @author 98578 */
@Service
public class UserServiceImpl implements UserService {

  @Resource private UserMapper userMapper;

  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Override
  public AjaxResponse selectOneByUsername() {
    String username =
        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return AjaxResponse.success(userMapper.selectOneByUsername(username));
  }

  /**
   * 事务管理 注册一名用户，分配一次基础权限
   *
   * @param record 用户
   */
  @Transactional
  @Override
  public AjaxResponse insertSelective(User record) {

    // 进行加密
    record.setPassword(encoder.encode(record.getPassword()));
    record.setCreateTime(new Date());
    record.setEnabled(1);

    int userId = userMapper.insertSelective(record);
    int authId = userMapper.insertUserRole(record.getUserId());
    if (userId > 0 && authId > 0) {
      return AjaxResponse.success();
    } else {
      return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR, "输入有误"));
    }
  }

  @Override
  public AjaxResponse selectByPrimaryKey(Integer userId) {
    User user = userMapper.selectByPrimaryKey(userId);
    user.setPassword(null);
    if (user.getEnabled() == 1) {
      return AjaxResponse.success(user);
    } else {
      return AjaxResponse.error(
          new CustomException(CustomExceptionType.FORBIDDEN_ERROR, "该账户权限已被冻结"));
    }
  }

  @Override
  public AjaxResponse updateByPrimaryKeySelective(User record) {
    // 数据保证 - 不能修改用户名和日期
    record.setUsername(null);
    record.setCreateTime(null);
    // 密码不为空，对修改后的密码进行加密操作
    String password = record.getPassword();
    if (password != null) {
      record.setPassword(encoder.encode(password));
    }
    if (userMapper.updateByPrimaryKeySelective(record) > 0) {
      return AjaxResponse.success();
    } else {
      return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR, "输入有误"));
    }
  }
}
