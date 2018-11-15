package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.Permission;
import com.demo.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouyulong
 * @date 2018/11/1 上午9:53.
 */
@Controller
@RequestMapping("/sys/permission")
public class PermissionController {

    private final static Logger logger= (Logger) LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    /**
     * 跳转资源列表页
     * @return
     *
     */
    @RequiresPermissions("permission:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String index() {
        return "permission/list";
    }

    @RequiresPermissions("permission:list")
    @RequestMapping(value = "/permlist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPerm() {
        Map<String,Object> map = new HashMap<>();
        map.put("msg", "OK!");
        map.put("code", 200);
        map.put("data", permissionService.findAll());
        return map;
    }

    /**
     * 新增资源
     * @param permission
     * @return
     */
    @RequiresPermissions("permission:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addPerm(Permission permission) {
        Map<String,Object> map = new HashMap<>();
        try {
            permissionService.addPermission(permission);
            logger.info("新增成功!");
            map.put("code", 201);
            map.put("msg", "新增成功!");
            map.put("data", permission);
        } catch (Exception e) {
            map.put("msg", "error");
        }

        return map;
    }

    /**
     * 更改是否菜单
     * @param permission
     * @return
     */
    @RequestMapping(value = "/changeIsmenu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> changeIsmenu(Permission permission) {
        Map<String,Object> map = new HashMap<>();
        try {
            permissionService.updateIsmenu(permission);
            logger.info("修改是否菜单成功!");
            map.put("code", 200);
            map.put("data", permission);
        } catch (Exception e) {
            map.put("msg", "error");
        }

        return map;
    }

    /**
     * 删除资源
     * @param id
     * @return
     */
    @RequestMapping(value="delete",method =RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            permissionService.delete(id);
            logger.info("删除成功!");
            map.put("code", 204);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
