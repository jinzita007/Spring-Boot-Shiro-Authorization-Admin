package com.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhouyulong
 * @date 2018/10/30 下午3:33.
 */
@Controller
public class LoginController {

    private final static Logger logger= (Logger) LoggerFactory.getLogger(LoginController.class);

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/login")
    public String index() {
        return "login";
    }

    /**
     * 用户登录
     * @param request
     * @param username
     * @param password
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, String username, String password, Model model) throws Exception{
        logger.info("UserController.login()");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        String msg = "";

            try {
                //登录操作
                subject.login(token);
                return "index";
            } catch (UnknownAccountException e) {
                logger.info("用户名或密码错误");
                msg = "用户名或密码错误";
            } catch (IncorrectCredentialsException e) {
                logger.info("用户名或密码错误");
                msg = "用户名或密码错误";
            } catch (LockedAccountException e) {
                logger.info("账号已被锁定，请联系管理员！");
                msg = "账号已被锁定，请联系管理员！";
            }
        model.addAttribute("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    /**
     * 退出接口
     * @return
     */
    @RequestMapping(value="logout",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> logout(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return map;
    }

    @GetMapping("/403")
    public String forbid() {
        return "403";
    }

}
