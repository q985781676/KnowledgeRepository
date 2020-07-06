package org.zxy.repository.back.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.zxy.repository.back.entity.Role;
import org.zxy.repository.back.mapper.MenuMapper;
import org.zxy.repository.back.mapper.RoleMapper;
import org.zxy.repository.back.service.RoleService;
import org.zxy.repository.common.exception.AjaxResponse;
import org.zxy.repository.common.exception.CustomException;
import org.zxy.repository.common.exception.CustomExceptionType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public AjaxResponse deleteByPrimaryKey(Integer roleId) {
        if (roleMapper.deleteByPrimaryKey(roleId)>0){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"所删除编号不存在"));
        }
    }

    @Transactional
    @Override
    public AjaxResponse insertSelective(Role record) {
        //加入角色
        int countRole = roleMapper.insertSelective(record);
        //同时分配资源
        int countMenu = roleMapper.batchInsertMenu(record.getMenus(),record.getRoleId());

        if (countMenu>=0&&countRole>0){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"添加失败"));
        }
    }


    @Override
    public AjaxResponse updateByPrimaryKeySelective(Role record) {
        if (roleMapper.updateByPrimaryKeySelective(record)>0){
            return AjaxResponse.success();
        }else {
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"更新失败"));
        }
    }

    @Override
    public AjaxResponse selectRoleWithMenus(Integer roleId) {
        return AjaxResponse.success(roleMapper.selectRoleWithMenus(roleId));
    }

    @Override
    public AjaxResponse selectAll(Integer pageNum, Integer pageSize) {
        Page<Role> page = PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectAll();

        Map<String, Object> map = new HashMap<>();
        map.put("pages",page.getPages());
        map.put("list",list);
        return AjaxResponse.success(map);
    }
}
