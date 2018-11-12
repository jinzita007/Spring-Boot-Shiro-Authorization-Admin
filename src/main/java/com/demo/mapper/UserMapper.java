package com.demo.mapper;


import com.demo.model.User;
import com.demo.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author zhouyulong
 * @date 2018/10/28 下午4:17.
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户查询用户名
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 新增用户
     * @param user
     */
    int save(User user);

    /**
     * 更新用户
     * @param user
     */
    int update(User user);

    /**
     * 更改用户状态
     * @param user
     * @return
     */
    int updateStatus(User user);

    /**
     * 删除用户
     * @param id
     */
    int delete(Integer id);

    /**
     * 新增用户角色关联
     * @param userRole
     * @return
     */
    int addUserRole(UserRole userRole);

    /**
     * 根据用户角色查找用户ID
     * @param userId
     * @return
     */
    UserRole findUserRoleByUserId(Integer userId);

    /**
     * 删除用户角色关联
     * @param userId
     * @return
     */
    int deleteUserRole(Integer userId);

}
