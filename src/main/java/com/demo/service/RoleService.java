package com.demo.service;

import com.demo.model.Role;
import com.demo.model.RolePermission;

import java.util.List;

/**
 * @author zhouyulong
 * @date 2018/10/31 下午12:43.
 */
public interface RoleService {

    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> findAll();

    /**
     * 根据用户名查找角色
     * @param username
     * @return
     */
    List<Role> findUserRole(String username);

    /**
     * 根据角色权限查询权限ID
     * @param roleId
     */
    List<RolePermission> findByRolePermission(Integer roleId);

    /**
     * 增加角色权限关联
     * @param role
     * @param permissionIds
     */
    void addRole(Role role, Integer... permissionIds);

    /**
     * 更新角色权限关联
     * @param role
     * @param permissionIds
     */
    void updateRole(Role role, Integer... permissionIds);

    /**
     * 删除角色和删除角色权限关联
     * @param id
     */
    void deleteRole(Integer id);

}
