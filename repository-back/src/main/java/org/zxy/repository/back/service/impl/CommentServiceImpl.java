package org.zxy.repository.back.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.zxy.repository.back.entity.Comment;
import org.zxy.repository.back.mapper.CommentMapper;
import org.zxy.repository.back.service.CommentService;
import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.common.exception.CustomException;
import org.zxy.repository.common.exception.CustomExceptionType;

@Service
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    @Override
    public AjaxResponse deleteByPrimaryKey(Integer id) {
        if (commentMapper.deleteByPrimaryKey(id)>0){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"所删除编号不存在"));
        }
    }

    @Override
    public AjaxResponse selectByPrimaryKey(Integer id) {
        return AjaxResponse.success(commentMapper.selectByPrimaryKey(id));
    }

    @Override
    public AjaxResponse updateByPrimaryKeySelective(Comment record) {
        if (commentMapper.updateByPrimaryKeySelective(record)>0){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"更新失败"));
        }
    }

    @Override
    public AjaxResponse selectByItemIdOrderByPublishTimeDesc(Integer itemId) {
        return AjaxResponse.success(commentMapper.selectByItemIdOrderByPublishTimeDesc(itemId));
    }

}
