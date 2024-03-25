// 게시판용입니다. けいじばんようです
package com.board.board_01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
    private int page;   // 현재 페이지   //げんざいペイジ
    private int maxPage;    // 전체 필요한 페이지 갯수
                            // 　ぜんたいできにひつようなペイジのかず
    private int startPage;  // 현재 페이지 기준 시작 페이지 값
                            //　げんざいペイジをきじゅんのかいしペイジち
    private int endPage;    // 현재 페이지 기준 마지막 페이지 값
                            //　げんざいペイジきじゅんさいごのペイジち
}
