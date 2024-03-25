// 게시판용입니다. けいじばんようです
// 회원가입용으로 임시 수정하였습니다.
// かいいんとうろくようにりんじしゅうせいしました。
package com.board.board_01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "indexForMember";
    }
}
