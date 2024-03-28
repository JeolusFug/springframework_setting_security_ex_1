package com.board.member.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.board.member.controller"})
public class MvcConfig implements WebMvcConfigurer {
//    default servlet 핸들러를 설정
//    원래 서블릿은 / (모든요청) 을 처리하는 default servlet을 제공
//    스프링에서 설정한 path는 스프링이 처리하고, 스프링이 처리하지 못한 겨올에 대한 처리는 디폴트 서블리에게 전달하여 처리
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // Spring MVC에서 jsp view가 위치하는 경로를 설정
    // views = 기존 뷰 view = security 때 만든 뷰
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    // "/"로 요청이 오면 "/main"으로 리다이렉트 하도록
    // 나중에 수정할지 고민해보기
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/main");
    }

    // /resources 경로에 있는 자료들을 /resources/**로 접근하게 함
    // 고민해보기
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
