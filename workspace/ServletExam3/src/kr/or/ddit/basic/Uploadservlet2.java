package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.omg.CORBA.PRIVATE_MEMBER;

@WebServlet("/upload2")
//아래 어노테이션은 반드시 선언해야 함. 또는 톰캣 속성값(allowCasulaMultipartParsing=true)을 설정해야 함.
@MultipartConfig(fileSizeThreshold = 1024*1024*3, maxFileSize=1024*1224*40, maxRequestSize=1024*1024*50)
public class Uploadservlet2 extends HttpServlet{
	
	 private static final String UPLOAD_DIR = "upload_files";
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  //Multipart Parsing 전에 피라미터 정보 조회해 보기
	      System.out.println("Multipart Parsing전 : "
	                     + req.getParameter("sender") );
	
	      String uploadPath = getServletContext().getRealPath("")
	    		  + File.separator + UPLOAD_DIR;
	      File uploadDir = new File(uploadPath);
	      if(!uploadDir.exists()) {
	    	  uploadDir.mkdir();
	      }
	      try {
	    	  String fileName= "";
	    	  for(Part part : req.getParts()) {
	    		  
	    		  System.out.println("Part Name : " + part.getName());
	    		  System.out.println("Part Size : " + part.getSize());
	    		  
	    		  fileName = getFileName(part);
	    		  if(fileName != null && !fileName.equals("")) {
	    			  //파일이 비어있지 않은 경우..
	    			 //파일 저장(업로드)
	    			  part.write(uploadPath + File.separator + fileName); 
	    			  System.out.println("파일명 :  " +fileName+ "업로드 완료!");
	    			
	    		  }
	    	  }
	    	  System.out.println("파라미터값(sender) => " + req.getParameter("sender"));
	    	  
	    	  resp.setContentType("text/html");
	    	  resp.getWriter().print("업로드 완료...!!!!");
	      }catch (Exception ex) {
		}
	}

    /**
     * Part 객체 파싱하여 파일이름 추출하기
     * @param part
     * @return 파일명 : 파일명이 존재하지 않으면 NULL값 리턴함(폼필드)
     */
	private String getFileName(Part part) {
	
		/*
		  * Content-Disposition 헤더에 대하여...
		  * 
		  * 1. response header에서 사용되는 경우...ex) 파일 다운로드
		  * Content-Disposition: inline(default)
		  * Content-Disposition: attachment  //파일 다운로드
		  * Content-Disposition: attachment; filename="a.jpg" //해당이름으로 다운로드
		  * 
		  * 2.multipart body를 위한 헤더정보로 사용되는 경우...ex)파일 업로드
		  *  Content-Disposition: form-data
		  *  Content-Disposition: form-data; name="fieldName"
		  *  Content-Disposition: form-data; name="fieldName"; filename="a.jpg"
		  */
		
		   for(String content : part.getHeader("Content-Disposition").split(";")) {
			   
			   if(content.trim().startsWith("filename")) {
				   return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
			   }
		   }
		
		
		return null;
	}
	
	 
	      
	}

