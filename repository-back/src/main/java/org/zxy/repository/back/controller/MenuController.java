package org.zxy.repository.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zxy.repository.back.service.MenuService;
import org.zxy.repository.common.exception.AjaxResponse;

/**
 * @author 98578
 * @create 2020-07-06 17:04
 */
@RequestMapping("/menu")
@RestController
public class MenuController {

  @Autowired private MenuService menuService;

  @RequestMapping("/info/{menuId}")
  public AjaxResponse info(@PathVariable("menuId") Integer menuId) {
    return menuService.selectByPrimaryKey(menuId);
  }


  @RequestMapping("/list")
  public AjaxResponse list(
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    return menuService.selectAll(pageNum, pageSize);
  }
}
