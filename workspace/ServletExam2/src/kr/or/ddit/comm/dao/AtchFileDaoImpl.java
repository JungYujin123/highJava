package kr.or.ddit.comm.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class AtchFileDaoImpl implements IAtchFileDao {
	
	private static IAtchFileDao fileDao;
	private SqlMapClient smc;
	
	private AtchFileDaoImpl() {
     smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAtchFileDao getInstance() {
		if(fileDao == null) {
			fileDao = new AtchFileDaoImpl();
		}
		return fileDao;
	}

	@Override
	public int insertAtchFile(AtchFileVO atchFileVO) throws SQLException {
        int cnt = 0; 
        
        Object obj = smc.insert("atchFile.insertAtchFile", atchFileVO);
        
       if(obj ==null) {
        	cnt=1;
       }
		return cnt;
	  }

	@Override
	public int insertAtchFileDetail(AtchFileVO atchFileVO) throws SQLException {
		   int cnt = 0; 
	        
	        Object obj = smc.insert("atchFile.insertAtchFileDetail", atchFileVO);
	        
	       if(obj ==null) {
	        	cnt=1;
	       }
			return cnt;
		  }

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) throws SQLException {

		  List<AtchFileVO> atchFileList = smc.queryForList("atchFile.getAtchFileList", atchFileVO);
		
		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) throws SQLException {
         AtchFileVO fileVO = (AtchFileVO)smc.queryForObject("atchFile.getAtchFileDetail",atchFileVO);
		return fileVO;
	}

}
