package kr.or.ddit.member.service;

import java.util.List;


import kr.or.ddit.member.dao.INoticeDao;

import kr.or.ddit.member.dao.NoticeDaoImpl;
import kr.or.ddit.member.vo.NoticeVO;

public class NoticeServiceImpl implements INoticeService {

	   
//	 1. 자기자신 class의 참조변수를 멤버변수로 선언한다.
//	 (이 변수는 private static으로 지정한다.)
	
	private static INoticeService notService;
	
	
	
	
	 // 사용할 DAO의 객체변수를 선언한다.
	   private INoticeDao noDao;
	   
	   	   
//	 2. 생성자를 private으로 한다.
//	 (외부 생성자에 접근을 못하게 하기 위해서 즉, 외부에서 new명령을 사용하지 못하게 하기 위해..)
//	 3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
//	  (이 메서드의 이름은 보통  getInstance()로 지정한다. static으로 지정함.)
	   public NoticeServiceImpl() {
	        this.noDao = NoticeDaoImpl.getInstance();
	   }	  
	   public static INoticeService getInstance() {
		   if(notService == null) {
			   notService = new NoticeServiceImpl();
		   }
	    return notService;
	   }
	   
	   

	   @Override
	   public int insert(NoticeVO nv) {
	      return noDao.insert(nv);
	   }

	   @Override
	   public boolean check(String boardWriter) {
	      return noDao.check(boardWriter);
	   }

	   @Override
	   public int update(NoticeVO nv) {
	      return noDao.update(nv);
	   }

	   @Override
	   public int delete(String boardWriter) {
	      return noDao.delete(boardWriter);
	   }

	   @Override
	   public List<NoticeVO> getAllList() {
	      return noDao.getAllList();
	   }
	   
	   @Override
	   public List<NoticeVO> search(NoticeVO nv) {
	      return noDao.search(nv);
	   }
	@Override
	public NoticeVO getNotice(String boardWriter) {
		// TODO Auto-generated method stub
		return noDao.getNotice(boardWriter);
	}
}
