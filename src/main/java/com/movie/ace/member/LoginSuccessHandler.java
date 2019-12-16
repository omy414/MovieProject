package com.movie.ace.member;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	private String loginId;
	private String defaultUrl;
	private String password;
	
	@Inject
	private UserDetailsService userSer;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String username = request.getParameter(loginId);
		System.out.println("username= "+username);
		
		String rawPassword = request.getParameter(password);
		System.out.println("rawPassword= "+rawPassword);
		
		
		clearAuthenticationAttributes(request);
		
		mkUserInfo(username, rawPassword, request);
		
		resultRedirectStrategy(request, response, authentication);
		
	}
	
	protected void mkUserInfo(String username, String rawPassword, HttpServletRequest request) {
		CustomUserDetails userInfo = ((CustomUserDetailsService) userSer).getUserInfo(username); 
		HttpSession session = request.getSession(true);
		session.setAttribute("userInfo", userInfo);
		session.setAttribute("rawPw", rawPassword);
	}
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session==null) return;
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(savedRequest!=null) {
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStratgy.sendRedirect(request, response, targetUrl);
        } else {
            redirectStratgy.sendRedirect(request, response, defaultUrl);
        }	
	}
	

}
