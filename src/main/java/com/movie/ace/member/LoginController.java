package com.movie.ace.member;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@Inject
	UserDetailsService userSer;
	@Inject
	MemberService memberService;
	
	//View로 넘길 장르 리스트 초기화 함수
	@ModelAttribute("genreList")
	List<String> genreList(){
		List<String> genreList = new ArrayList<String>();
		genreList.add("액션");
		genreList.add("로맨스");
		genreList.add("스릴러");
		return genreList;
	}
	
	@RequestMapping(value="/loginPage", method = RequestMethod.GET)
	public String loginURL() throws Exception{
		return "/member/loginPage";
	}
	
	@RequestMapping(value="/loginModalPage", method = RequestMethod.GET)
	public String loginModalURL() throws Exception{
		return "/member/loginModalPage";
	}
	
	@RequestMapping("/access_denied_page")
	public String accessDeniedPage() throws Exception{
		return "/member/access_denied_page";
	}
	
	@GetMapping("/modifyMyinfo")
	public ModelAndView modifyForm(@ModelAttribute("modifyRequest") ModifyRequest modReq) {
		ModelAndView mv = new ModelAndView("/member/modifyForm");
		mv.addObject(genreList());
		return mv;
	}
	
	@PostMapping("/modifyMyinfo")
	public ModelAndView modify(ModifyRequest modReq, Errors errors, HttpServletRequest request) throws Exception {
		new ModifyRequestValidator().validate(modReq, errors);
		ModelAndView mv = new ModelAndView();
		String rawPw = modReq.getPw();
		
		if(errors.hasErrors()) {
			mv.setViewName("/member/modifyForm");
			return mv;
		}
		memberService.updateMember(modReq);
		HttpSession session = request.getSession(true);
		session.setAttribute("rawPw", rawPw);
		mv.setViewName("/member/modifyOk");
		return mv;
	}
	
	@GetMapping("/test")
	public String test() {
		return "/member/test";
	}

}
