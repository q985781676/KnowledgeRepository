package org.zxy.repository.back.service;

import org.zxy.repository.back.entity.Menu;
import org.zxy.repository.common.exception.AjaxResponse;import java.util.List;

public interface MenuService {

    AjaxResponse selectByPrimaryKey(Integer menuId);

    /**
     * 分页查询列表
     */
    AjaxResponse selectAll(Integer pageNum,Integer pageSize);

}

