package org.zxy.repository.front.service;

import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.front.entity.Item;
public interface ItemService{

    AjaxResponse insertSelective(Item record);
    AjaxResponse selectOrderByPublishTime(Integer columnId);
    AjaxResponse selectOrderByCommentNum(Integer columnId);
    AjaxResponse selectItemDetailByItemId(Integer columnId);
}
