package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.NoticeVO;
import kr.or.ddit.util.JDBCUtil2;
import kr.or.ddit.util.SqlMapClientFactory;


public class NoticeDaoImpl implements INoticeDao {
	
	
	// 1. 자기자신 class의 참조변수를 멤버변수로 선언한다.
	 // (이 변수는 private static으로 지정한다.)
	private static INoticeDao noDao;
	
	private SqlMapClient smc;
	
	// 2. 생성자를 private으로 한다.
	private NoticeDaoImpl() {	
		smc = SqlMapClientFactory.getInstance();
	}
	// 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
   //(이 메서드의 이름은 보통  getInstance()로 지정한다. static으로 지정함.)
	public static INoticeDao getInstance() {
		
		if(noDao == null) {
			noDao = new NoticeDaoImpl();
		}
		return noDao;
	}
	

	@Override
	public int insert(NoticeVO nv) {

           
		  int cnt = 0;
		
		 try {
			 cnt = smc.update("notice.insert",nv);				
		 } catch (SQLException ex) {			
            ex.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean check(String boardWriter) {

	    boolean isExist = false;
        
        try {
        	int cnt = (int)smc.queryForObject("notice.check", boardWriter);
       	 
       	 if(cnt > 0) {
       		 isExist = true;
       	 }
       	 
        }catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return isExist;
	}

	@Override
	public int update(NoticeVO nv) {

		int cnt =0;
		
		try {
			cnt = smc.update("notice.update", nv);
		 }catch (SQLException ex) {		 
			ex.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int delete(String boardWriter) {

		int cnt = 0;
		
		try {
			smc.delete("notice.delete",boardWriter);
		}catch (SQLException ex) {		
			ex.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<NoticeVO> getAllList() {

		List<NoticeVO> noList = new ArrayList<NoticeVO>();
		
		try {
			noList = smc.queryForList("notice.getAllList");
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return noList;
	}

	@Override
	public List<NoticeVO> search(NoticeVO nv) {
		
		List<NoticeVO> noList = new ArrayList<>();
		
		try {
			noList = smc.queryForList("notice.search", nv);

		}catch (SQLException ex) {
			ex.printStackTrace();
		} 
		return noList;
	}
	@Override
	public NoticeVO getNotice(String boardWriter) {
		   NoticeVO nv = new NoticeVO();   
			
			try {
				   nv = (NoticeVO)smc.queryForObject("notice.getNotice",boardWriter);
			        
			    
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return nv;
	}


}
