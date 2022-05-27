package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.INoticeService;
import kr.or.ddit.member.service.NoticeServiceImpl;
import kr.or.ddit.member.vo.NoticeVO;



@WebServlet("/notice/list.do")
public class SelectAllNoticeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 //1. 서비스 객체 생성하기
		  INoticeService noticeService = NoticeServiceImpl.getInstance();
		  
		  //2. 회원정보조회
		  List<NoticeVO> noList = noticeService.getAllList();
		  req.setAttribute("noList", noList);
		  
		  RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/list.jsp");
		  dispatcher.forward(req, resp);  //뷰페이지로 전달
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
