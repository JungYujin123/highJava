package kr.or.ddit.member.main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
import kr.or.ddit.member.service.INoticeService;
import kr.or.ddit.member.service.NoticeServiceImpl;
import kr.or.ddit.member.vo.NoticeVO;
import kr.or.ddit.util.JDBCUtil2;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	        ---> serch
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class NoticeMain {
	
	// 사용할 서비스객체를 담기위한 변수를 선언한다.
	private INoticeService noService;
	
	public NoticeMain() {
		noService = new NoticeServiceImpl();
	}
	
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
				case 4 :  // 전체 목록 출력
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
	
	/**
	 * 회원정보를 검색하는 메서드
	 */
	private void search() {
      /*
       * 검색할 회원ID, 회원이름, 전화번호, 주소 등을 입력하면 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
       * 주소는 입력한 값이 포함만 되어도 검색되도록 한다. 
       * 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다. 
       */
		  scan.nextLine(); //입력버퍼 비우기
		  System.out.println();
		  System.out.println("검색할 정보를 입력하세요.");
		  System.out.println("게시글 번호>> ");
		  String boardNo = scan.nextLine().trim();
		  
		  System.out.println("게시글 제목>> ");
		  String boardTitle = scan.nextLine().trim();
		  
		  System.out.println("작성자>> ");
		  String boardWriter = scan.nextLine().trim();
		  
		  System.out.println("게시 날짜>> ");
		  String boardDate = scan.nextLine().trim();
		  
		  System.out.println("게시글 내용>> ");
		  String boardContent = scan.nextLine().trim();
		  
		  NoticeVO nv = new NoticeVO();
		  nv.setBoardNo(boardNo);
		  nv.setBoardTitle(boardTitle);
		  nv.setBoardWriter(boardWriter);
		  nv.setBoardDate(boardDate);
		  nv.setBoardContent(boardContent);
		  
		  
		  List<NoticeVO> noList = 
		  noService.search(nv);
		
		   System.out.println();
			System.out.println("------------------------------------");
			System.out.println("글번호\t제목\t작성자\t작성 날짜\t작성 내용");
			System.out.println("------------------------------------");
				
				if(noList.size()==0) {
					System.out.println("검색할 게시글이 없습니다.");
				}else {
					for(NoticeVO nv2 : noList) {
						System.out.println(boardNo +"\t" + boardTitle +"\t"+ boardWriter +"\t" + boardDate +"\t" +boardContent);
				}
				}
					
					System.out.println("-------------------------------------------");
					System.out.println("검색 작업 끝...");
	}

	// 2. 회원정보를 삭제하는 메서드
	private void delete() {
		System.out.println(); 
		System.out.println("삭제할 작성자 정보를 입력하세요."); 
		System.out.println("작성자>>  ");
		
		String boardWriter = scan.next();
		
		int cnt = noService.delete(boardWriter);
		
		if(cnt > 0) {
			System.out.println(boardWriter + "님 게시글을 삭제했습니다.");
		}else {
			System.out.println(boardWriter + "님 게시글 삭제 실패!!");
		}
	
	}

	// 3. 회원정보를 수정하는 메서드
	private void update() {
	boolean chk = false;
		
	String boardWriter = "";
	
	do {
		System.out.println(); 
		System.out.println("수정할 작성자 정보를 입력하세요."); 
		System.out.print("작성자>>  ");
		
		boardWriter = scan.next();
		
		chk = check(boardWriter);
		
		if(chk == true) {
			System.out.println("작성자가" + boardWriter + "인 게시글은 존재하지 않습니다.");
			System.out.println("다시 입력해 주세요.");
		}
		
	 } while(chk == true);
		System.out.println("게시글 제목>>  ");
		 String boardTitle = scan.next();
		 
		 
		 scan.nextLine(); //입력 버퍼 비우기
			System.out.println("게시글 내용>>  ");
			 String boardContent = scan.nextLine();
				 
				 NoticeVO nv = new NoticeVO();
				 nv.setBoardWriter(boardWriter);
				 nv.setBoardTitle(boardTitle);
				 nv.setBoardContent(boardContent);
				
			 
				 int cnt = noService.update(nv);
				
					if(cnt >0) {
						System.out.println(boardWriter + "님 게시글을 수정했습니다.");
					}else {
						System.out.println(boardWriter + "님 게시글 수정 실패!!");
					}
		
	}

	// 4. 전체 목록 출력 메소드
	private void display() {
		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("글번호\t제목\t작성자\t작성 날짜\t작성 내용");
		System.out.println("------------------------------------");
		
		
     	List<NoticeVO> noList = noService.getAllList();
		
		if(noList.size()==0) {
			System.out.println("출력할 게시글이 없습니다.");
		}else {
			for(NoticeVO nv : noList) {
			System.out.println(nv.getBoardNo() +"\t" + nv.getBoardTitle()+"\t"+ nv.getBoardWriter() +"\t" + nv.getBoardDate() +"\t" + nv.getBoardContent());
	    
		}
		}
			
			System.out.println("-------------------------------------------");
			System.out.println("출력 작업 끝...");
		
		
	}


	 // 1. 회원정보를 추가하기 위한 메서드
	 
	private void insert() {
		
		boolean chk = false;
		
		String boardWriter = "";
		
		do {
			System.out.println(); 
			System.out.println("추가할 작성자 정보를 입력하세요."); 
			System.out.print("작성자>>  ");
			
			boardWriter = scan.next();
			
			chk = check(boardWriter);
			
			if(chk == true) {
				System.out.println("작성자가" + boardWriter + "인 회원은 이미 존재합니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		 } while(chk == true);
		System.out.print("게시글 제목>>  ");
		 String boardTitle = scan.next();
		 
		 scan.nextLine(); //입력 버퍼 비우기
			System.out.print("게시글 내용>>  ");
			 String boardContent = scan.nextLine();
			 	 
				 NoticeVO nv = new NoticeVO();
				 nv.setBoardWriter(boardWriter);
				 nv.setBoardTitle(boardTitle);
				 nv.setBoardContent(boardContent);
				 
                 
                 int cnt = noService.insert(nv);
                 
					if(cnt>0) {
						System.out.println(boardWriter + "게시글 추가 작업 성공!");
					}else {
						System.out.println(boardWriter + "게시글 추가 작업 실패!!");
					}
				
	}
	/**
	 * 회원 ID를 이용하여 회원이 존재하는 지 알려주는 메서드
	 * @param memId 회원아이디
	 * @return 존재여부
	 */
    private boolean check(String boardWriter) {
		// TODO Auto-generated method stub
    	boolean isExist = false;
    	
    	isExist = noService.check(boardWriter);
        
        
		return isExist;
	}



	public static void main(String[] args) {
		NoticeMain noObj = new NoticeMain();
	     noObj.start();
	}

}






