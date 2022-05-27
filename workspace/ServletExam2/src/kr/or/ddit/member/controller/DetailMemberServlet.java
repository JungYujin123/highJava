package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/detail.do")
public class DetailMemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 파라미터정보 가져오기
		String memId = req.getParameter("memId");
		
		//2.서비스 객체 가져오기
		IMemberService memberService = IMemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		//3.회원정보 조회
		MemberVO mv = memberService.getMember(memId);
		
		if(mv.getAtchFileId() > 0) { //첨부파일이 존재하면...
			//첨부파일 정보 조회
			  AtchFileVO fileVO = new AtchFileVO();
			  fileVO.setAtchFileId(mv.getAtchFileId());
			  List<AtchFileVO> atchFileList = null;
			  try {
			  atchFileList = fileService.getAtchFileList(fileVO);
			} catch (Exception ex) {
                  ex.printStackTrace();
			}
			  req.setAttribute("atchFileList", atchFileList);
		}
	    req.setAttribute("mv", mv);
	   
	  
	   
	   //결과를 VIEW화면에 출력하기
	   req.getRequestDispatcher("/WEB-INF/views/member/detail.jsp").forward(req, resp);
	
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
