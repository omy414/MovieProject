package com.movie.ace.wishlist;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detail/*")
public class DetailController {
	  
	@Autowired
	private SqlSession ss;

	@Autowired
	private WishListDAO wishDAO;

	@Autowired
	WishList wishlist;

	@Autowired
	ModalGetReplys modal_getreply;

	@RequestMapping("modal")
	public WishList modalDetail(@ModelAttribute WishList moviecode, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("^^^^^^^^^^^^^^^^^^modal Detail들어옴^^^^^^^^^^^^^^^^^^^^");
		wishlist = wishDAO.getDetail(req, res, moviecode);
		return wishlist;
	}

	@RequestMapping("write")
	public void modalWrite(@ModelAttribute ModalWrite modalwrite, HttpServletRequest req, HttpServletResponse res) {
		//modalwrite.setMovie_rate(5); // 평점 구현되면 여기도 받아와서, 구현해야한다.
		wishDAO.setMoviereply(modalwrite);
	}

	@RequestMapping("getReplys")
	public List<ModalGetReplys> modalGetReplys(@ModelAttribute ModalGetReplys modal_getReply, HttpServletRequest req,
			HttpServletResponse res) {
		List<ModalGetReplys> test = new LinkedList<ModalGetReplys>();
		return wishDAO.getMoviereply(modal_getReply);
	}
}
