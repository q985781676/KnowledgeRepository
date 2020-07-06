package org.zxy.repository.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxy.repository.back.entity.Comment;
import org.zxy.repository.back.service.CommentService;
import org.zxy.repository.common.exception.AjaxResponse;

/**
 * @author 98578
 * @create 2020-07-06 11:01
 */
@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/delete/{id}")
    public AjaxResponse delete(@PathVariable("id") Integer id){
        return commentService.deleteByPrimaryKey(id);
    }

    @RequestMapping("/info/{id}")
    public AjaxResponse info(@PathVariable("id") Integer id){
        return commentService.selectByPrimaryKey(id);
    }

    @RequestMapping("/update")
    public AjaxResponse update(@RequestBody Comment comment){
        return commentService.updateByPrimaryKeySelective(comment);
    }

    @RequestMapping("/list/{itemId}")
    public AjaxResponse listByItemId(@PathVariable("itemId") Integer itemId){
        return commentService.selectByItemIdOrderByPublishTimeDesc(itemId);
    }
}
