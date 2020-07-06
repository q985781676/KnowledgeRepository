package org.zxy.repository.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.front.service.ColumnService;

/**
 * @author 98578
 * @create 2020-07-05 17:10
 */
@RequestMapping("/column")
@RestController
public class ColumnController {

  @Autowired private ColumnService columnService;

  /** 按热度排序查询栏目 */
  @RequestMapping("/list/item_num")
  public AjaxResponse selectOrderByItemNumDesc() {
    return columnService.selectOrderByItemNumDesc();
  }

  /** 按创建时间排序 */
  @RequestMapping("/list/time")
  public AjaxResponse selectOrderByCreateTimeDesc() {
    return columnService.selectOrderByCreateTimeDesc();
  }
}
