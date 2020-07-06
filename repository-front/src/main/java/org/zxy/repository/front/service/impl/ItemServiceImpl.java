package org.zxy.repository.front.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.common.exception.CustomException;
import org.zxy.repository.common.exception.CustomExceptionType;
import org.zxy.repository.front.mapper.ItemMapper;
import org.zxy.repository.front.entity.Item;
import org.zxy.repository.front.service.ItemService;

import java.util.Date;

/**
 * @author 98578
 */
@Service
public class ItemServiceImpl implements ItemService {

  @Resource private ItemMapper itemMapper;

  @Override
  public AjaxResponse insertSelective(Item record) {
    record.setPublishTime(new Date());
    if (itemMapper.insertSelective(record) > 0) {
      return AjaxResponse.success();
    } else {
      return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"输入有误"));
    }
  }

  @Override
  public AjaxResponse selectOrderByPublishTime(Integer columnId) {
    return AjaxResponse.success(itemMapper.selectOrderByPublishTime(columnId));
  }

  @Override
  public AjaxResponse selectOrderByCommentNum(Integer columnId) {
    return AjaxResponse.success(itemMapper.selectOrderByCommentNum(columnId));
  }

  @Override
  public AjaxResponse selectItemDetailByItemId(Integer columnId) {

    return AjaxResponse.success(itemMapper.selectItemDetailByItemId(columnId));
  }
}
