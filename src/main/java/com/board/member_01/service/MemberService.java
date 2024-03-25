// 회원가입용입니다. かいいんとうろくようです
package com.board.member_01.service;

import com.board.member_01.dto.MemberDTO;
import com.board.member_01.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public int savetomember(MemberDTO memberDTO) {
        return memberRepository.savetomember(memberDTO);
    }

    public boolean loginForMember(MemberDTO memberDTO) {
        MemberDTO loginMember = memberRepository.loginForMember(memberDTO);
        if (loginMember != null) {
            // null이 아닐 시 로그인을 성공시킴
            // nullでないばあい、ログインをせいこうさせる
            return true;
        } else {
            return false;
        }
    }

    public List<MemberDTO> findAllForMember() {
        return memberRepository.findAllForMember();
    }

    public MemberDTO findByIdForMember(Long id) {
        return memberRepository.findByIdForMember(id);
    }

    public void deleteForMember(Long id) {
        memberRepository.deleteForMember(id);
    }

    public MemberDTO findByMemberEmailForMember(String loginEmail) {
        return memberRepository.findByMemberEmailForMember(loginEmail);
    }

    public boolean updateForMember(MemberDTO memberDTO) {
        int resultForMember = memberRepository.updateForMember(memberDTO);
        if (resultForMember > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String emailCheckForMember(String memberEmail) {
        MemberDTO memberDTO = memberRepository.findByMemberEmailForMember(memberEmail);
        if (memberDTO == null) {
            // email을 사용하는 사람이 없다면 "ok" 있으면 "no"
            // "ok"와 "no"는 임의의 값, 변경 가능
            // IDをつかうひとがいなければ"ok"あれば"no"
            // "ok"と"no"はにんいのち、へんこうかのう
            return "nooneuse";
        } else {
            return "someoneuse";
        }
    }
}
