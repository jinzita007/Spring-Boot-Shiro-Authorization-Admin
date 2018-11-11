package com.demo.service;

import com.demo.model.User;
import com.demo.model.UserRole;

import java.util.List;
import java.util.Set;

/**
 * @author zhouyulong
 * @date 2018/10/28 下午4:17.
 */
public interface UserService {

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 根据用户查询用户名
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 新增用户
     * @param user
     * @return
     */
    public int save(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    public int update(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    public void delete(Integer id);

    /**
     * 新增用户角色关联
     * @param userRole
     * @return
     */
    public int addUserRole(UserRole userRole);

    /**
     * 根据用户角色查找用户ID
     * @param userId
     * @return
     */
    public UserRole findUserRoleByUserId(Integer userId);

    /**
     * 删除用户角色关联
     * @param userId
     * @return
     */
    public int deleteUserRole(Integer userId);


}
