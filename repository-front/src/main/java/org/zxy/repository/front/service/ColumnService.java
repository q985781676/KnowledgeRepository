package org.zxy.repository.front.service;

import org.zxy.repository.common.exception.AjaxResponse;

public interface ColumnService{

    AjaxResponse selectOrderByItemNumDesc();
    AjaxResponse selectOrderByCreateTimeDesc();

}
