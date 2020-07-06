package org.zxy.repository.back.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.zxy.repository.back.entity.Item;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer itemId);

    Item selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(Item record);

    List<Item> selectByTitleOrderByPublishTime(@Param("title")String title);

}