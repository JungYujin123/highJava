package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao {

	// 1. 자기자신 class의 참조변수를 멤버변수로 선언한다.
	 // (이 변수는 private static으로 지정한다.)
	private static IMemberDao memDao;
	
	private SqlMapClient smc;
	
	// 2. 생성자를 private으로 한다.
	private MemberDaoImpl() {	
		smc = SqlMapClientFactory.getInstance();
	}
	// 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
    //(이 메서드의 이름은 보통  getInstance()로 지정한다. static으로 지정함.)
	public static IMemberDao getInstance() {
		
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	
	

	@Override
	public int insertMember(MemberVO mv) {

           
		  int cnt = 0;
		
		  try {
			cnt = smc.update("member.insertMember",mv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

	    boolean isExist = false;
        
     
	    try {
			int cnt = (int)smc.queryForObject("member.checkMember", memId);
	
	        if(cnt>0) {
	        	isExist=true;
	        }
	    
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isExist;
	}

	@Override
	public int updateMember(MemberVO mv) {

		int cnt =0;
		
		 try {
			cnt = smc.update("member.updateMember", mv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;
		
		try {
			cnt = smc.delete("member.deleteMember", memId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {

		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			memList = smc.queryForList("member.getMemberAll");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			memList = smc.queryForList("member.searchMember", mv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memList;
	}
	
	@Override
	public MemberVO getMember(String memId) {
		   MemberVO mv = new MemberVO();   
		
		try {
			   mv = (MemberVO)smc.queryForObject("member.getMember",memId);
		        
		    
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return mv;
		}

	}
	

	

