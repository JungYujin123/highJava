package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberService {

	
	   /**
	    * MemberVO에 담겨진 자료를 DB에 insert하는 메소드
	    * @param mv DB에 insert할 자료가 저장된 MemberVO 객체
	    * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	    */
	   public int insertMember(MemberVO mv);
	   
	   /**
	    * 주어진 회원ID가 존재하는지 여부를 알아내기 위한 메소드
	    * @param memId 검색할 회원ID
	    * @return 해당회원 ID가 있으면 true, 없으면 false
	    */
	   public boolean checkMember(String memId);
	   
	   /**
	    * 하나의 MemberVO자료를 이용하여 DB를 update하는 메소드
	    * @param mv update할 회원 정보가 담긴 MemberVO 객체
	    * @return DB작업이 성공하면 1, 실패하면 0
	    */
	   public int updateMember(MemberVO mv);
	   
	   
	   /**
	    * 회원ID를 매개변수로 받아 해당 회원정보를 삭제하는 메소드
	    * @param memId 삭제할 회원ID
	    * @return DB작업이 성공하면 1, 실패하면 0
	    */
	   public int deleteMember(String memId);
	   
	   
	   /**
	    * DB안의 mtmember 테이블 전체 레코드를 가져와서 List에 담아 반환하는 메소드
	    * 
	    * @return
	    */
	   public List<MemberVO> getAllMemberList();
	   
	   /**
		 * MemberVO에 담긴 자료를 이용하여 회원을 검색하는 메서드
		 * @param mv 검색할 자료가 들어있는 MemberVO객체
		 * @return 검색된 결과를 담은 List객체
		 */
		public List<MemberVO> searchMember(MemberVO mv);

		
}
