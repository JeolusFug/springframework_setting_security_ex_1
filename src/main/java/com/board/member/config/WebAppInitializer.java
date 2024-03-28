package com.board.member.config;

import org.springframework.context.ApplicationContext;
import org.springframework.security.access.SecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // Spring Config 파일을 설정
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationConfig.class, SecurityConfig.class};
    }

    // spring WEB Config 파일을 설정 WebConfig는 Bean을 RootConfig에서 설정한 곳에서 부터 찾음
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MvcConfig.class};
    }

//    getServletMapping()은 DispatcherServlet이 매핑되기 위한 패스를 지정
//    위의 코드에서는 애플리케이션 기본 서블릿인 / 에만 매핑이 되어있음 그리고 이것은 애플리케이션으로 들어오는 모든 요청을 처리
//    원래 서블릿에서는 / 을 처리하는 DefaultServlet이 설정되어 있음
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");

        return new Filter[]{encodingFilter};
    }
}
