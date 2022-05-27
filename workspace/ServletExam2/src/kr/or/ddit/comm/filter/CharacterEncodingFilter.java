package kr.or.ddit.comm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	private String encoding;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("인코딩 설정 정보 : " + encoding);
		 
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
	
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		 if(config.getInitParameter("encoding") == null) {
			 this.encoding = "UTF-8"; //기본인코팅 UTF-8로 설정
		 }else {
			 this.encoding = config.getInitParameter("encoding");
		 }
	}

}
