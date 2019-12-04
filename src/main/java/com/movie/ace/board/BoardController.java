package com.movie.ace.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.movie.ace.board.ReplyVO;

@Controller
public class BoardController {

	@Inject
	BoardMapper boardmapper;

	// 게시판 보기
	@RequestMapping("Movieboard")
	public ModelAndView list(@RequestParam(defaultValue = "all") String searchOption,
			@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "1") int curPage)
			throws Exception {

		System.out.println(keyword);
		int count = boardmapper.countArticle(searchOption, keyword);
		// 페이지 나누기
		BoardPager boardPager = new BoardPager(count, curPage);
		int start = boardPager.getPageBegin();
		int end = boardPager.getPageEnd();

		List<BoardVO> list = boardmapper.listAll(start, end, searchOption, keyword);
		System.out.println("count: " + count);
		System.out.println("start: " + start);
		System.out.println("end: " + end);
		// 데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("count", count); // 레코드 갯수
		map.put("searchOption", searchOption);// 검색옵션
		map.put("keyword", keyword); // 검색 키워드
		map.put("boardPager", boardPager); // 페이지 처리
		System.out.println(keyword);

		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map); // 맵에 저장된 데이터를 mav에 저장
		mav.setViewName("board/Movieboard");
		return mav;
	}

	// 관리자페이지
	@RequestMapping(value = "AdminPage", method = RequestMethod.GET)
	public String adminPage(HttpServletRequest req, HttpServletResponse res) {
		return "board/AdminPage";
	}

	// 자유게시판
	@RequestMapping(value = "Freeboard", method = RequestMethod.GET)
	public String Freboard(HttpServletRequest req, HttpServletResponse res) {
		return "board/Freeboard";
	}

	// 문의게시판
	@RequestMapping(value = "QnAboard", method = RequestMethod.GET)
	public String QnAboard() {
		return "board/QnAboard";
	}

	// 글쓰기 폼보기
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	// 게시글 작성처리

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo) throws Exception {
		boardmapper.create(vo);
		return "redirect:Movieboard";
	}

	// 게시글 상세내용 조회 조회수 증가처리
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int mboard_no, HttpSession session) throws Exception {
		boardmapper.increaseViewcnt(mboard_no, session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/view");
		mav.addObject("dto", boardmapper.read(mboard_no));
		return mav;
	}

	// 게시글 수정으로 가기
	@RequestMapping(value = "change", method = RequestMethod.GET)
	public ModelAndView change(@RequestParam int mboard_no, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("change");
		mav.addObject("dto", boardmapper.read(mboard_no));
		return mav;
	}

	// 게시글 수정
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute BoardVO vo) throws Exception {
		boardmapper.update(vo);
		return "redirect:Movieboard";
	}

	// 게시글 삭제
	@RequestMapping("delete")
	public String delete(@RequestParam int mboard_no) throws Exception {
		boardmapper.deleteReply(mboard_no);
		boardmapper.delete(mboard_no);
		return "redirect:Movieboard";
	}

	// 댓글 에 관한 부분 ----------------------------------------------------------
	// 댓글 입력
	@RequestMapping("insertReply")
	public void insertReply(@ModelAttribute ReplyVO vo, HttpSession session) {
		String member_id = (String) session.getAttribute("1");
		vo.setMember_id(member_id);
		boardmapper.createReply(vo);
	}

	// 댓글 목록(화면을 리턴)
	@RequestMapping("listReply")
	public ModelAndView listReply(@RequestParam int mboard_no, ModelAndView mav) {
		List<ReplyVO> list = boardmapper.listReply(mboard_no);
		mav.setViewName("listReply");
		mav.addObject("list", list);
		return mav;
	}

	// 댓글 목록(Json방식으로 처리 데이터를 리턴)
	@RequestMapping("listreplyJson")
	@ResponseBody // 리턴 데이터를 json으로 변환(생략 가능)
	public List<ReplyVO> listreplyJson(@RequestParam int mboard_no) {
		List<ReplyVO> list = boardmapper.listReply(mboard_no);
		return list;
	}

}
