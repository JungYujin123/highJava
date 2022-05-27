package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class IMemberServiceImpl implements IMemberService {

	   
//	 1. 자기자신 class의 참조변수를 멤버변수로 선언한다.
//	 (이 변수는 private static으로 지정한다.)
	
	private static IMemberService memService;
	
	
	
	
	   // 사용할 DAO의 객체변수를 선언한다.
	   private IMemberDao memDao;
	   
	   	   
//	 2. 생성자를 private으로 한다.
//	 (외부 생성자에 접근을 못하게 하기 위해서 즉, 외부에서 new명령을 사용하지 못하게 하기 위해..)
//	 3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
//	  (이 메서드의 이름은 보통  getInstance()로 지정한다. static으로 지정함.)
	   public IMemberServiceImpl() {
	        memDao = MemberDaoImpl.getInstance();
	   }	  
	   public static IMemberService getInstance() {
		   if(memService == null) {
			   memService = new IMemberServiceImpl();
		   }
	    return memService;
	   }
	   

	   @Override
	   public int insertMember(MemberVO mv) {
	      return memDao.insertMember(mv);
	   }

	   @Override
	   public boolean checkMember(String memId) {
	      return memDao.checkMember(memId);
	   }

	   @Override
	   public int updateMember(MemberVO mv) {
	      return memDao.updateMember(mv);
	   }

	   @Override
	   public int deleteMember(String memId) {
	      return memDao.deleteMember(memId);
	   }

	   @Override
	   public List<MemberVO> getAllMemberList() {
	      return memDao.getAllMemberList();
	   }
	   
	   @Override
	   public List<MemberVO> searchMember(MemberVO mv) {
	      return memDao.searchMember(mv);
	   }
}
