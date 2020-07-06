package org.zxy.repository.back.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zxy.repository.back.entity.User;


public interface UserMapper {

	User selectOneByUsername(@Param("username")String username);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int insertUserRole(Integer userId);

    List<User> selectAllByUsernameOrderByCreateTime(@Param("username")String username);

    int deleteByUserId(@Param("userId")Integer userId);



}