package org.zxy.repository.front.mapper;

import org.apache.ibatis.annotations.Param;
import org.zxy.repository.front.entity.Item;

import java.util.List;

public interface ItemMapper {
    int insertSelective(Item record);
    List<Item> selectOrderByPublishTime(@Param("columnId") Integer columnId);
    List<Item> selectOrderByCommentNum(@Param("columnId") Integer columnId);
    Item selectItemDetailByItemId(@Param("columnId") Integer columnId);

}