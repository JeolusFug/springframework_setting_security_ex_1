package com.board.member.dao;

import com.board.member.config.ApplicationConfig;
import com.board.member.dto.Member;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class MemberDaoTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    MemberDao memberDao;

    @Autowired
    MemberRoleDao memberRoleDao;

    @Test
    public void configTest() throws Exception{
        // 아무 작업도 하지 않음. 실행이 잘된다는 것은 Spring 설정이 잘 되어 있다는 것을 의미
    }

    @Test
    public void connectionTest() throws Exception {
        Connection connection = dataSource.getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void getUser() throws Exception {
        Member member = memberDao.getMemberByEmail("hong@naver.com");
        Assert.assertNotNull(member);
        Assert.assertEquals("홍길동", member.getName());
    }
}
