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

import kr.or.ddit.member.service.INoticeService;
import kr.or.ddit.member.service.NoticeServiceImpl;
import kr.or.ddit.member.vo.NoticeVO;


@WebServlet("/Notice/detail.do")
public class DetailNoticeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 파라미터정보 가져오기
		String boardWriter = req.getParameter("boardWriter");
		
		//2.서비스 객체 가져오기
		INoticeService noticeService = NoticeServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		//3.회원정보 조회
		NoticeVO nv = noticeService.getNotice(boardWriter);
		
		if(nv.getAtchFileId() > 0) { //첨부파일이 존재하면...
			//첨부파일 정보 조회
			  AtchFileVO fileVO = new AtchFileVO();
			  fileVO.setAtchFileId(nv.getAtchFileId());
			  List<AtchFileVO> atchFileList = null;
			  try {
			  atchFileList = fileService.getAtchFileList(fileVO);
			} catch (Exception ex) {
                  ex.printStackTrace();
			}
			  req.setAttribute("atchFileList", atchFileList);
		}
	    req.setAttribute("nv", nv);
	   
	  
	   
	   //결과를 VIEW화면에 출력하기
	   req.getRequestDispatcher("/WEB-INF/views/member/detail.jsp").forward(req, resp);
	
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
