package com.demo.mapper;

import com.demo.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhouyulong
 * @date 2018/10/31 下午12:48.
 */
@Mapper
public interface PermissionMapper {

    /**
     * 查询所有权限信息
     * @return
     */
    List<Permission> findAll();

    List<Permission> parentMenu();

    List<Permission> childMenu(Integer pid);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    List<Permission> findUserPermissions(String username);

    /**
     * 新增资源
     * @param permission
     */
    void addPermission(Permission permission);

    /**
     * 切换菜单
     * @param permission
     */
    void updateIsmenu(Permission permission);

    /**
     * 删除资源
     * @param id
     */
    void delete(Integer id);

}
