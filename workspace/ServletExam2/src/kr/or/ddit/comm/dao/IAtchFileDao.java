package kr.or.ddit.comm.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.comm.vo.AtchFileVO;

public interface IAtchFileDao {
   /**
    * 
    * @param atchFileVO
    * @return
    * @throws SQLException
    */
	public int insertAtchFile(AtchFileVO atchFileVO)
	throws SQLException;
	
	/**
	 * 첨부파일 세부정보 저장
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public int insertAtchFileDetail(AtchFileVO atchFileVO)
			throws SQLException;
	/**
	 * 첨부파일 목록 조회
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO)
			throws SQLException;
	
	/**
	 * 첨부파일 세부정보 조회 메서드
	 * @param atchFileVO 가져올 첨부파일 정보(아이디 및 순번)
	 * @return 해당 정보로 조회한 첨부파일 세부정보
	 * @throws SQLException
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO)
			throws SQLException;
	
}
