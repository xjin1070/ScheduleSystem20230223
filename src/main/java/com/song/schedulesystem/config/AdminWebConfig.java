package com.song.schedulesystem.config;

import com.song.schedulesystem.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器的使用
 * 1.编写一个拦截器实现HandlerInterception接口
 * 2.拦截器注册到容器中（实现WebMvcConfigurer接口的 addInterceptor方法）
 * 3.指定拦截规则（如果拦截所有，静态资源也会被拦截98
 */
//@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    //添加拦截器，添加路径，放行的路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")//所有请求都被拦截，包括静态资源
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**","/city");//放心静态新资源
    }
}
