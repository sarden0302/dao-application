package edu.kh.com.daoapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 웹 관련 설정 Springboot 프로젝트가 컴퓨터에 직접적으로 접근 할 수 있도록 설정
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload-img}")
    private String uploadImg;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // resources
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + uploadImg + "/");
    }
}
