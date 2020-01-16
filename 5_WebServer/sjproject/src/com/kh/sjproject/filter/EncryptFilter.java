package com.kh.sjproject.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.sjproject.wrapper.EncryptWrapper;

@WebFilter(urlPatterns = {"/member/login.do", "/member/signUp.do", "/member/updatePwd.do", "/member/deleteMember.do"})
public class EncryptFilter implements Filter {

    public EncryptFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		// Encrypt Wrapper
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		
		
		// Encrypt Wrapper 객체 생성
		EncryptWrapper encWrapper = new EncryptWrapper(req); // EncryptWrapper는 기본 생성자가 없음
		
		chain.doFilter(encWrapper, response); // request -> encWrapper 암호화가 적용된, 포장된 request로 대체함
		// 왜냐면 encWrapper는 HttpServletRequestWrapper를 상속받았으므로 이상한건 아님
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
