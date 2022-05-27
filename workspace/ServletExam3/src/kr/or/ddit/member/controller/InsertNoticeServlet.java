package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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


@WebServlet("/insert.do")
@MultipartConfig(fileSizeThreshold = 1024*1024*3, maxFileSize=1024*1224*40, maxRequestSize=1024*1024*50)
public class InsertNoticeServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
	   req.getRequestDispatcher("/WEB-INF/views/member/insertForm.jsp").forward(req, resp);
	

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	         
		//1.요청 파라미터 정보 가져오기
		String boardWriter = req.getParameter("boardWriter");
		String boardTitle = req.getParameter("boardTitle");
		String boardNo = req.getParameter("boardNo");
		String boardDate = req.getParameter("boardDate");
		String boardContent = req.getParameter("boardContent");
		
		
		//2.서비스 객체 생성하기
		INoticeService noticeService = NoticeServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = new AtchFileVO();
		
		try {
			//첨부파일 목록 자저장(공통기능)
			atchFileVO = fileService.saveAtchFileList(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//3.회원정보 등록
		NoticeVO nv = new NoticeVO();
		nv.setBoardContent(boardContent);
		nv.setBoardDate(boardDate);
		nv.setBoardNo(boardNo);
		nv.setBoardTitle(boardTitle);
		nv.setBoardWriter(boardWriter);
		nv.setAtchFileId(atchFileVO.getAtchFileId());
		
		int cnt = noticeService.insert(nv);
		
		String msg = "";
		if(cnt>0) {
			msg="성공";
		}else {
			msg="실패";
		}
		
		req.setAttribute("msg", msg);
		
		//4. 목록 조회화면으로 이동
             // req.getRequestDispatcher("/member/list.do").forward(req, resp);
             
		//요청한 것이 잘못됐을 때 올바른 주소를 알려줌 2번 적용
		String redirectUrl =
		req.getContextPath()+"/member/list.do?msg="
		                    +URLEncoder.encode(msg,"UTF-8");
		
		resp.sendRedirect(redirectUrl);//목록조회 화면으로 리다이렉트
              
}
}