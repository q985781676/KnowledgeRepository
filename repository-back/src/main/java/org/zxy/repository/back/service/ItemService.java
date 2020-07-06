package org.zxy.repository.back.service;

import org.zxy.repository.back.entity.Item;
import org.zxy.repository.common.exception.AjaxResponse;

public interface ItemService{


    AjaxResponse deleteByPrimaryKey(Integer itemId);

    AjaxResponse selectByPrimaryKey(Integer itemId);

    AjaxResponse updateByPrimaryKeySelective(Item record);

    AjaxResponse selectByTitleOrderByPublishTime(String title,Integer pageNum,Integer pageSize);

}
