package com.kdn.honbob.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "DELETE")  // DELETE 메서드 허용 추가
                .allowedHeaders("*");  // 모든 헤더 허용 (실제 사용에 맞게 세밀하게 설정 가능)
    }
}
