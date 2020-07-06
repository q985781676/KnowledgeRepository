package org.zxy.repository.front.mapper;
import org.apache.ibatis.annotations.Param;

import org.zxy.repository.front.entity.User;

import java.util.List;

public interface UserMapper {

	User selectOneByUsername(@Param("username")String username);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int insertUserRole(Integer userId);
}