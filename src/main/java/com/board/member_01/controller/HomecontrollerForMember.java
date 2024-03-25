// 회원가입용입니다. かいいんとうろくようです
package com.board.member_01.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomecontrollerForMember {
    @GetMapping("/")
    public String indexForMember() {
        return "indexForMember";
    }
}
