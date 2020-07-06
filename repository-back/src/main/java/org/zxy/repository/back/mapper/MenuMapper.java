package org.zxy.repository.back.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.zxy.repository.back.entity.Menu;

public interface MenuMapper {
    Menu selectByPrimaryKey(Integer menuId);

    List<Menu> seletAll();

    /**
     * 按 menuid 区间寻找
     *
     * @param list 可为空，查找全部列表。不为空按list内容查找对应记录
     * @return 资源结果集
     */
    List<Menu> selectAllInMenuId(@Param("list") List<Menu> list);
}