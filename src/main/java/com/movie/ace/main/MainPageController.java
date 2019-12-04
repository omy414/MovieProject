package com.movie.ace.main;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
@RequestMapping("/")
public class MainPageController {

		
	
		//테스트용 메인홈1
		@RequestMapping(value = "/home", method = RequestMethod.GET)
		public String home(HttpServletRequest req, HttpServletResponse res) { //http서블릿
			return "home";
		}
		
		//테스트용 홈2
		@RequestMapping(value = "/Main", method = RequestMethod.GET)
		public String Main() {
			return "Main";
		}
		
		@RequestMapping(value = "/Test", method = RequestMethod.GET)
		public String Test(Locale locale, Model model) { //로컬 ,모델
			return "Test";
		}
		
}
