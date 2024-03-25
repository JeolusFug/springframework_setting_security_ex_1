// 게시판용입니다. けいじばんようです
package com.board.board_01.service;

import com.board.board_01.dto.BoardDTO;
import com.board.board_01.dto.PageDTO;
import com.board.board_01.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    // 페이징 기능이 있는 작성
    // ページングきのうがあるさくせい
    public int save(BoardDTO boardDTO) {
        return boardRepository.save(boardDTO);
    }

    // 페이징 기능이 없는 작성
    // ページングきのうがないさくせい
    public int save1(BoardDTO boardDTO) {
        return boardRepository.save1(boardDTO);
    }
    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    int pageLimit = 10;  // 한 페이지당 보여줄 글 갯수
                        //　１ペイジにみせるぶんのかず
    int blockLimit = 5; // 하단에 보여줄 페이지 번호 갯수
                        //　したにみせるペイジばんごのかず
    public List<BoardDTO> pagingList(int page) {
        /*
            한페이지당 보여지는 글 갯수가 3일때
            1페이지를 요청하면 0, 2페이지를 요청하면 3, 3페이지를 요청하면 6 이 필요함
            ひとつのペイジにみせるぶんのかずが３のとじ、
            １ペイジをようせいすれば０，２ペイジをようせいすれば３，３ペイジをようせいすれば６がひつよう
         */
        int pageStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pageStart);
        pagingParams.put("limit", pageLimit);
        List<BoardDTO> pagingList = boardRepository.pagingList(pagingParams);

        return pagingList;
    }

    public PageDTO pagingParam(int page) {
        // 현재 글 갯수 조회
        //　げんざいぶんすうのしょうかい
        int boardCount = boardRepository.boardCount();
        // 전체 페이지 갯수 계산
        // Math.ceil = 올림처리 3.3333 -> 4
        //　ぜんページすうのけいさん
        //　Math.ceil = アップリプロセス
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산
        //　はじまるページをけいさん
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산
        //　さいごのページをけいさん
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }

    // 페이징 기능이 없는 검색기능, keyword를 함께 보냄
    // ページングきのうがないけんさくきのう、keywordをいっしょにおくる
    public List<BoardDTO> findAllSearch(String keyword, String searchType) {
        Map<String, String> findtool = new HashMap<>();
        findtool.put("keyword", keyword);
        findtool.put("searchType", searchType);
        return boardRepository.findAllSearch(findtool);
    }

    // 검색 기능 작성 후 페이징 기능 작성
    // けんさくきのうをさくせいしたあと、ページングきのうをさくせい
    public List<BoardDTO> Search1(int page) {

        int pageStart = (page - 1) * pageLimit;
        Map<String, Integer> SearchPaging1 = new HashMap<>();
        SearchPaging1.put("start", pageStart);
        SearchPaging1.put("limit", pageLimit);

        return boardRepository.Search1(SearchPaging1);
    }

    public List<BoardDTO> Search2(String keyword, String searchType, int page) {
        int pageStart = (page - 1) * pageLimit;
        Map<String, Object> SearchTool2 = new HashMap<>();
        SearchTool2.put("keyword", keyword);
        SearchTool2.put("searchType", searchType);
        SearchTool2.put("start", pageStart);
        SearchTool2.put("limit", pageLimit);

        return boardRepository.Search2(SearchTool2);
    }

    public PageDTO PageSearchCount1(int page) {

        int PageSearchCount1 = boardRepository.PageSearchCount1();
        int maxPage = (int) (Math.ceil((double) PageSearchCount1 / pageLimit));
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = startPage + blockLimit - 1;

        if (endPage > maxPage) {
            endPage = maxPage;
        }

        PageDTO pageDTO1 = new PageDTO();
        pageDTO1.setPage(page);
        pageDTO1.setMaxPage(maxPage);
        pageDTO1.setStartPage(startPage);
        pageDTO1.setEndPage(endPage);

        return pageDTO1;
    }

    public PageDTO PageSearchCount2(int page, String keyword, String searchType) {
        Map<String, String> SearchPagingTool = new HashMap<>();
        SearchPagingTool.put("keyword", keyword);
        SearchPagingTool.put("searchType", searchType);

        int PageSearchCount2 = boardRepository.PageSearchCount2(SearchPagingTool);

        int maxPage = (int) (Math.ceil((double) PageSearchCount2 / pageLimit));
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        int endPage = startPage + blockLimit - 1;

        if (endPage > maxPage) {
            endPage = maxPage;
        }

        PageDTO pageDTO2 = new PageDTO();
        pageDTO2.setPage(page);
        pageDTO2.setMaxPage(maxPage);
        pageDTO2.setStartPage(startPage);
        pageDTO2.setEndPage(endPage);

        return pageDTO2;
    }
    // 글 작성자와 현재 로그인한 ID가 동일한지 확인하기 위함
    // ぶんのさくせいしゃとげんざいログインしたIDがどういつかかくにんするため
    public BoardDTO checkWriter(Long id) {
        return boardRepository.checkWriter(id);
    }
}