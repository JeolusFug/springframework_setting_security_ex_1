// DB의 정보를 읽어 들이는 레이어에 속하기 때문에 @Repository가 사용됨
// SimpleJdbcInsert로 간단하게 insert를 할 수 있고, INSERT가 성공하면 key(id)를 반환

package com.board.member.dao;

import com.board.member.dto.Member;
import com.sun.source.tree.WhileLoopTree;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDao {
    private SimpleJdbcInsert insertAction;
    private NamedParameterJdbcTemplate jdbc;
    // BeanPropertyRowMapper 는 Role 클래스의 프로퍼티를 보고 자동으로 칼럼과 매핑해주는 RowMapper 객체를 생성
    // roleId 프로퍼티는 role_id 칼럼과 매핑
    private RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);

    public MemberDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                            .withTableName("member")
                            .usingGeneratedKeyColumns("id");
    }

    public Member getMemberByEmail(String email) {
        Map<String , Object> map = new HashMap<>();
        map.put("email", email);

        return jdbc.queryForObject(MemberDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
    }

    public Long insert(Member member) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);

        return insertAction.executeAndReturnKey(params).longValue();
    }
}
