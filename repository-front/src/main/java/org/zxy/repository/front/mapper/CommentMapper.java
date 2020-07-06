package org.zxy.repository.front.mapper;

import org.apache.ibatis.annotations.Param;
import org.zxy.repository.front.entity.Comment;

import java.util.List;

public interface CommentMapper {
    int insertSelective(Comment record);
    List<Comment> selectByItemIdOrderByPublishTimeDesc(@Param("itemId") Integer itemId);
}