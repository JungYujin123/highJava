package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02_ServletTest extends HttpServlet {

/*
 * 서블릿 동작방식에 대하여
 * 
 * 1.사용자(클라이언트)가 URL을 클릭하면 HTTP Request를 서블릿 컨테이너로 전송(요청)한다.
 * 2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 하는 지 검색한다.
 * (로딩이 안된 경우에는 로딩함, 로딩시 init()메서드 호출됨.)
 * 3. 서블릿 컨테이너는 요청을 처리할 개별 스레드 객체를 생성하여 해당 서블릿 객체의 service()메서드를 호출한다.
 * (HttpServletRequest 및 HttpServletResponse 객체를 생성하여 파라미터로 넘겨준다.)
 * 4. service()메서드는 메서드 타입을 체크하여 적절한 메서드를 호출한다. 
 * (doGet,doPost,doPut,doDelete 등)
 * 5. 요청 처리가 완료되면, HttpServletRequest 및 HttpServletReponse 객체는 소멸된다.
 * 6. 컨테이너로부터 서블릿이 제거되는 경우에 destroy()메서드가 호출된다.  
 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//POST 방식으로 넘어오는 Body데이터를 인코딩 처리하기
		//반드시 request에서 값을 가져오기 전에 먼저 설정해야 함.
		req.setCharacterEncoding("UTF-8");
		
		//요청메세지로부터 name값을 가져오기
		String name = req.getParameter("name");
	
	    System.out.println("name => " +name);
	    
	    //응답메시지 인코딩 설정
	    //(Content-Type의 ; charset=UTF-8과 동일)
	    resp.setCharacterEncoding("UTF-8");
	    //응답메시지의 컨텐트 타입 설정
	    resp.setContentType("text/plain");
	    
	    //실제 수행할 기능(로직)이 시작되는 부분...
	  //문자열이기때문에 outputStream대신 Writer사용
	    PrintWriter out = resp.getWriter(); 
	    
	    out.println("name => " +name);
	    out.println("서블릿 경로 => " +req.getServletPath());
	    out.println("컨텍스트 경로 => " +req.getContextPath());
	}

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
}



