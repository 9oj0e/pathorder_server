package shop.project.pathorderserver._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.project.pathorderserver._core.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //.addPathPatterns("/api/**") // TODO: 로그인 및 JWT 검증시, 주석 제거
                .excludePathPatterns("/**");
    }
}