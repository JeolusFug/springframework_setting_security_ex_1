package com.board.member.service;

import com.board.member.config.ApplicationConfig;
import com.board.member.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLOutput;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class PasswordEncoderTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void passwordEncode() throws Exception {
        System.out.println(passwordEncoder.encode("1234"));
    }

    @Test
    public void passwordTest() throws Exception {
        // passwordEncoder의 encode() 메소드를 이용해서 인코딩한 값, 인코딩 할 때 마다 값이 바뀜
        String encodePassword = "$2a$10$USbExG2YOZJqu5rR9eWAqO3NqwjS6c8uI0c695cnURA2gxqRnx41O";
        String password = "1234";
        // 인코딩한 값과 원래의 값이 같은지를 검사
        // 인코딩할 때 마다 값이 바뀌어도 결국 1234를 표한한 값이기 때문에 true가 나옴
        boolean test = passwordEncoder.matches(password, encodePassword);
        System.out.println(test);
    }
}
