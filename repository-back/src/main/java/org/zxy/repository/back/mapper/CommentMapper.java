package org.zxy.repository.back.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.zxy.repository.back.entity.Comment;

public interface CommentMapper {

    int deleteByPrimaryKey(Integer id);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    List<Comment> selectByItemIdOrderByPublishTimeDesc(@Param("itemId")Integer itemId);


}