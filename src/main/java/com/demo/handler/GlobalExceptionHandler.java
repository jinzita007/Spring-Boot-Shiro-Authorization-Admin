package com.demo.handler;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhouyulong
 * @date 2018/10/31 下午3:16.
 * 定义全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(value = UnauthorizedException.class)//处理访问方法时权限不足问题
        public String defaultErrorHandler(HttpServletRequest req, Exception e) {
            return "403";
        }
}
