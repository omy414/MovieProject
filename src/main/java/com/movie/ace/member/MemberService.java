package com.movie.ace.member;

public interface MemberService {
	
	//회원가입
	void register(RegisterRequest regReq) throws Exception;
	
	//아이디 중복체크
	int idCheck(String id) throws Exception;
	
	//회원정보 수정
	void updateMember(ModifyRequest modReq)throws Exception;
	
	//임시 멤버 번호 체크
	MemberVO noCheck() throws Exception;
}
