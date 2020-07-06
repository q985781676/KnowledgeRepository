package org.zxy.repository.back.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zxy.repository.back.entity.Menu;
import org.zxy.repository.back.entity.Role;

public interface RoleMapper {

    int deleteByPrimaryKey(Integer roleId);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    Role selectRoleWithMenus(Integer roleId);

    List<Role> selectAll();

    int batchInsertMenu(@Param("list") List<Menu> list,@Param("roleId") Integer roleId);
}