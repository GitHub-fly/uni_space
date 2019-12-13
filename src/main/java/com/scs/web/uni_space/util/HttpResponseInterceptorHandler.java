package com.scs.web.uni_space.util;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName HttpResponseInterceptorHandler
 * @Description 设置请求和响应头信息
 * @Author xiaobinggan
 * @Date 2019/12/12 8:28 上午
 * @Version 1.0
 **/
public class HttpResponseInterceptorHandler extends HandlerInterceptorAdapter {
    // 实现HandlerInterceptor  或者 继承HandlerInterceptorAdapter都可以,如果想看着简洁就使用后者
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("content-test", "123");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
