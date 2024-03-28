// MemberServiceImpl 클래스는 MemberService 인터페이스를 구현
// MemberService 인터페이스를 구현한다는 것은 UserDbService 역시 구현해야 한다는 의미
// member_role 테이블은 member 테이블의 id를 외래키로 가지기 때문에 memberDao 에서 insert 후 반환된 id 값을 MemberRole 을 만들 때 이용
// Member의 id는 member 테이블에 생성될 때 자동으로 만들어지기 때문에 INSERT 후 따로 설정해주어야 함
// INSERT 이기 때문에 readOnly = false로 설정
package com.board.member.service;

import com.board.member.dao.MemberDao;
import com.board.member.dao.MemberRoleDao;
import com.board.member.dto.Member;
import com.board.member.dto.MemberRole;
import com.board.member.service.security.UserEntity;
import com.board.member.service.security.UserRoleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    // 생성자에 의해 주입되는 객체이고, 해당 객체를 초기화할 필요가 이후에 없기 때문에 final로 선언
    // final로 선언하고 초기화를 안한 필드는 생성자에서 초기화를 함
    private final MemberDao memberDao;
    private final MemberRoleDao memberRoleDao;

    // @Service가 붙은 객체는 스프링이 자동으로 Bean으로 생성하는데
    // 기본생성자가 없고 아래와 같이 인자를 받는 생성자만 있을 경우 자동으로 관련된 타입이 Bean으로 있을 경우 주입해서 사용하게 됨
    public MemberServiceImpl(MemberDao memberDao, MemberRoleDao memberRoleDao) {
        this.memberDao = memberDao;
        this.memberRoleDao = memberRoleDao;
    }
    @Override
    @Transactional
    public UserEntity getUser(String loginUserId) {
        Member member = memberDao.getMemberByEmail(loginUserId);

        return new UserEntity(member.getEmail(), member.getPassword());
    }

    @Override
    @Transactional
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
        List<MemberRole> memberRoles = memberRoleDao.getRolesByEmail(loginUserId);
        List<UserRoleEntity> list = new ArrayList<>();

        for (MemberRole memberRole : memberRoles) {
            list.add(new UserRoleEntity(loginUserId, memberRole.getRoleName()));
        }
        return list;
    }

    @Override
    @Transactional(readOnly = false)
    public Member addMember(Member member) {
        Long memberId = memberDao.insert(member);
        member.setId(memberId);

        MemberRole memberRole = new MemberRole();
        memberRole.setMemberId(memberId);
        memberRole.setRoleName("ROLE_USER");
        memberRoleDao.insert(memberRole);

        return member;
    }

    // DB로부터 아이디(Email)을 이용해 사용자 정보를 가져오는 메서드
    @Override
    public Member getMemberByEmail(String email) {
        return memberDao.getMemberByEmail(email);
    }
}
