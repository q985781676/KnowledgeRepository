package org.zxy.repository.front.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.common.exception.CustomException;
import org.zxy.repository.common.exception.CustomExceptionType;
import org.zxy.repository.front.mapper.CommentMapper;
import org.zxy.repository.front.entity.Comment;
import org.zxy.repository.front.service.CommentService;

import java.util.Date;

/**
 * @author 98578
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    @Override
    public AjaxResponse insertSelective(Comment record) {
        record.setPublishTime(new Date());
        if (commentMapper.insertSelective(record)>0) {
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"输入有误"));
        }

    }

}
