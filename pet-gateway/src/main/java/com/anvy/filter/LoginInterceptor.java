package com.anvy.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
* @Description: session 拦截。权限控制。
* @author Anvy Lao
* @date 2020/5/6 10:41
*/
public class LoginInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
    * @Description: 执行前拦截。
    * @author Anvy Lao
    * @date 2020/5/6 10:41
    */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.debug(requestURI);
        return true;

    }

}