package com.demo.service.Impl;

import com.demo.mapper.RoleMapper;
import com.demo.model.Role;
import com.demo.model.RolePermission;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouyulong
 * @date 2018/10/31 下午12:44.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public List<Role> findUserRole(String username){
        return roleMapper.findUserRole(username);
    }

    @Override
    public List<RolePermission> findByRolePermission(Integer roleId) {
        return roleMapper.findByRolePermission(roleId);
    }

    @Override
    public void addRole(Role role, Integer... permissionIds) {
        roleMapper.addRole(role);
        if(permissionIds != null && permissionIds.length>0 ) {
            for (Integer permissionId : permissionIds) {
                System.out.println("--------角色id: "+role.getId()+"-----------角色权限ID: "+permissionId);
                roleMapper.addRolePermission(new RolePermission(role.getId(),permissionId));
            }
        }
    }

    @Override
    public void updateRole(Role role, Integer... permissionIds) {
        roleMapper.updateRole(role);
        roleMapper.deleteRolePermission(role.getId());
        if(permissionIds != null && permissionIds.length>0 ) {
            for (Integer permissionId : permissionIds) {
                System.out.println("--------角色id: "+role.getId()+"-----------角色权限ID: "+permissionId);
                roleMapper.addRolePermission(new RolePermission(role.getId(), permissionId));
            }
        }

    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteRole(id);
        roleMapper.deleteRolePermission(id);
    }

}
