package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.Permission;
import com.demo.model.User;
import com.demo.service.PermissionService;
import com.demo.utils.MenuTree;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouyulong
 * @date 2018/10/30 上午9:19.
 */
@Controller
public class IndexController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/dashboard";
    }

    /**
     * 获取二级树形菜单
     * @param model
     * @return
     */
    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String index(Model model) {
        //登录成后，即可通过Subject获取登录的用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
//        List<Permission> menuList = permissionService.menuList();
        model.addAttribute("user", user);
//        model.addAttribute("menuList", menuList);
        return "index";
    }

    @RequestMapping("/menu")
    @ResponseBody
    public Map<String, Object> indexPage() {

        Map<String, Object> map = new HashMap<>();
        try {

            List<Permission> menuList = permissionService.menuList();
            map.put("menuList", menuList);

        } catch (Exception e) {
            map.put("msg", "error");
        }

        return map;
    }


}
