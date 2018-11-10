package com.demo.service.Impl;

import com.demo.mapper.PermissionMapper;
import com.demo.model.Permission;
import com.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhouyulong
 * @date 2018/10/31 下午12:47.
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public List<Permission> menuList() {
        List<Permission> list = new ArrayList<>();
        List<Permission> permissionList = permissionMapper.parentMenu();
        for (Permission menus : permissionList) {
            Integer pid = menus.getPid();
            if (pid == 0) {
                menus.setChildren(menuChild(menus.getId()));
                list.add(menus);
            }
        }
            return list;
        }

    @Override
    public List<Permission> menuChild(Integer id){
        List<Permission> list = new ArrayList<>();
        List<Permission> permissionList = permissionMapper.parentMenu();
        for(Permission menu:permissionList){
            if(menu.getPid().equals(id)){
                menu.setChildren(menuChild(menu.getId()));
                list.add(menu);
            }
        }

        //递归退出条件
        if(list.size()==0){
            return null;
        }

        return list;
    }

    @Override
    public List<Permission> findUserPermissions(String username) {
        return permissionMapper.findUserPermissions(username);
    }

    @Override
    public void addPermission(Permission permission) {
        permissionMapper.addPermission(permission);
    }

    @Override
    public void updateIsmenu(Permission permission) {
        permissionMapper.updateIsmenu(permission);
    }
}
