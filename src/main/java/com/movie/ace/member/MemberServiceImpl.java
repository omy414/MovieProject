package com.movie.ace.member;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Inject
	PasswordEncoder passwordEncoder;
	 
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	//회원 등록
	@Override
	public void register(RegisterRequest regReq) throws Exception {
		MemberVO member_id = memberDAO.selectById(regReq.getId());
		if(member_id!=null) {
			throw new AlreadyExistingIdException(regReq.getId()+" is duplicate id.");
		}
		MemberVO member_email = memberDAO.selectByEmail(regReq.getEmail());
		if(member_email!=null) {
			throw new AlreadyExistingEmailException(regReq.getEmail()+" is duplicate email.");
		}
		String encPassword = passwordEncoder.encode(regReq.getPw());
		regReq.setPw(encPassword);
		memberDAO.insertMember(regReq);
	}
	
	//회원정보 수정
	@Override
	public void updateMember(ModifyRequest modReq) throws Exception {
		String encPassword = passwordEncoder.encode(modReq.getPw());
		modReq.setPw(encPassword);
		memberDAO.updateMember(modReq);
	}
	
	//아이디 중복 체크
	@Override
	public int idCheck(String id) throws Exception {
		int cnt = memberDAO.idCheck(id);
		return cnt;
	}
	
	//임시 멤버 번호 체크
	@Override
	public MemberVO noCheck() throws Exception {
		MemberVO member_no = memberDAO.noCheck();
		return member_no;
	}
	
	
}
