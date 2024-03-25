// 게시판용입니다. けいじばんようです
package com.board.board_01.controller;

import com.board.board_01.dto.CommentDTO;
import com.board.board_01.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    public @ResponseBody List<CommentDTO> save(HttpSession session, @ModelAttribute CommentDTO commentDTO) {
        // System.out.println("commentDTO = " + commentDTO);
        // 위에 주석처리는 댓글을 작성했을때의 테스트용입니다.
        //　うえのコメントしょりはコメントをさくせいしたときのテストようです。
        commentService.save(commentDTO);
        // 해당 게시글에 작성된 댓글 리스트를 가져옴
        // 게시글 번호가 필요 boardId 값으로 가지고 있음
        //　がいとうぶんにさくせいされたコメントリストをもってくる
        //　えきじばんばんごうがひつよう、boardIdがもっています
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        return commentDTOList;
    }
    /* @PostMapping("/delete")
    public String commentdelete(@RequestParam("id") Long id) {
        commentService.commentdelete(id);
        return "redirect:/board?id=${board.id}";
    } */

    @PostMapping("/DeleteCheck")
    public @ResponseBody String DeleteCheck(HttpSession session, @ModelAttribute CommentDTO commentDTO,
                                             @RequestParam("commentId") Long id) {
        String Writer = commentService.findWriter(id);
        String loginId = (String) session.getAttribute("loginEmail");
        //System.out.println("comment id = " + id + ", commentWriter = " + Writer + ", loginId = " + loginId);
        //System.out.println("id = " + id );@RequestParam("commentId") Long id
        if (loginId.equals(Writer)) {
            return "Matched";
        } else {
            return "NotMatched";
        }
    }

    @PostMapping("/Delete")
    public @ResponseBody List<CommentDTO> Delete(@ModelAttribute CommentDTO commentDTO,
                                                  @RequestParam(value = "commentId") Long id) {
        commentService.commentdelete(id);
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        //System.out.println("commentDTOList = " + commentDTOList + ",boardId " + commentDTO.getBoardId());
        return commentDTOList;
    }

    // 수정 버튼을 눌렀을 시 작성자와 로그인 아이디를 확인하기 위함
    @PostMapping("/UpdateCheck")
    public @ResponseBody String updateCheck (HttpSession session, @RequestParam("commentId") Long id) {
        String Writer = commentService.findWriter(id);
        String loginId = (String) session.getAttribute("loginEmail");
        //System.out.println("Writer = " + Writer + ", loginId = " + loginId);
        if (loginId.equals(Writer)) {
            return "Same";
        } else {
            return "NOPE";
        }
    }

    // 수정 기능
    // しゅうせいきのう
    @PostMapping("/update")
    public @ResponseBody List<CommentDTO> update(@ModelAttribute CommentDTO commentDTO,
                                                 @RequestParam("commentId") Long id,
                                                 @RequestParam("commentContents") String contents) {
        Map<String, Object> updateTool = new HashMap<>();
        updateTool.put("id", id);
        updateTool.put("contents", contents);
        // System.out.println("id = " + id + ", contents = " + contents);
        // 수정 후 전체 댓글 목록을 다시 불러오기
        // しゅうせいご、ぜんたいのコメントをさいよみこむ
        commentService.commentupdate(updateTool);

        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        System.out.println("commentDTOList2 = " + commentDTOList);
        return commentDTOList;
    }
}
