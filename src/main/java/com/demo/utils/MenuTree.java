package com.demo.utils;

import com.demo.model.Permission;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouyulong
 * @date 2018/11/8 下午4:11.
 * 递归构造树型结构
 */
public class MenuTree {
    public static Map<String,Object> mapArray = new LinkedHashMap<String, Object>();
    public List<Permission> permissionList;
    public List<Object> list = new ArrayList<Object>();

    public List<Object> menuList(List<Permission> menu) {

        Map<String,Object> mapArr = new LinkedHashMap<String, Object>();
        for (Permission menus : menu) {
            if (menus.getPid().equals(0)) {
                mapArr.put("id", menus.getId());
                mapArr.put("pid", menus.getPid());
                mapArr.put("name", menus.getName());
                mapArr.put("ismenu", menus.getIsmenu());
                mapArr.put("children",menuChild(menus.getId()));
                list.add(mapArr);
            }

        }
        return list;
    }

    public List<?> menuChild(Integer id){
        List<Object> lists = new ArrayList<Object>();
        for(Permission menu:permissionList){
            Map<String,Object> childArray = new LinkedHashMap<String, Object>();
            if(menu.getPid().equals(id)){
                childArray.put("id", menu.getId());
                childArray.put("pid", menu.getPid());
                childArray.put("name", menu.getName());
                childArray.put("ismenu", menu.getIsmenu());
                childArray.put("children",menuChild(menu.getId()));
                lists.add(childArray);
            }
        }
        return lists;
    }

}
