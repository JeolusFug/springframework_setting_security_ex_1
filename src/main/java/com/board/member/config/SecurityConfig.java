// Spring security를 이용해 로그인, 로그아웃, 인증, 인가 등을 처리하기 위한 설정파일
// @EnableWebSecurity가 붙어있을 경우 Spring security를 구성하는 기본적인 Bean들을 자동으로 구성해줌
// WebSecurityConfigurerAdapter를 상속받으면 특정 메소드를 오버라이딩 함으로써 좀 더 손쉽게 설정할 수 있음

package com.board.member.config;

import com.board.member.service.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    // /webjars/** 경로에 대한 요청은 인증/인가 처리하지 않도록 무시
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/webjars/**"
        );
    }

    // AuthenticationFilter는 아이디, 비밀번호를 입력해서 로그인 할 때 처리해주는 필터
    // 아이디에 해당하는 정보를 DB에서 읽어 들일 때 UserDetailsService를 구현하고 있는 객체를 이용
    // customUserDetailsService 는 userDetailsService를 구현하고 있는 객체
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }
    
// 아래의 메소드를 오버라이딩 한다는 것은 인증, 인가에 대한 설정을 한다는 의미, 가장 중요한 메소드
// http.csrf().disable()은 csrf()라는 기능을 끔
// csrf를 보안 설정 중 post 방식으로 값을 전송할 때 token을 사용해야 하는 보안 설정
// csrf를 사용하면 보안성은 높아지지만 개발초기에는 불편함이 있다는 단점이 있어 끔.
// disable() 메소드는 http(여기에선 HttpSecurty)를 리턴    
    // /, /main에 대한 요청은 누구든 가능
    // 그 외 요청은 모두 인증 후 접근 가능
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/main", "/members/loginerror", "/members/joinform", "/members/join", "/members/welcome").permitAll()
                .antMatchers("/securepage", "/members/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                    // 로그인 폼에 대한 설정
                    .formLogin()
                    // 로그인 폼은 입력된 페이지를 사용 jsp와 컨드롤러가 작성 되어 있어야 함
                    .loginPage("/members/loginform")
                    // 로그인 폼에서 input type의 이름을 넣어줌. 실제 jsp 파일의 input type 이름과 일치해야 함
                    .usernameParameter("userId")
                    .passwordParameter("password")
                    // 폼에서 값을 입력하고 확인을 누르면 해당 경로에서 로그인 처리를 함
                    // 로그인을 처리하는 Security Filter가 해당 경로를 검사하다가 아이디와 비밀번호가 전달되면 로그인 과정을 처리
                    // 직접 구현하는 것은 아니지만 jsp에서
                    // <form method="post" action="/member/authenticate">처럼 경로를 입력해 주어야함
                    .loginProcessingUrl("/authenticate")
                    // 로그인 처리가 실패하면 "/loginerror?login_error=1"로 포워딩 됨
                    // 컨트롤러와 jsp가 작성되어 있어야 함
                    .failureForwardUrl("/members/loginerror?login_error=1")
                    // 로그인을 성공하면 해당 경로("/")로 리다이렉트 하게 됨
                    .defaultSuccessUrl("/", true)
                    //로그인 폼은 누구나 접든할 수 있게 함
                    .permitAll()
                .and()
                    // 로그아웃 요청이 오면 세션에서 로그인 정보를 삭제한 후 "/"로 리다이렉트
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
    }

    // 패스워드 인코더를 빈으로 등록
    // 비밀번호를 인코딩하거나, 인코딩된 비밀번호와 사용자가 입력한 비밀번호가 같은지 확인할 때 사용
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
