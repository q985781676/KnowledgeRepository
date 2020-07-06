package org.zxy.repository.back.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.zxy.repository.back.mapper.MenuMapper;
import org.zxy.repository.back.entity.Menu;
import org.zxy.repository.back.service.MenuService;
import org.zxy.repository.common.exception.AjaxResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public AjaxResponse selectByPrimaryKey(Integer menuId) {
        return AjaxResponse.success(menuMapper.selectByPrimaryKey(menuId));
    }

    @Override
    public AjaxResponse selectAll(Integer pageNum,Integer pageSize) {
        Page<Menu> page = PageHelper.startPage(pageNum, pageSize);
        List<Menu> list = menuMapper.seletAll();

        Map<String, Object> map = new HashMap<>();
        map.put("pages",page.getPages());
        map.put("list",list);
        return AjaxResponse.success(map);
    }

}

