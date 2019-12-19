package com.movie.ace.wishlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListDAO {

	@Autowired
	private SqlSession ss;

	@Autowired
	WishList wishlist;

	public void getWishList(HttpServletRequest req, HttpServletResponse res, int member_num) {

		String member_no = String.valueOf(member_num);
		wishlist.setMember_no(member_no);

		List<WishList> wishlist_return = new LinkedList<WishList>();
		wishlist_return = ss.getMapper(WishListMapper.class).getWishList(wishlist);

		Iterator<WishList> itr = wishlist_return.iterator();

		// 나중에 위시리스트를 넣을때 이미 member_no을 넣게 된다면 아래 3줄은 필요가 없겠지?
		while (itr.hasNext()) {
			itr.next().setMember_no(member_no); // 지금은 1이지만 바로 위의 member_no_return 로 넣어라.
		}

		// member_no 꺼내려고 쓴거다.
		if (wishlist_return.size() != 0) {
			wishlist = wishlist_return.get(0);
			req.getSession().setAttribute("wishlist", wishlist_return);
			req.getSession().setAttribute("member_num", wishlist.getMember_no());
		} else {
			req.getSession().setAttribute("wishlist", wishlist_return);
			req.getSession().setAttribute("member_num", wishlist.getMember_no());
		}
	}

	public WishList getDetail(HttpServletRequest req, HttpServletResponse res, WishList moviecode) {

		wishlist = ss.getMapper(WishListMapper.class).getDetail(moviecode); // 안되면 여기를 moviecd 만 넘기는 걸로 해보자.
		return wishlist;
	}

	public void setMoviereply(ModalWrite modal) {
		ss.getMapper(WishListMapper.class).setMovieReply(modal);
	}

	public List<ModalGetReplys> getMoviereply(ModalGetReplys modalgetreply) {
		List<ModalGetReplys> listreply = new LinkedList<ModalGetReplys>();
		listreply = ss.getMapper(WishListMapper.class).getMovieReply(modalgetreply);
		return listreply;
	}

	public void delWishList(WishList modify) {
		ss.getMapper(WishListMapper.class).delWishList(modify);
	}

	public void insertWishList(WishList modify) {
		ss.getMapper(WishListMapper.class).insertWishList(modify);
	}

	public int checkBeforeInsert(WishList movie) {
		int duplicate;
		duplicate = ss.getMapper(WishListMapper.class).checkBeforeInsert(movie);
		System.out.println(duplicate+"%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		return duplicate;
	}
}
