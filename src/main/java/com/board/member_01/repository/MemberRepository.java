// 회원가입용입니다. かいいんとうろくようです
package com.board.member_01.repository;

import com.board.member_01.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final SqlSessionTemplate sql;


    public int savetomember(MemberDTO memberDTO) {
        // System.out.println("memberDTO = " + memberDTO);
        // 테스트용 テストよう
        return sql.insert("Member.saveForMember", memberDTO);
        // Member.saveForMember에서 Member는 memberMapper.xml의 제일 위에 있는 namespace와 맞춰주면 됨
        // .saveForMember는 memberMapper.xml에 id 값을 사용하면됨
        // Member.saveForMemberのMemberはmemberMapper.xmlのいちばんうえいあるnamespaceとあわせてばいい
        // .saveForMemberはmemberMapper.xmlのidをつかえばいい


    }

    public MemberDTO loginForMember(MemberDTO memberDTO) {
        return sql.selectOne("Member.loginForMember", memberDTO);
        // DB의 member_table에 회원가입 아이디인 MemberEmail을 Unique로 설정해놨기 때문에 selectOne을 사용
        // 선택하려는게 중복이 가능한 항목이라면 selectList를 사용
        // DBのmember_tableにかいいんとうろくIDであるMemberEmailをUniqueにせっていしてあるため、selectOneをしようする
        // せんたくしようとするのがちょうふくがかのうなこうもくならselectListをしよう

    }

    public List<MemberDTO> findAllForMember() {
        return sql.selectList("Member.findAllForMember");
    }

    public MemberDTO findByIdForMember(Long id) {
        return sql.selectOne("Member.findByIdForMember", id);
    }

    public void deleteForMember(Long id) {
        sql.delete("Member.deleteForMember", id);
    }

    public MemberDTO findByMemberEmailForMember(String loginEmail) {
        return sql.selectOne("Member.findByMemberEmailForMember", loginEmail);
    }

    public int updateForMember(MemberDTO memberDTO) {
        return sql.update("Member.updateForMember", memberDTO);
    }
}
