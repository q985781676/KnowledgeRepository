package org.zxy.repository.back.service;

import org.zxy.repository.back.entity.Column;
import org.zxy.repository.common.exception.AjaxResponse;

public interface ColumnService{

    AjaxResponse deleteByPrimaryKey(Integer columnId);

    AjaxResponse insertSelective(Column record);

    AjaxResponse selectByPrimaryKey(Integer columnId);

    AjaxResponse updateByPrimaryKeySelective(Column record);

    /**
     * 搜索
     * @param columnName 可选变量
     */
    AjaxResponse selectAllByColumnName(String columnName,Integer pageNum,Integer pageSize);

}
