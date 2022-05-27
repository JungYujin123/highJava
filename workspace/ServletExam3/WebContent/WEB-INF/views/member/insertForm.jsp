<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>신규회원 등록</title>
</head>
<body>
  <form action="insert.do" method="post" enctype="multipart/form-data">
  
    <table>
      <tr>
       <td>작성자</td>
       <td><input type="text" name="boardWriter" value=""></td>
      </tr>
       <tr>
       <td>제목</td>
       <td><input type="text" name="boardTitle" value=""></td>
      </tr>
       <tr>
       <td>게시번호</td>
       <td><input type="text" name="boardNo" value=""></td>
      </tr>
       <tr>
       <td>게시날짜</td>
       <td><input type="text" name="boardDate" value=""></td>
      </tr>
       <tr>
       <td>게시내용</td>
       <td><textarea name="boardContent" rows="3" cols="20"></textarea></td>
      </tr>
      <tr>
       <td>첨부파일:</td>
       <td><input type="file" name="atchFile"></td>
      </tr>
    </table>
    <input type="submit" value="게시물 등록">
  
  </form>
</body>
</html>