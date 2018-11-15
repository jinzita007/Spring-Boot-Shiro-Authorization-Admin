package com.demo.service;

import com.demo.model.Permission;

import java.util.List;

/**
 * @author zhouyulong
 * @date 2018/10/31 下午12:46.
 */
public interface PermissionService {

    public List<Permission> findAll();

    public List<Permission> menuList();
    public List<Permission> menuChild(Integer id);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public List<Permission> findUserPermissions(String username);

    /**
     * 新增资源
     * @param permission
     */
    public void addPermission(Permission permission);

    /**
     * 切换菜单
     * @param permission
     */
    public void updateIsmenu(Permission permission);

    /**
     * 删除资源
     * @param id
     */
    public void delete(Integer id);

}
