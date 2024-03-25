// 게시판용입니다. けいじばんようです
package com.board.board_01.repository;

import com.board.board_01.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;
    // 페이징 기능이 있는 작성
    // ページングきのうがあるさくせい
    public int save(BoardDTO boardDTO) {
        return sql.insert("Board.save", boardDTO);
    }

    // 페이징 기능이 없는 작성
    // ページングきのうがないさくせい
    public int save1(BoardDTO boardDTO) {
        return sql.insert("Board.save1", boardDTO);
    }
    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }

    public void update(BoardDTO boardDTO) {
        sql.update("Board.update", boardDTO);
    }

    public List<BoardDTO> pagingList(Map<String, Integer> pagingParams) {
        return sql.selectList("Board.pagingList", pagingParams);
    }

    public int boardCount() {
        return sql.selectOne("Board.boardCount");
    }

    // 페이징 기능이 없는 검색기능, keyword를 함께 보냄
    // ページングきのうがないけんさくきのう、keywordをいっしょにおくる
    public List<BoardDTO> findAllSearch(Map<String, String> findtool) {
        return sql.selectList("Board.findAllSearch", findtool);
    }

    // 검색 기능 작성 후 페이징 기능 작성
    // けんさくきのうをさくせいしたあと、ページングきのうをさくせい
    public List<BoardDTO> Search1(Map<String, Integer> SearchPaging1) {
        return sql.selectList("Board.Search1", SearchPaging1);
    }
    public List<BoardDTO> Search2(Map<String, Object> SearchTool2) {
        return sql.selectList("Board.Search2", SearchTool2);
    }

    public int PageSearchCount1() {
        return sql.selectOne("Board.PageSearchCount1");
    }
    public int PageSearchCount2(Map<String, String> searchPagingTool) {
        return sql.selectOne("Board.PageSearchCount2", searchPagingTool);
    }

    // 글 작성자와 현재 로그인한 ID가 동일한지 확인하기 위함
    // ぶんのさくせいしゃとげんざいログインしたIDがどういつかかくにんするため
    public BoardDTO checkWriter(Long id) {
        return sql.selectOne("Board.checkForDelete", id);
    }
}