package com.board.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    
    // @ResponseBody 어노테이션이 붙어있을 경우엔 리턴하는 문자열을 화면에 직접 출력
    // 만약 @ResponseBody 어노테이션이 없을 때는 뷰(view)이름을 리턴
    @RequestMapping("/main")
    @ResponseBody
    public String main() {
        return "main page";
    }

    @RequestMapping("/securepage")
    @ResponseBody
    public String securitypage() {
        return "secure page";
    }
}
