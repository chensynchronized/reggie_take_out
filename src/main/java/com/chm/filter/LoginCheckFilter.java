package com.chm.filter;



import com.alibaba.fastjson.JSON;
import com.chm.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    //路径匹配
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1获取本次请求的url
        String requestURI = request.getRequestURI();
        //2.判断本次请求是否需要处理，检查登录状态
        String[] urls=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        boolean check = check(urls, requestURI);
        //3.如果不需要处理则直接放行
        if(check){
            filterChain.doFilter(request,response);
            return;

        }
        //4.判断登录状态，如果已登录则直接放行
        if(request.getSession().getAttribute("employee")!=null){
            filterChain.doFilter(request,response);
            return;
        }
        //5.如果未登录则返回未登录结果
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));



    }

    @Override
    public void destroy() {
        Filter.super.destroy();

    }
    public boolean check(String[] urls,String requestURI){
        for (int i = 0; i < urls.length; i++) {
            if(PATH_MATCHER.match(urls[i],requestURI)){
                return true;
            }

        }
        return false;
    }
}
