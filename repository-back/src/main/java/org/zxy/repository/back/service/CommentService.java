package org.zxy.repository.back.service;

import org.zxy.repository.back.entity.Comment;
import org.zxy.repository.common.exception.AjaxResponse;

public interface CommentService{


    AjaxResponse deleteByPrimaryKey(Integer id);

    AjaxResponse selectByPrimaryKey(Integer id);

    AjaxResponse updateByPrimaryKeySelective(Comment record);

    AjaxResponse selectByItemIdOrderByPublishTimeDesc(Integer itemId);
}
