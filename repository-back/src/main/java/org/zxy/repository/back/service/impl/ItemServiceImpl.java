package org.zxy.repository.back.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.zxy.repository.back.mapper.ItemMapper;
import org.zxy.repository.back.entity.Item;
import org.zxy.repository.back.service.ItemService;
import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.common.exception.CustomException;
import org.zxy.repository.common.exception.CustomExceptionType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{

    @Resource
    private ItemMapper itemMapper;

    @Override
    public AjaxResponse deleteByPrimaryKey(Integer itemId) {
        if (itemMapper.deleteByPrimaryKey(itemId)>0){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"所删除编号不存在"));
        }
    }

    @Override
    public AjaxResponse selectByPrimaryKey(Integer itemId) {
        return AjaxResponse.success(itemMapper.selectByPrimaryKey(itemId));
    }

    @Override
    public AjaxResponse updateByPrimaryKeySelective(Item record) {
        if (itemMapper.updateByPrimaryKeySelective(record)>0){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"更新失败"));
        }
    }

    @Override
    public AjaxResponse selectByTitleOrderByPublishTime(String title, Integer pageNum, Integer pageSize) {
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<Item> list = itemMapper.selectByTitleOrderByPublishTime(title);

        Map<String, Object> map = new HashMap<>();
        map.put("pages",page.getPages());
        map.put("list",list);
        return AjaxResponse.success(map);
    }

}
