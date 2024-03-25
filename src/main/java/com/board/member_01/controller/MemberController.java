// 회원가입용입니다. かいいんとうろくようです
package com.board.member_01.controller;

import com.board.member_01.dto.MemberDTO;
import com.board.member_01.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.PanelUI;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/member")
// 공통 주소를 설정하기 위함
// きょうつうアドレスをせっていするため
@RequiredArgsConstructor
// 의존성을 주입받기 위함
// いぞんせいのちゅうにゅうをうけるための
public class MemberController {
    private final MemberService memberService;
    // @GetMapping("/member/save")
    @GetMapping("/saveForMember")
    public String saveFormForMember() {
        return "saveForMember";
    }

    @PostMapping("/saveForMember")
    public String savetomember(@ModelAttribute MemberDTO memberDTO) {
        int saveResultToMember = memberService.savetomember(memberDTO);
        if (saveResultToMember > 0) {
            return "loginForMember";
        } else {
            return "saveForMember";
        }
    }

    @GetMapping("/loginForMember")
    public String loginForm() {
        return "loginForMember";
    }
    // 로그인 페이지를 띄우는 메서드
    // ログインページをひょうじするメソッド

    @PostMapping("/loginForMember")
    public String loginForMember(@ModelAttribute MemberDTO memberDTO,
                                 HttpSession session) {
        // 로그인 처리를 하는 메서드
        // ログインしょりをおこなうメソッド
        // 세션을 사용하여 로그인시 정보가 따라다닐 수 있도록
        // セッションをしようしてログインじにじょうほうがついていけるように
        boolean loginResultForMember = memberService.loginForMember(memberDTO);
        if (loginResultForMember) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "mainForMember";
        }else {
            return "loginForMember";
        }
    }

    @GetMapping("/")
    public String findAllForMember(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAllForMember();
        model.addAttribute("memberList", memberDTOList);
        return "listForMember";
    }

    // member?id=1과 같은 방식으로 옵니다.
    // member?id=1みたいなほうほうできます
    @GetMapping
    public String findByIdForMember(@RequestParam("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findByIdForMember(id);
        model.addAttribute("member", memberDTO);
        return "detailForMember";
    }

    @GetMapping("/deleteForMember")
    public String deleteForMember(@RequestParam("id") Long id) {
        memberService.deleteForMember(id);
        return "redirect:/member/";
    }

    // 수정화면 요청
    // しゅうせいがめんリクエスト
    @GetMapping("/updateForMember")
    public String updateForm(HttpSession session, Model model) {
        // 세션에 저장된 나의 이메일 가져오기
        // セッションにほぞんされているじぶんのIDをしゅとくする
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmailForMember(loginEmail);
        model.addAttribute("member", memberDTO);
        return "updateForMember";
    }

    // 수정 처리
    // しゅうせいしょり
    @PostMapping("/updateForMember")
    public String updateForMember(@ModelAttribute MemberDTO memberDTO) {
        boolean resultForMember = memberService.updateForMember(memberDTO);
        if (resultForMember) {
            return "redirect:/member?id=" + memberDTO.getId();
        } else {
            return "indexForMember";
        }
    }

    @PostMapping("/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        // @ResponseBody가 있어야만 ajax에 응답이 나갈 수 있음
        // @Response Bodyがいないとajaxにおうとうができない
        // System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheckForMember(memberEmail);
        return checkResult;
    }
}
