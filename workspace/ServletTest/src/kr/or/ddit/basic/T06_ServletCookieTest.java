package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T06_ServletCookieTest extends HttpServlet {

	/*
	 * 쿠키란? 웹서버와 브라우저는 애플리케이션을 사용하는 동안 필요한 값을 쿠키를 통해 공유하여 상태를 유지한다.
	 * 
	 * 1. 구성요소
	 * -이름
	 * -값
	 * -유효기간(초)
	 * -도메인 : ex)www.somehost.com, .somhost.com
	 * => 쿠키의 도메인이 쿠키를 생성한 서버의 도메인을 벗어나면 브라우져는 쿠키를 저장(생성)하지 않는다.
	 *    (보안 떄문에...)
	 * - 경로 : 쿠키를 공유할 기준경로를 지정한다. => 지정하지 않으면 실행한 URL의 경로 부분이 사용됨.
	 * 
	 * 2. 동작방식
	 * - 쿠키생성단계: 생성한 쿠키를 응답데이터의 헤더에 저장하여 브라우저에 전송한다.
	 * - 쿠키저장단계 : 브라우저는 응답데이터에 포함된 쿠키를 쿠키저장소에 보관한다. (client에 저장)
	 * - 쿠키전송단계 : 브라우저는 저장한 쿠키를 요청이 있을 때마다 웹서버에 전송한다.(삭제되기 전까지..)
	 *              웹서버는 브라우저가 전송한 쿠키를 사용해서 필요한 작업을 수행한다.
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           // setCookieExam(req,resp); //쿠키 설정 예제
		
		    //readCookieExam(req, resp); //쿠키정보 읽기 예제
		
		    deleteCookieExam(req, resp);  //쿠키정보 삭제 예제
	}
	
	  
	
	
	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*
		 * 사용중인 쿠키 정보를 삭제하는 방법
		 * 1. 사용중인 쿠키정보를 이용하여 쿠키객체를 생성한다.
		 * 2. 쿠키객체의 최대 지속시간을 0으로 설정한다.
		 * 3. 설정한 쿠키객체를 응답헤더에 추가하여 전송한다.
		 */
		
		  Cookie cookie = null;
		  
		  Cookie[] cookies = req.getCookies();
		  
		  //응답헤더에 인코딩 및 Content Type 설정
		  resp.setCharacterEncoding("UTF-8");
		  resp.setContentType("text/html");
		  
		  PrintWriter out = resp.getWriter();
		  
		  String title = "쿠키정보 삭제 예제";
		  
		  out.println("<html>"
				      + "<head><title>" + title + "</title></head>"
				      + "<body>");
				if(cookies != null) {
					out.println("<h2>" + title + "</h2>");
					
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						
						if((cookie.getName()).equals("userId")) {
							cookie.setMaxAge(0); //단위는 초
							resp.addCookie(cookie);
							out.println("삭제한 쿠키 : " +cookie.getName() + "<br>");
						}
						out.print("쿠키 이름 : " + cookie.getName() + ", ");
						out.print("쿠키 값 : " + URLDecoder.decode(cookie.getValue(),"UTF-8")+"<br>");
					}
				}else {
					   out.println("<h2>쿠키정보가 없습니다.</h2>");
				   }
				   out.println("</body>");
				   out.println("</html>");
				}
	




	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//현재 도메인에서 사용중인 쿠키정보 배열 가져오기
		Cookie[] cookies = req.getCookies();
 		
		//응답헤더에 인코팅 및 Content Type 설정하기
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 읽기 예제";
		
		out.println("<html>"
	                +"<head><title>" + title + "</title></head>"
	                +"<body>");
		   if(cookies != null) {
			   out.println("<h2>"+title+"</h2>");
			   
			   for(Cookie cookie : cookies) {
				   out.println("name : " + cookie.getName()+"<br>");
				   out.println("value : " + URLDecoder.decode(cookie.getValue(),"UTF-8")+"<br>");
				   out.println("<hr>");
			   }
		   }else {
			   out.println("<h2>쿠키정보가 없습니다.</h2>");
		   }
		   out.println("</body>");
		   out.println("</html>");
		
	}


	protected void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     /*
      * 쿠키 정보를 설정하는 방법...
      * 
      * 1. 쿠키객체를 생성한다. 사용불가 문자(공백, [], () = , " / ? @ : ; )
      * Cookie cookie = new Cookie("키값", "value값");
      *   쿠키값은 사용불가문자를 제외한 나머지 출력가능한 아스키 문자 사용 가능함.
      *   => 이외의 값(예를 들면 한글)을 사용시에는 URLEncoder,encode() 사용하여 인코딩처리해준다.
      *   
      *   2. 쿠키 최대 지속시간을 설정한다.(초단위) => 지정하지 않으면 웹브라우저를 종료할 떄 쿠키를 함께 삭제한다. 
      *   cookie.setMaxAge(60*60*24); //24시간
      *   
      *   3. 응답헤더에 쿠키 객체를 추가한다.
      *   resp.addCookie(cookie);
      *   
      *   =>출력버퍼가 플러시 된 이후에는 쿠키를 추가할 수 없다. (응답헤더를  통해서 브라우저에 전달하기 때문에.,,)
      */
	
	    //쿠키생성하기
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
		
		//쿠키값에 한글을 사용 시 인코딩 처리를 해준다.
		Cookie name = new Cookie("name", URLEncoder.encode(req.getParameter("name"),"UTF-8"));
		
		//쿠키 소멸시간 설정(초단위) => 지정하지 않으면 웹브라우저를 종료할 때 쿠키를 함께 삭제한다. 
		userId.setMaxAge(60*60*24);  //1일
		userId.setHttpOnly(true);    //자바스크립트를 이용한 직접 접근 방지
		
		name.setMaxAge(60*60*48);     //2일
		
		//응답헤더에 쿠키 추가하기
		resp.addCookie(userId);
		resp.addCookie(name);		
	
		//응답헤더에 인코딩 및 Content type 설정
		resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html");
	    
	    PrintWriter out = resp.getWriter();
	    
	    String title = "쿠키 설정 예제";
	    
	    out.println("<html>"
	    		   +"<head>"
	    		+"<title>" +title + "</title>"
	    		+"</head>"
	    		+"<body>"
	    		+"<h1 align=\"center\">" + title +"</h1>"
	    		+"<ul>"
	    		+"<li><b>ID</b>: "+req.getParameter("userId") + "</li>"
	    		+"<li><b>이름</b>: "+req.getParameter("name") + "</li>"
	    		+"</ul>"
	    		+"</body>"
	    		+"</html>"
	    		);
	
	}
}
