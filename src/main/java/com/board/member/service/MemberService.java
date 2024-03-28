// UserDbService 인터페이스를 상속받는 MemberService 인터페이스를 작성, UserDbService는 스프링 시큐리티에서 필요한 정보를 가지고 오는 메소드를 가지고 있음
// MemberService는 회원과 관련된 모든 정보를 처리하는 서비스
// 예를 들어 회원 등록과 관련된 메소드는 MemberService에 추가되게 됨
package com.board.member.service;

import com.board.member.dto.Member;
import com.board.member.service.security.UserDbService;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService extends UserDbService {
    @Transactional(readOnly = false)
    Member addMember(Member member);

    Member getMemberByEmail(String email);
}
