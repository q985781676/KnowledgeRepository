package org.zxy.repository.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zxy.repository.back.entity.Column;
import org.zxy.repository.back.service.ColumnService;
import org.zxy.repository.common.exception.AjaxResponse;

/**
 * @author 98578
 * @create 2020-07-06 10:22
 */
@RequestMapping("/column")
@RestController
public class ColumnController {

  @Autowired private ColumnService columnService;

  @RequestMapping("/delete/{columnId}")
  public AjaxResponse delete(@PathVariable("columnId") Integer columnId) {
    return columnService.deleteByPrimaryKey(columnId);
  }

  @RequestMapping("/save")
  public AjaxResponse save(@RequestBody Column column) {
    return columnService.insertSelective(column);
  }

  @RequestMapping("/info/{columnId}")
  public AjaxResponse info(@PathVariable("columnId") Integer columnId) {
    return columnService.selectByPrimaryKey(columnId);
  }

  @RequestMapping("/update")
  public AjaxResponse update(@RequestBody Column column) {
    return columnService.updateByPrimaryKeySelective(column);
  }

  @RequestMapping("/list")
  public AjaxResponse list(
      @RequestParam(value = "columnName", defaultValue = "", required = false) String columnName,
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    return columnService.selectAllByColumnName(columnName, pageNum, pageSize);
  }
}
