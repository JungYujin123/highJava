package Notice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
import kr.or.ddit.util.JDBCUtil2;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
		== 게시판  ==
		1. 새글 작성			---> insert
		2. 게시글 삭제			---> delete
		3. 게시글 수정			---> update
		4. 전체 목록 출력	        ---> select
		5. 게시글 검색                       ---> search
		6. 작업 끝.
	----------------------
	 
	   
게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal
*/
public class Noticeinfo {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 게시판 ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 게시글 삭제");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 게시글 검색");		
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 새글 작성
			       insert();
					break;
				case 2 :  // 게시글 삭제
				     delete();
					break;
				case 3 :  // 게시글 수정
				  update();
					break;
				case 4 :  // 전체 목룍 출력
			       display();
			       break;
				case 5 :  // 게시글 겁색
				    search();
				    break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	//5.게시글 검색 메소드
	private void search() {
		System.out.println(); 
		System.out.println("검색할 작성자를 입력하세요."); 
		System.out.println("작성자>>  ");
		
		String boardWriter = scan.next();
		
		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("글번호\t제목\t작성자\t작성 날짜\t작성 내용");
		System.out.println("------------------------------------");
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "select * from JDBC_BOARD "
				    	 + " where BOARD_WRITER = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardWriter);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String boardNo = rs.getString("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				      boardWriter = rs.getString("BOARD_WRITER");
				String boardDate = rs.getString("BOARD_DATE");
				String boardContent = rs.getString("BOARD_CONTENT");
				
				System.out.println(boardNo +"\t" + boardTitle +"\t"+ boardWriter +"\t" + boardDate +"\t" +boardContent);
			}
			System.out.println("-------------------------------------------");
			System.out.println("출력 작업 끝...");
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		
		
		
	}

	// 2. 게시글 삭제 메서드
	private void delete() {
		System.out.println(); 
		System.out.println("삭제할 작성자 정보를 입력하세요."); 
		System.out.println("작성자>>  ");
		
		String boardWriter = scan.next();
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "  delete from JDBC_BOARD "
				    	 + " where BOARD_WRITER = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardWriter);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(boardWriter + "님 게시글을 삭제했습니다.");
			}else {
				System.out.println(boardWriter + "님 게시글 삭제 실패!!");
			}
			
		}catch (SQLException ex) {
			System.out.println(boardWriter + "님 게시글 삭제 실패!!");
			ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnection(conn, stmt, pstmt, rs);
		}
	}

	// 3. 게시글 수정 메서드
	private void update() {
	
		boolean chk = false;
		
		String boardWriter = "";
		
		do {
			System.out.println(); 
			System.out.println("수정할 작성자 정보를 입력하세요."); 
			System.out.println("작성자>>  ");
			
			boardWriter = scan.next();
			
			chk = checkWriter(boardWriter);
			
			if(chk == false) {
				System.out.println("작성자가" + boardWriter + "인 게시글은 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		 } while(chk == false);
		System.out.println("작성자>>  ");
		 String memName = scan.next();
		 
			System.out.println("게시글 제목>>  ");
			 String boardTitle = scan.next();
			 
			 
			 scan.nextLine(); //입력 버퍼 비우기
				System.out.println("게시글 내용>>  ");
				 String boardContent = scan.nextLine();
				 
				 try {
					 conn = JDBCUtil2.getConnection();
					 
					 String sql = "  UPDATE JDBC_BOARD "
							     +  "  set BOARD_TITLE =? "
								 + "  ,BOARD_CONTENT =? "
								+ "  where BOARD_WRITER = ? ";
					 
					 pstmt = conn.prepareStatement(sql);
					 pstmt.setString(1, boardTitle);
					 pstmt.setString(2, boardContent);
					 pstmt.setString(3, boardWriter);	
					 
					int cnt = pstmt.executeUpdate();
					
					if(cnt >0) {
						System.out.println(boardWriter + "님 게시글을 수정했습니다.");
					}else {
						System.out.println(boardWriter + "님 게시글 수정 실패!!");
					}
					
				 }catch (SQLException ex) {
						System.out.println(boardWriter + "님 게시글 수정 실패!!");
					ex.printStackTrace();
				}finally {
					JDBCUtil2.disConnection(conn, stmt, pstmt, rs);
				}
		
	}

	// 4. 전체 목록 출력  메소드
	private void display() {
		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("글번호\t제목\t작성자\t작성 날짜\t작성 내용");
		System.out.println("------------------------------------");
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "select * from JDBC_BOARD";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String boardNo = rs.getString("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardWriter = rs.getString("BOARD_WRITER");
				String boardDate = rs.getString("BOARD_DATE");
				String boardContent = rs.getString("BOARD_CONTENT");
				
				System.out.println(boardNo +"\t" + boardTitle +"\t"+ boardWriter +"\t" + boardDate +"\t" +boardContent);
			}
			System.out.println("-------------------------------------------");
			System.out.println("출력 작업 끝...");
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}


	 // 1. 새글 입력 메서드
	private void insert() {
		

		System.out.println("추가할 게시글 정보를 입력하세요."); 
		System.out.println("게시글 제목>>  ");
		 String boardTitle = scan.next();
		 
			System.out.println("게시글 작성자>>  ");
			 String boardWriter = scan.next();
						 
			 scan.nextLine(); //입력 버퍼 비우기
				System.out.println("게시글 내용>>  ");
				 String boardContent = scan.nextLine();
				 
				 try {
					 conn = JDBCUtil2.getConnection();
					 
					 String sql = "INSERT INTO JDBC_BOARD "
							 + " (BOARD_NO,BOARD_TITLE,BOARD_WRITER,BOARD_DATE,BOARD_CONTENT) "
							+ " VALUES (board_seq.nextVal, ?,?,sysdate,?) ";
					 
					pstmt = conn.prepareStatement(sql);	
					pstmt.setString(1, boardTitle);	
					pstmt.setString(2, boardWriter);
					pstmt.setString(3, boardContent);	
					
					int cnt = pstmt.executeUpdate();
					
					if(cnt>0) {
						System.out.println(boardWriter + "님 게시글 작성 성공!");
					}else {
						System.out.println(boardWriter + "님 게시글 작성 실패!!");
					}
						
				 } catch (SQLException ex) {
					 System.out.println(boardWriter + "님 게시글 작성 실패!!");
		             ex.printStackTrace();
				}finally {
					JDBCUtil2.disConnection(conn, stmt, pstmt, rs);
				}
	}
   /**
     * 회원 ID를 이용하여 회원이 존재하는 지 알려주는 메서드
     * @param memId 회원아이디
     * @return 존재여부
     */
	private boolean checkWriter(String boardWriter) {
	         boolean isExist = false;
	         
	         try {
	        	 conn = JDBCUtil2.getConnection();
	        	 
	        	 String sql = " SELECT COUNT(*) as cnt from JDBC_BOARD "
	        		      	+ " where BOARD_WRITER = ? ";
	        	 
	        	 pstmt = conn.prepareStatement(sql);
	        	 pstmt.setString(1, boardWriter);
	        	 
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

	public static void main(String[] args) {
		Noticeinfo NoObj = new Noticeinfo();
		NoObj.start();
	}

}






