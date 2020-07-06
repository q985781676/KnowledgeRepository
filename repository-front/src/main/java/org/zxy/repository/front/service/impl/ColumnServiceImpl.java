package org.zxy.repository.front.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.front.mapper.ColumnMapper;
import org.zxy.repository.front.service.ColumnService;
@Service
public class ColumnServiceImpl implements ColumnService{

    @Resource
    private ColumnMapper columnMapper;

    @Override
    public AjaxResponse selectOrderByItemNumDesc() {
        return AjaxResponse.success(columnMapper.selectOrderByItemNumDesc());
    }

    @Override
    public AjaxResponse selectOrderByCreateTimeDesc() {
        return AjaxResponse.success(columnMapper.selectOrderByCreateTimeDesc());
    }
}
