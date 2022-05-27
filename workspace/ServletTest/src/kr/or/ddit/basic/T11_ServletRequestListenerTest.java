package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T11_ServletRequestListenerTest extends HttpServlet {

	
	   @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   //속성값 추가
		   req.setAttribute("ATTR1", "속성1");
		   //속성값 변경
		   req.setAttribute("ATTR1", "속성11");
		   //속성값 추가
		   req.setAttribute("ATTR2", "속성2");
		   //속성값 제거
		   req.removeAttribute("ATTR1");
	}
	   
	   
	   
	   @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
