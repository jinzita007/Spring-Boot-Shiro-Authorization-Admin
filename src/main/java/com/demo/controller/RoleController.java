package com.demo.controller;

import com.demo.model.Permission;
import com.demo.model.Role;
import com.demo.model.RolePermission;
import com.demo.service.PermissionService;
import com.demo.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouyulong
 * @date 2018/10/31 下午2:56.
 * 角色管理控制层
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 跳转角色列表页
     * @return
     */
    @RequiresPermissions("role:list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("list", roleService.findAll());
        return "role/list";
    }

    /**
     * 所有角色列表接口
     * @return
     */
    @RequestMapping(value = "role_list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> RoleList() {
        Map<String,Object> map = new HashMap<>();
        List<Role> roleList = roleService.findAll();
        map.put("data", roleList);
        return map;
    }

    /**
     * 查询权限信息
     * @return
     */
    @RequestMapping(value = "perm_list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryByperm() {
        Map<String,Object> map = new HashMap<>();
        List<Permission> permissionList = permissionService.findAll();
        map.put("msg", "ok");
        map.put("data", permissionList);
        return map;
    }

    /**
     * 根据角色权限查询角色ID
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/roleperm/{roleId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findByRolePermId(@PathVariable("roleId") Integer roleId) {
        Map<String,Object> map = new HashMap<>();
        List<RolePermission> rolePermissionList = roleService.findByRolePermission(roleId);
        map.put("rolePermissionList", rolePermissionList);
        return map;

    }

    /**
     * 新增角色接口
     * @return
     */
    @RequiresPermissions("role:create")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addRole(Role role, Integer... permissionIds){
        Map<String,Object> map = new HashMap<>();
        roleService.addRole(role, permissionIds);
        map.put("msg", "OK!");
        map.put("name", role.getName());
        map.put("permissionIds", permissionIds);
        return map;
    }

    /**
     * 更新角色接口
     * @param role
     * @param permissionIds
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateRole(Role role, Integer... permissionIds) {
        Map<String,Object> map = new HashMap<>();
        roleService.updateRole(role, permissionIds);
        map.put("msg", "OK!");
        map.put("name", role.getName());
        map.put("permissionIds", permissionIds);
        return map;

    }

    /**
     * 删除角色
     * @param id
     */
    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRole(Integer id){
        try {
            roleService.deleteRole(id);
            return "success";
        } catch (Exception e) {
            return "error";
        }

    }

}
