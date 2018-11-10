package com.demo.service;

import com.demo.model.User;

import java.util.List;
import java.util.Set;

/**
 * @author zhouyulong
 * @date 2018/10/28 下午4:17.
 */
public interface UserService {

    /**
     * 根据用户查询用户名
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

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
    public int delete(Integer id);


}