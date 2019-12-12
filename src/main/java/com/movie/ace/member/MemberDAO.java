package com.movie.ace.member;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAO {

	@Inject
	private SqlSessionTemplate sqlSession;
	
	public MemberVO selectByEmail(String email) {
		return (MemberVO) sqlSession.selectOne("member.selectByEmail", email);
	}
	
	public MemberVO selectById(String id) {
		return (MemberVO) sqlSession.selectOne("member.selectById", id);
	}
	
	public Object loginCheck(String id) {
		return sqlSession.selectOne("member.loginCheck", id);
	}
	
	public void insertMember(RegisterRequest regReq) {
		sqlSession.insert("member.register", regReq);
		sqlSession.insert("authority", regReq);
	}
	
	public int idCheck(String id) {
		return (int) sqlSession.selectOne("member.idCheck", id);
	}
	
	//임시 멤버번호 체크
	public MemberVO noCheck() {
		return (MemberVO) sqlSession.selectOne("member.noCheck");
	}
}
