package org.zxy.repository.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.front.entity.Comment;
import org.zxy.repository.front.service.CommentService;

/**
 * @author 98578
 * @create 2020-07-05 16:05
 */
@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 发布评论
     */
    @PreAuthorize("hasAnyAuthority('base','p1')")
    @RequestMapping("/save")
    public AjaxResponse save(@RequestBody Comment comment){
        return commentService.insertSelective(comment);
    }
}
