package com.board.member.controller;

import com.board.member.dto.Member;
import com.board.member.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(path = "/members")
public class MemberController {
    // Spring Container가 생성자를 통해 자동으로 주입합니다.
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    private MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/loginform")
    public String loginform() {
        return "members/loginform";
    }

    @RequestMapping("/loginerror")
    public String loginerror(@RequestParam("login_error") String loginError) {
        return "members/loginerror";
    }

    @GetMapping("/joinform")
    public String registerform() {
        return "members/joinform";
    }

    @PostMapping("/join")
    public String register(@ModelAttribute Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        Member insertMember = memberService.addMember(member);

        return "redirect:/loginform";
    }
    
    // Principal 객체의 getName() 메소드를 이용해 로그인 아이디를 구할 수 있음
    // 로그인 아이디를 구하고 해당 아이디를 이용해 DB로부터 회원 정보를 읽어옴
    @GetMapping("/memberinfo")
    public String memberInfo(Principal principal, ModelMap modelMap) {
        String email = principal.getName();
        Member member = memberService.getMemberByEmail(email);
        modelMap.addAttribute("member", member);

        return "/members/memberinfo";
    }
}
