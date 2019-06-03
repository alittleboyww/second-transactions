package com.second.hand.transactions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/4/26 0026
 * Time:15:03
 * Describe:定制错误页面
 */

/**
 * webMvc配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源映射
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}

