package org.zxy.repository.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zxy.repository.back.entity.Item;
import org.zxy.repository.back.service.ItemService;
import org.zxy.repository.common.exception.AjaxResponse;

/**
 * @author 98578
 * @create 2020-07-06 11:20
 */
@RequestMapping("/item")
@RestController
public class ItemController {

  @Autowired private ItemService itemService;

  @RequestMapping("/delete/{itemId}")
  public AjaxResponse deleteByPrimaryKey(@PathVariable("itemId") Integer itemId) {
    return itemService.deleteByPrimaryKey(itemId);
  }

  @RequestMapping("/info/{itemId}")
  public AjaxResponse selectByPrimaryKey(@PathVariable("itemId") Integer itemId) {
    return itemService.selectByPrimaryKey(itemId);
  }

  @RequestMapping("/update")
  public AjaxResponse update(@RequestBody Item item) {
    return itemService.updateByPrimaryKeySelective(item);
  }

  @RequestMapping("/list")
  public AjaxResponse list(
      @RequestParam(value = "title", defaultValue = "", required = false) String title,
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    return itemService.selectByTitleOrderByPublishTime(title, pageNum, pageSize);
  }
}
