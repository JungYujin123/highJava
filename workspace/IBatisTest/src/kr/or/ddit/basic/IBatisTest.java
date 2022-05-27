package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class IBatisTest {
	public static void main(String[] args) {
	  //iBatis를 이용하여 DB자료를 처리하는 순서
	  //1.iBatis의 환경설정파일을 읽어와 실행시킨다.
	  
	  try {
		  //1-1. xml문선 읽어오기
		  //설정파일의 인코딩정보 설정하기
		  Charset charset = Charset.forName("UTF-8");
		  Resources.setCharset(charset);
		  
		  Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
		  
		  //1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성하기
		  SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		  
		  rd.close(); //Reader 객체 닫기
		  
//		  //2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
//		  //2-1. insert작업 연습(같은 아이디가 이미 존재하면 오류 떠 미리 확인 및 삭제 delete from MYMEMBER where MEM_ID = 'd001'; commit;)
//		  System.out.println("insert작업 시작...");
//		  
//		  //1) 저장할 데이터를 VO에 담는다
//		  MemberVO mv = new MemberVO();
//		  mv.setMemId("d001");
//		  mv.setMemName("강감찬");
//		  mv.setMemTel("010-1111-1111");
//		  mv.setMemAddr("경상남도 진주시");
//		  
		  
		  //2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
		  //형식) smc.insert("namespace값.id값", 파라미터클래스);
		  // 반환값 : 성공하면 null이 반환된다.
		  
		  //첫번째 Object 이용한 insert
		/*  Object obj = smc.insert("memberTest.insertMember", mv);
		  if(obj == null) {
			  System.out.println("insert작업 성공...");
		  }else {
			  System.out.println("insert작업 실패!!!");
		  }
		  System.out.println("-----------------------------------");*/
	
		 /* //2번째방법 업데이트로 변경한  단순 insert법
		  int cnt = smc.update("memberTest.insertMember", mv);
		  if(cnt >0) {
			  System.out.println("insert작업 성공...");
		  }else {
			  System.out.println("insert작업 실패!!!");
		  }
		  System.out.println("-----------------------------------");
		  */
		  
		  
		/*  //2-2. update 연습
		  System.out.println("update작업 시작...");
		  
		  MemberVO mv2 = new MemberVO();
		  mv2.setMemId("d001");
		  mv2.setMemName("이순신");
		  mv2.setMemTel("012-111");
		  mv2.setMemAddr("대전시 중구 대흥동");
		  
		  //update()메서드의 반환값은 성공한 레코드 수이다.
		  int cnt= smc.update("memberTest.updateMember",mv2);
		  if(cnt>0) {
			  System.out.println("update작업 성공...");
			  
		  }else {
			  System.out.println("update작업 실패!!");
		  }
		  System.out.println("-----------------------------------");
	  */
		  
//		  //2-3.delete 연습
//		  System.out.println("delete 작업 시작...");
//		  
//		  //delete메서드 반환값 : 성공한 레코드 수
//		  
//		  int cnt = smc.delete("memberTest.deleteMember", "d001");
//		  if(cnt>0) {
//			  System.out.println("delete작업 성공");
//		  }else {
//			  System.out.println("delete작업 실패!!");
//		  }
//		  System.out.println("-----------------------------------");
		  
		/*  오류 :There is no statement named memberTest.deletMember in this SqlMap.
		  1. (member.xml에 )쿼리 작성을 안해놨거나 2.아이디 잘못쳤거나 3. 설정파일 경로 안알려줬거나
		  */
	/*	  
		  //2-4.select 연습
		  //1)응답 결과가 여러개인 경우
		  System.out.println("select연습 시작(응답결과가 여러개인 경우...)");
		  
		   * 응답 결과가 여러개일 경우에는 queryForList메서드를 사용한다. 값 던져줌
		   * 이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO테이터를 List에 추가해주는 작업을 자동으로 수행한다.
		   
		  
		  List<MemberVO> memList = smc.queryForList("memberTest.getMemberAll");
		
		   for(MemberVO mv2:memList) {
			   System.out.println("ID : " + mv2.getMemId());
			   System.out.println("이름 : " + mv2.getMemName());
			   System.out.println("전화번호 : " + mv2.getMemTel());
			   System.out.println("주소 : " + mv2.getMemAddr());
			   System.out.println("-----------------------------------");
		   }
		  */
		  
		  //2) 응답이 1개일 경우
		  System.out.println("select 연습 시작(결과가 1개일 경우)...");
		  
		  //응답결과가 1개가 확실한 경우에는 queryForObject메서드를 이용한다.
		  
		  MemberVO mv3 = (MemberVO)smc.queryForObject("memberTest.getMember", "a009");
		  System.out.println("ID : " + mv3.getMemId());
		   System.out.println("이름 : " + mv3.getMemName());
		   System.out.println("전화번호 : " + mv3.getMemTel());
		   System.out.println("주소 : " + mv3.getMemAddr());
		   System.out.println("출력 끝...");
		  
		  
		  
		  
		  
		  
	  }catch(IOException ex) {
		  ex.printStackTrace();
	  }catch (SQLException ex) {
		ex.printStackTrace();
	}
	}
}
