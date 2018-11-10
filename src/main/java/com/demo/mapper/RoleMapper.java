package com.demo.mapper;

import com.demo.model.Role;
import com.demo.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhouyulong
 * @date 2018/10/31 上午10:53.
 */
@Mapper
public interface RoleMapper {

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
     * 新增角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 删除角色
     * @param id
     */
    void deleteRole(Integer id);

    /**
     * 增加角色权限关联
     * @param rolePermission
     */
    void addRolePermission(RolePermission rolePermission);

    /**
     * 更新角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 更新角色权限关联
     * @param rolePermission
     */
    void updateRolePermission(RolePermission rolePermission);


    void deleteRolePermission(Integer id);

}
