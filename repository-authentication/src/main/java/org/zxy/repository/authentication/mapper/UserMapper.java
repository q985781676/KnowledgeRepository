package org.zxy.repository.authentication.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zxy.repository.authentication.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectOneByUsername(@Param("username")String username);

    List<String> selectAuthorityByUserId(Integer userId);

}