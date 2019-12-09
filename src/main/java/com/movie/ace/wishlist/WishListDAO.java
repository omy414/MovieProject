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
	
	public void getWishList(HttpServletRequest req,HttpServletResponse res) {
	
//	String member_no = (String)req.getSession().getAttribute("member_no");
//	wishlist.setMember_number(member_no);
	
	
	List<WishList> wishlist_return = new LinkedList<WishList>(); 
	wishlist_return = ss.getMapper(WishListMapper.class).getWishList(wishlist);
	
	Iterator <WishList> itr = wishlist_return.iterator();
	
//	String member_no_return = (String)req.getSession().getAttribute("member_no");
	
	while(itr.hasNext()) {
		
	 	itr.next().setMember_no("1");  //지금은 1이지만 바로 위의 member_no_return 로 넣어라.
	}
	
	//member_no 꺼내려고 쓴거다.
	wishlist = wishlist_return.get(0);
	
	req.getSession().setAttribute("wishlist", wishlist_return);
	req.getSession().setAttribute("member_num", wishlist.getMember_no());
	}
	
	public WishList getDetail(HttpServletRequest req,HttpServletResponse res,WishList moviecode) {
		
		wishlist = ss.getMapper(WishListMapper.class).getDetail(moviecode); //안되면 여기를 moviecd 만 넘기는 걸로 해보자.
		return wishlist;
	} 
	
	public void setMoviereply(ModalWrite modal) {
		ss.getMapper(WishListMapper.class).setMovieReply(modal);
	}
	
	public List<ModalGetReplys> getMoviereply(ModalGetReplys modalgetreply){
		List<ModalGetReplys> listreply = new LinkedList<ModalGetReplys>();
		listreply = ss.getMapper(WishListMapper.class).getMovieReply(modalgetreply);		
		return listreply;
	}
	
	public void delWishList(WishList modify) {
		ss.getMapper(WishListMapper.class).delWishList(modify);
	}
}
