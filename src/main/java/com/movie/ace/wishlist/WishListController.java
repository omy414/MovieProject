package com.movie.ace.wishlist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WishListController {

	@Autowired
	private WishListDAO wishDAO;

	@RequestMapping(value = "/Test", method = RequestMethod.GET)
	public String Movielist(HttpServletRequest req, HttpServletResponse res) {
			System.out.println("************************/Test 들어옴***********************************");
			wishDAO.getWishList(req, res);
			System.out.println("************************/Test나간다****************************************************");
		return "/wishlist/Test";
	}
	
	@RequestMapping(value = "/MovieDel", method = RequestMethod.GET)
	public String Moviedelete(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("************************/MOVIE DELETE 들어옴***********************************");
		String moviecd = req.getParameter("moviecd");
		String memberNo = req.getParameter("member_no");
		
		WishList del_wishlist = new WishList(); 
		del_wishlist.setMoviecd(moviecd);
		del_wishlist.setMember_no(memberNo);
		
		wishDAO.delWishList(del_wishlist);
		wishDAO.getWishList(req, res);
		
		System.out.println("************************/MOVIE DELETE나간다****************************************************");
		return "/wishlist/Test";
	}
}
	
