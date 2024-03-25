// 게시판용입니다. けいじばんようです
package com.board.board_01.controller;

import com.board.board_01.dto.BoardDTO;
import com.board.board_01.dto.CommentDTO;
import com.board.board_01.dto.PageDTO;
import com.board.board_01.service.BoardService;
import com.board.board_01.service.CommentService;
import com.board.member_01.dto.MemberDTO;
import com.board.member_01.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    // 글 작성기능, 작성 후 페이징 기능이 있는 목록으로 넘어감
    // もじのさくせいきのう、さくせいごページングきのうがあるもくろくにうつる
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    // 글 작성기능, 작성 후 페이징 기능이 있는 목록으로 넘어감
    // もじのさくせいきのう、さくせいごページングきのうがあるもくろくにうつる
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        int saveResult = boardService.save(boardDTO);
        if (saveResult > 0) {
            return "redirect:/board/paging";
        } else {
            return "save";
        }
    }

    // 글 작성기능, 작성 후 페이징 기능이 없는 목록으로 넘어감
    // もじのさくせいきのう、さくせいごページングきのうがないもくろくにうつる
    @GetMapping("/save1")
    public String save1Form() {
        return "save1";
    }

    // 글 작성기능, 작성 후 페이징 기능이 없는 목록으로 넘어감
    // もじのさくせいきのう、さくせいごページングきのうがないもくろくにうつる
    @PostMapping("/save1")
    public String save1(@ModelAttribute BoardDTO boardDTO) {
        int saveResult1 = boardService.save1(boardDTO);
        if (saveResult1 > 0) {
            return "redirect:/board/";
        } else {
            return "save1";
        }
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping
    public String findById(@RequestParam("id") Long id,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", page);
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);
        return "detail";
    }
    // 글 작성자와 현재 로그인한 아이디가 같을 시 글 삭제 가능
    // ぶんのさくせいしゃとげんざいログインしたIDがおなじばあい、ぶんをさくじょかのう
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id,
                         HttpSession session,
                         HttpServletRequest request) {
        BoardDTO boardDTO = boardService.checkWriter(id);
        String Writer = boardDTO.getBoardWriter();
        String loginId = (String) session.getAttribute("loginEmail");
        // System.out.println("id = " + id + ", Writer = " + Writer + ", loginId = " + loginId);
        if (Writer.equals(loginId)) {
            boardService.delete(id);
            request.setAttribute("msg", "삭제가 완료되었습니다.");
            request.setAttribute("url", "/board/PagingAfterSearch");
            return "alert";
        } else {
            request.setAttribute("msg", "작성자가 아닙니다.");
            request.setAttribute("url", "/board?id=" + id);
            return "alert";
        }
    }
    // 삭제 기능과 동일하게 수정
    // さくじょきのうとどうようにしゅうせい
    @GetMapping("/update")
    public String updateForm(@RequestParam("id") Long id, Model model,
                             HttpSession session,
                             HttpServletRequest request) {
        BoardDTO boardDTO = boardService.findById(id);
        String Writer = boardDTO.getBoardWriter();
        String loginId = (String) session.getAttribute("loginEmail");
        if (Writer.equals(loginId)) {
            model.addAttribute("board", boardDTO);
            return "update";
        } else {
            request.setAttribute("msg", "작성자가 아닙니다.");
            request.setAttribute("url", "/board?id=" + id);
            return "alert";
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO);
       BoardDTO dto = boardService.findById(boardDTO.getId());
       model.addAttribute("board", dto);
       return "detail";
 //      return "redirect:/board?id="+boardDTO.getId();
    }
    
    // 처음 페이지 요청은 1페이지를 보여줌
    // required = false 는 필수가 아니라는 뜻
    // /board/paging?page=2 라고오면 page 변수에는 2가 들어가고
    // /board/paging?page 가 오면 page 변수에는 defaultValue = "1" 에 설정한 것 처럼 1이 들어옴
    // さいしょのページのリクエストは1ページをひょうじ
    // required = falseはひっすではないといういみ
    // /board/paging?page=2がくるとpageへんすうには２がはいって
    // /board/paging?pageがくろとpageへんすうひはdefaultValue = "1"にせっていしたように１がはいる

    @GetMapping("/paging")
    public String paging(Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
//        System.out.println("page = " + page);
        // 해당 페이지에서 보여줄 글 목록
        //　がいとうページでひょうじするぶんのリスト、もくろく
        List<BoardDTO> pagingList = boardService.pagingList(page);
//        System.out.println("pagingList = " + pagingList);
        PageDTO pageDTO = boardService.pagingParam(page);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        return "paging";
    }
    
    // 페이징이 없는 게시판의 검색기능을 위함
    // ページングきのうがないけいじばんのけんさくきのうのため
    @GetMapping("/listSearch")
    public String findAll2(Model model,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "searchType", required = false) String searchType) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        // jsp에서 keyword를 받아와 controller에서 사용
        // jspでkeywordをうけとり、controllerでしよう
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", searchType);
        
        // 검색어인 keyword가 null이 아닐시 keyword를 포함한 list를 화면에 보여줌
        // けんさくごであるkeywordがnullでないばあい、keywordをふくむlistをがめんによううじする
        if (keyword != null) {
            List<BoardDTO> boardDTOList1 = boardService.findAllSearch(keyword, searchType);
            model.addAttribute("boardList", boardDTOList1);
        }

        return "listSearch";
    }

    // 검색 기능 작성 후 페이징 기능 작성
    // けんさくきのうをさくせいしたあと、ページングきのうをさくせい
    @GetMapping("/PagingAfterSearch")
    public String PagingAfterSearch(Model model,
                                    @RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "searchType", required = false) String searchType,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", searchType);
        if (keyword == null) {
            List<BoardDTO> PagingAfterSearchDTO1 = boardService.Search1(page);
            PageDTO pagingsearchDTO = boardService.PageSearchCount1(page);
            model.addAttribute("pagingSearch", pagingsearchDTO);
            model.addAttribute("boardList", PagingAfterSearchDTO1);
        }
        else {
            List<BoardDTO> PagingAfterSearchDTO2 = boardService.Search2(keyword, searchType, page);
            PageDTO pagingsearchDTO2 = boardService.PageSearchCount2(page, keyword, searchType);
            model.addAttribute("pagingSearch", pagingsearchDTO2);
            model.addAttribute("boardList", PagingAfterSearchDTO2);
        }

        return "PagingAfterSearch";
    }
}
