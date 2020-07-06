package org.zxy.repository.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.front.entity.Item;
import org.zxy.repository.front.service.ItemService;

/**
 * @author 98578
 * @create 2020-07-05 19:14
 */
@RequestMapping("/item")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;


    /**
     * 按时间排序查询
     * @param columnId 栏目id传入不为空返回当前栏目的排序结果
     */
    @RequestMapping("/list/time")
    public AjaxResponse selectOrderByPublishTime(@RequestParam(name = "columnId",required = false) Integer columnId){
        return itemService.selectOrderByPublishTime(columnId);
    }

     /**
     * 按评论热度排序查询
     * @param columnId 栏目id传入不为空返回当前栏目的排序结果
     */
    @RequestMapping("/list/comment_num")
    public AjaxResponse selectOrderByCommentNum(@RequestParam(name = "columnId",required = false) Integer columnId){
        return itemService.selectOrderByCommentNum(columnId);
    }



    /**
     * 发布文章
     */
    @PreAuthorize("hasAnyAuthority('base','p1')")
    @RequestMapping("/save")
    public AjaxResponse save(@RequestBody Item item){
        return itemService.insertSelective(item);
    }

    @RequestMapping("/info/{columnId}")
    public AjaxResponse info(@PathVariable Integer columnId){
        return AjaxResponse.success(itemService.selectItemDetailByItemId(columnId));
    }

}
