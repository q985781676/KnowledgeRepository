package org.zxy.repository.front.service;

import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.front.entity.Comment;
public interface CommentService{


    AjaxResponse insertSelective(Comment record);

}
