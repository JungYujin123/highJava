package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil2;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	

	@Override
	public int insertMember(MemberVO mv) {

           
		  int cnt = 0;
		
		 try {
			 conn = JDBCUtil2.getConnection();
			 
			 String sql = "INSERT INTO mymember"
					 + " (mem_id,mem_name,mem_tel,mem_addr)"
					+ " VALUES (?,?,?,?)" ;
			 
			 pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());	
			pstmt.setString(2, mv.getMemName());	
			pstmt.setString(3, mv.getMemTel());	
			pstmt.setString(4, mv.getMemAddr());	
			
			cnt = pstmt.executeUpdate();
			
				
		 } catch (SQLException ex) {			
            ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnection(conn, stmt, pstmt, rs);
		}
		 
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

	    boolean isExist = false;
        
        try {
       	 conn = JDBCUtil2.getConnection();
       	 
       	 String sql = " SELECT COUNT(*) as cnt from MYMEMBER "
       		      	+ " where MEM_ID = ? ";
       	 
       	 pstmt = conn.prepareStatement(sql);
       	 pstmt.setString(1, memId);
       	 
       	 rs = pstmt.executeQuery();
       	 
       	 int cnt = 0;
       	 if(rs.next()) {
       		 cnt = rs.getInt("cnt");
       	 }
       	 
       	 if(cnt > 0) {
       		 isExist = true;
       	 }
       	 
        }catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnection(conn, stmt, pstmt, rs);
		}
		
		return isExist;
	}

	@Override
	public int updateMember(MemberVO mv) {

		int cnt =0;
		
		try {
			 conn = JDBCUtil2.getConnection();
			 
			 String sql = "  UPDATE MYMEMBER "
					     +  "  set MEM_NAME =? "
					   	+ "  ,MEM_TEL =? "
						 + "  ,MEM_ADDR =? "
						+ "  where MEM_ID = ? ";
			 
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, mv.getMemName());
			 pstmt.setString(2, mv.getMemTel());
			 pstmt.setString(3, mv.getMemAddr());
			 pstmt.setString(4, mv.getMemId());
			 
			cnt = pstmt.executeUpdate();	
			
		 }catch (SQLException ex) {		 
			ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnection(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "  delete from mymember "
				    	 + " where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
	
		}catch (SQLException ex) {		
			ex.printStackTrace();
		}finally {
			JDBCUtil3.disConnection(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {

		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				MemberVO mv = new MemberVO();
				mv.setMemId(memId);
				mv.setMemName(memName);
				mv.setMemTel(memTel);
				mv.setMemAddr(memAddr);
				
				memList.add(mv);
			}
		
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			
			 conn= JDBCUtil3.getConnection();

			String sql = "select * from mymember where 1=1 ";
			if(mv.getMemId()!=null && !mv.getMemId().equals("")) {
				sql += " and mem_id = ? ";
			}
			if(mv.getMemName()!=null && !mv.getMemName().equals("")) {
				sql += " and mem_name = ? ";
			}
			if(mv.getMemTel()!=null && !mv.getMemTel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if(mv.getMemAddr()!=null && !mv.getMemAddr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%'";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1; 
			
			if(mv.getMemId()!=null && !mv.getMemId().equals("")) {
				pstmt.setString(index++,mv.getMemId());
			}
			if(mv.getMemName()!=null && !mv.getMemName().equals("")) {
				pstmt.setString(index++,mv.getMemName());
			}
			if(mv.getMemTel()!=null && !mv.getMemTel().equals("")) {
				pstmt.setString(index++,mv.getMemTel());
			}
			if(mv.getMemAddr()!=null && !mv.getMemAddr().equals("")) {
				pstmt.setString(index++,mv.getMemId());
			}
			
			 rs = pstmt.executeQuery();
		
				while(rs.next()) {
					String memId = rs.getString("mem_id");
					String memName = rs.getString("mem_name");
					String memTel = rs.getString("mem_tel");
					String memAddr = rs.getString("mem_addr");
					
					MemberVO mv2 = new MemberVO();
					mv2.setMemId(memId);
					mv2.setMemName(memName);
					mv2.setMemTel(memTel);
					mv2.setMemAddr(memAddr);
					
					memList.add(mv2);
				}
				
		}catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.disConnection(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}

	
}
