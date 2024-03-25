// 게시판용입니다. けいじばんようです
package com.board.board_01.repository;

import com.board.board_01.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final SqlSessionTemplate sql;



    public void save(CommentDTO commentDTO) {
        sql.insert("Comment.save", commentDTO);
    }

    public List<CommentDTO> findAll(Long boardId) {
        return sql.selectList("Comment.findAll", boardId);
    }

    /* public void commentdelete(Long id) {
        sql.delete("Comment.delete", id);
    } */

    public void commentdelete(Long id) {
        sql.delete("Comment.commentdelete", id);
    }

    public String findWriter(Long id) {
        return sql.selectOne("Comment.findWriter", id);
    }

    // 수정만 하기 때문에 return이 없음
    // しゅうせいするだけなのでreturnがない
    public void commentupdate(Map<String, Object> updateTool) {
        sql.update("Comment.commentUpdate", updateTool);
    }
}
