package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;


import kr.or.ddit.member.vo.NoticeVO;


/**
 * 실제 DB에 연결해서 SQL문을 수행하여 결과를 작성해
 *  Service에 전달하는 DAO의 인터페이스
 * @author PC-23
 *
 */
public interface INoticeDao {

	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO 객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 */	
	public int insert(NoticeVO nv); 

	
	/**
	 * 주어진 회원ID가 존재하는 지 여부를 알아내기 위한 메서드
	 * @param memId 검색할 회원ID
	 * @return 해당회원 ID가 있으면 true, 없으면 false
	 */
	public boolean check(String boardWriter);
	
	
	
	
	 /**
	    * 주어진 회원ID로 회원정보를 알아내기 위한 메서드
	    * @param memId 검색할 회원ID
	    * @return 해당회원 ID가 있으면 회원정보 리턴함
	    */
	   
 public NoticeVO getNotice(String boardWriter);
	
	
	
	
	/**
	 * 하나의 MemberVO자료를 이용하여 DB를 update하는 메서드
	 * 
	 * @param mv update할 회원 정보가 담긴 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int update(NoticeVO nv);
	
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원정보를 삭제하는 메서드
	 * @param memId 삭제할 회원 ID
	 * @return 작업성공: 1, 작업실패 : 0
	 */
	public int delete(String boardWriter);
		
	
	/**
	 * DB안의 mymember 테이블 전체 레코드를 가져와서 List에 담아 반환하는 메서드
	 * 
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<NoticeVO> getAllList();
	
	
	/**
	 * MemberVO에 담긴 자료를 이용하여 회원을 검색하는 메서드
	 * @param mv 검색할 자료가 들어있는 MemberVO객체
	 * @return 검색된 결과를 담은 List객체
	 */
	public List<NoticeVO> search(NoticeVO nv);

	
	
	
	
	
}
