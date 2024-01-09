package com.chm.config;

import com.chm.filter.LoginCheckFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {



    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
        log.info("开始进行静态资源映射");
    }
    @Bean
    public FilterRegistrationBean myFilter(){
        LoginCheckFilter loginCheckFilter = new LoginCheckFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(loginCheckFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}
