package org.zxy.repository.back.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.zxy.repository.back.mapper.ColumnMapper;
import org.zxy.repository.back.entity.Column;
import org.zxy.repository.back.service.ColumnService;
import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.common.exception.CustomException;
import org.zxy.repository.common.exception.CustomExceptionType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ColumnServiceImpl implements ColumnService{

    @Resource
    private ColumnMapper columnMapper;

    @Override
    public AjaxResponse deleteByPrimaryKey(Integer columnId) {
        if (columnMapper.deleteByPrimaryKey(columnId)>0){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"所删除编号不存在"));
        }

    }

    @Override
    public AjaxResponse insertSelective(Column record) {
        if (columnMapper.insertSelective(record)>0) {
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"添加失败"));
        }

    }

    @Override
    public AjaxResponse selectByPrimaryKey(Integer columnId) {
        return AjaxResponse.success(columnMapper.selectByPrimaryKey(columnId));
    }

    @Override
    public AjaxResponse updateByPrimaryKeySelective(Column record) {
        if (columnMapper.updateByPrimaryKeySelective(record)>0){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"更新失败"));
        }
    }

    @Override
    public AjaxResponse selectAllByColumnName(String columnName,Integer pageNum,Integer pageSize) {
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<Column> list = columnMapper.selectAllByColumnName(columnName);

        Map<String, Object> map = new HashMap<>();
        map.put("pages",page.getPages());
        map.put("list",list);
        return AjaxResponse.success(map);
    }

}
