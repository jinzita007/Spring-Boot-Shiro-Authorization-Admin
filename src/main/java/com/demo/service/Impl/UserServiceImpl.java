package com.demo.service.Impl;

import com.demo.model.User;
import com.demo.mapper.UserMapper;
import com.demo.model.UserRole;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouyulong
 * @date 2018/10/28 下午4:18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<User> selectByUsername(String q) {
        return userMapper.selectByUsername(q);
    }

    @Override
    public int save(User user) {
        return userMapper.save(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public void updateStatus(User user) {
        userMapper.updateStatus(user);
    }

    @Override
    public void delete(Integer userId) {
        userMapper.delete(userId);
        userMapper.deleteUserRole(userId);
    }

    @Override
    public int addUserRole(UserRole userRole) {
        return userMapper.addUserRole(userRole);
    }
    @Override
    public UserRole findUserRoleByUserId(Integer userId){
        return userMapper.findUserRoleByUserId(userId);
    }

    @Override
    public int deleteUserRole(Integer userId) {
        return userMapper.deleteUserRole(userId);
    }

}
