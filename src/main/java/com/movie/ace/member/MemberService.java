package com.movie.ace.member;

public interface MemberService {
	
	void register(RegisterRequest regReq) throws Exception;
	
	int idCheck(String id) throws Exception;
	
	//임시 멤버 번호 체크
	MemberVO noCheck() throws Exception;
}
