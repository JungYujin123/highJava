package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T09_ServletFilter2 implements Filter{

	@Override
	public void destroy() {
       
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("T09_ServletFilter2 시작");
		
		//서블릿 수행시간 계산하기
		long startTime = System.nanoTime();
		
		//필터 체인으로 실행한다. 
		fc.doFilter(req, resp);  //2번째 필터로 서비스 실행 후 다시 이곳으로 돌아옴 1-2-서비스-2-1
		
		System.out.println("수행시간(ns) : " + (System.nanoTime() - startTime));
		
		System.out.println("T09_ServletFilter2  끝.");
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}
