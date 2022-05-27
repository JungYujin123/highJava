package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T10_ServletContextListenerTest extends HttpServlet {

	   @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   //속성값 추가
		   req.getServletContext().setAttribute("ATTR1", "속성1");
		   //속성값 변경
		   req.getServletContext().setAttribute("ATTR11", "속성11");
		   //속성값 추가
		   req.getServletContext().setAttribute("ATTR2", "속성2");
		   
		   //속성값 제거
		   getServletContext().removeAttribute("ATTR1");
	}
	   
	   
	   //브라우저에 출력은 없음,  MyServletContextListener값 콘솔에  출력
	   
	   
	   
	   @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
