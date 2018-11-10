package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhouyulong
 * @date 2018/11/2 上午11:14.
 */
@Controller
public class PageController {
    @RequestMapping("/include/{pageName}")
    public String include(@PathVariable("pageName") String pageName){
        System.out.println("/include/"+pageName);
        return "include/"+pageName;
    }
}
