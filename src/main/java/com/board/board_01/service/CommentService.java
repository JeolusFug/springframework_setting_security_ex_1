// 게시판용입니다. けいじばんようです
package com.board.board_01.service;

import com.board.board_01.dto.CommentDTO;
import com.board.board_01.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;



    public void save(CommentDTO commentDTO) {
        commentRepository.save(commentDTO);
    }

    public List<CommentDTO> findAll(Long boardId) {
        return commentRepository.findAll(boardId);
    }
    /* public void commentdelete(Long id) {
        commentRepository.commentdelete(id);
    } */

    public void commentdelete(Long id) {
        // System.out.println("comment-delete-btn = " + id);
        commentRepository.commentdelete(id);
    }

    public String findWriter(Long id) {
        return commentRepository.findWriter(id);
    }

    // 댓글 수정
    // コメントしゅうせい
    public void commentupdate(Map<String, Object> updateTool) {
        commentRepository.commentupdate(updateTool);
    }

}
