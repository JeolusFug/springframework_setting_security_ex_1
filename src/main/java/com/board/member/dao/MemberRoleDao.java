// SimpleJdbcInsert로 간단하게 insert를 할 수 있고, INSERT가 성공하면 key(id)를 반환
package com.board.member.dao;

import com.board.member.dto.MemberRole;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRoleDao {
    private SimpleJdbcInsert insertAction;
    private NamedParameterJdbcTemplate jdbc;
    // BeanPropertyRowMapper는 Role 클래스의 프로퍼티를 보고 자동으로 컬럼과 매핑해주는 RowMapper 객체를 생성
    // roleId 프로퍼티는 role_id 컬럼과 매핑
    private RowMapper<MemberRole> rowMapper = BeanPropertyRowMapper.newInstance(MemberRole.class);

    public MemberRoleDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                            .withTableName("member_role")
                            .usingGeneratedKeyColumns("id");
    }

    public List<MemberRole> getRolesByEmail(String email) {
        Map<String , Object> map = new HashMap<>();
        map.put("email", email);

        return jdbc.query(MemberRoleDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
    }

    public Long insert(MemberRole memberRole) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(memberRole);

        return insertAction.executeAndReturnKey(params).longValue();
    }
}
