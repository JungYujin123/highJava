<%@page import="kr.or.ddit.member.vo.NoticeVO"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

    List<NoticeVO> noList = (List<NoticeVO>) request.getAttribute("noList");
   String msg = request.getParameter("msg")==null?"":request.getParameter("msg");

%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
   <table border="1">
      <tr>
         <th>작성자</th>
         <th>제목</th>
         <th>게시번호</th>
         <th>게시날짜</th>
         <th>게시내용</th>
         <th>첨부파일</th>
      </tr>
      <%
       int memSize = noList.size();
      if(memSize > 0){
         for(int i=0; i<memSize; i++){
      %>
      <tr>
         <td><%= noList.get(i).getBoardWriter() %></td>
         <td><%= noList.get(i).getBoardTitle() %></td>
         <td><a href="detail.do?memId=<%=noList.get(i).getBoardNo()%>"><%= noList.get(i).getBoardNo() %></a></td>
         <td><%= noList.get(i).getBoardDate() %></td>
         <td><%= noList.get(i).getBoardContent() %></td>
         <td><%= noList.get(i).getAtchFileId() %></td>
      </tr>
      <%
         }
      }else{
      %>
      <tr>
         <td colspan="4">게시물이 없습니다.</td>
      </tr>
      <%
      }
      %>
   </table>
   
   <%
     if(msg.equals("성공")){
   %>
   <script>
      alert('정상적으로 처리되었습니다.');
   </script>
   <%
     }
   %>
</body>
</html>