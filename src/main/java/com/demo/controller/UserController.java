package com.demo.controller;

import com.demo.model.User;
import com.demo.model.UserRole;
import com.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author zhouyulong
 * @date 2018/10/28 下午4:17.
 * 用户管理
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {

    private final static Logger logger= (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 跳转用户列表页
     * @return
     */
    @RequiresPermissions("user:list")
    @RequestMapping(value="list",method =RequestMethod.GET)
    public String index() {
        return "user/list";
    }

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value="user_list",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserList(@RequestParam(defaultValue= "1") Integer pageNumber, Integer pageSize, String q){
        //调用分页工具类，传递过来的当前页和每页条数
        PageHelper.startPage(pageNumber, pageSize, true);

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        if(q==null) {
            //将查询出来的集合放进list对象里面
            List<User> userList = userService.findAll();
            //将查询出来的数据集放进PageInfo
            PageInfo<User> info = new PageInfo<>(userList);
            map.put("total", info.getTotal());
            map.put("rows", info.getList());
        } else {
            System.out.println("进入模糊查询...");
            List<User> user = userService.selectByUsername(q);
            PageInfo<User> info2 = new PageInfo<>(user);
            map.put("total", info2.getTotal());
            map.put("rows", info2.getList());
        }

        return map;
    }

    /**
     * 根据用户查询用户名
     * @return
     */
    @RequestMapping(value="username",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findByUsername(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        String username = "admin";
        map.put("data", userService.findByUsername(username));
        return map;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping(value="add",method =RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> AddUser(User user) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        String algorithmName = "md5";
        String password = user.getPassword();
        String salt1 = user.getUsername();
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2; //加密的次数
        SimpleHash hash = new SimpleHash(algorithmName, password,
                salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        try {
            User user1 = userService.findByUsername(user.getUsername());
            if(user1!=null) {
                map.put("code", 409);
                map.put("msg", "用户已经存在");
                map.put("data", user);
            } else {
                user.setPassword(encodedPassword);
                user.setSalt(salt2);
                user.setReg_time(timestamp);
                userService.save(user);
                map.put("code", 201);
                map.put("msg", "新增用户成功");
                map.put("data", user);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @RequestMapping(value="update",method =RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(User user) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            userService.update(user);
            map.put("code", 202);
            map.put("msg", "更新成功");
            map.put("data", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateStatus(User user) {
        Map<String,Object> map = new HashMap<>();
        try {
            userService.updateStatus(user);
            logger.info("修改状态成功!");
            map.put("code", 204);
            map.put("msg", "修改状态成功");
            map.put("data", user);
        } catch (Exception e) {
            map.put("msg", "error");
        }

        return map;
    }

    /**
     * 新增用户角色关联
     * @param userRole
     * @return
     */
    @RequestMapping(value="saveUserRole",method =RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveUserRole(UserRole userRole) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            UserRole userRole1 = userService.findUserRoleByUserId(userRole.getUserId());

            if(userRole1!=null) {
                if(userRole1.getRoleId().equals(userRole.getRoleId())) {
                    map.put("code", 200);
                    map.put("msg", "用户角色关联已经存在");
                    map.put("data", userRole);
                } else {
                        if(userRole.getRoleId() == null) {
                           userService.deleteUserRole(userRole.getUserId());
                            map.put("code", 200);
                            map.put("msg", "解除用户角色关联成功");
                            map.put("data", userRole);
                        } else {
                            userService.deleteUserRole(userRole.getUserId());
                            userService.addUserRole(userRole);
                            map.put("code", 200);
                            map.put("msg", "新增用户角色关联成功");
                            map.put("data", userRole);

                        }
                }


            } else if(userRole1==null){
                if(userRole.getRoleId() == null) {
                    if(userRole1==null) {
                        map.put("code", 200);
                        map.put("msg", "不需新增用户角色关联");
                        map.put("data", userRole);
                    }
                } else {
                    userService.addUserRole(userRole);
                    map.put("code", 200);
                    map.put("msg", "新增用户角色关联成功");
                    map.put("data", userRole);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据用户角色查找用户ID
     * @param userId
     * @return
     */
    @RequestMapping(value="findUserRoleByUserId",method =RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findUserRoleByUserId(Integer userId) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            map.put("code", 200);
            map.put("data",userService.findUserRoleByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value="delete",method =RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(Integer userId) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            userService.delete(userId);
            map.put("code", 204);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
