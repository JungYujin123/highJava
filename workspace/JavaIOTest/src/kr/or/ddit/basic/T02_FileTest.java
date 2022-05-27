package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02_FileTest {

	public static void main(String[] args) {
		
		// 파일, 디렉토리 존재 여부, 파일 생성
		
		File f1 = new File("d:/D_Other/레오.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		
		File f4 = new File("d:/D_Other/연습용"); // 실제 있는 "폴더"
		File f5 = new File("d:/D_Other/댕댕.txt"); //실제 없는 파일
		
		System.out.println(f4.isDirectory());
		System.out.println(f4.isFile());
		System.out.println(f5.exists());
		
		
		if(f1.exists()) {
			System.out.println(f1.getAbsolutePath() +"은 존재합나다.");
		}else {
			System.out.println(f1.getAbsolutePath()+"은 없는 파일입니다. ");
			
			try{
				if(f1.createNewFile()) {
					System.out.println(f1.getAbsolutePath()+"파일을 새로 만들었습니다. ");
				}
				
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if(f2.exists()) {
			System.out.println(f2.getAbsolutePath() +"은 존재합나다.");
		}else {
			System.out.println(f2.getAbsolutePath() +"은 없는 파일입니다. ");
		}
		
		System.out.println("--------------------------------------------------");
		
		//파일,디렉토리 리스트, for문  사용하여 분류
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles();
		for(File f : files) {
			System.out.print(f.getName() + "=>");
			if(f.isFile()) {
				System.out.println("파일");
			} else  if(f.isDirectory()) {
				System.out.println(""
						+ "디렉토리");
			}
		
		}
		System.out.println("=================================");
	
		//파일,디렉토리 리스트, for문 사용하여 정렬
		String[] strFiles = f3.list();
		for(String strFile : strFiles) {
			System.out.println(strFile);
		}
		
		
		System.out.println("---------------------------------");
		System.out.println();
		
		displayFileLis(new File("d:/D_Other"));
	}


	/* 지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
     	dir 목록조회할 디렉터리
	*/
	public static void displayFileLis(File dir) {
		  System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
	     
		  // 디렉토리 안의 모든 파일목록을 가져온다.
	      File[] files = dir.listFiles();
	      
	      // 하위 디렉토리 정보를 저장할 ArrayList 생성(File배열이ㅡ 인덱스 정보 저장)
	      List<Integer> subDirList = new ArrayList<Integer>();
	      
	      // 날짜를 보여주기 위한 형식 설정
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
	      
	      for(int i = 0; i<files.length; i++) {
	         String attr = ""; // 파일의 속성(읽기, 쓰기, 히든, 디렉토리 구분)
	         String size = ""; // 파일용량
	         
	         if(files[i].isDirectory()) {
	            attr = "<DIR>";
	            subDirList.add(i); //인덱스값을 List에 추가
	         } else {
	        	 size = files[i].length() + "";
	             attr = files[i].canRead() ? "R" : "";
	             attr += files[i].canWrite() ? "W" : "";
	             attr += files[i].isHidden() ? "H" : "";
	         }
	         
	         System.out.printf("%s %-5s %12s %s\n", sdf.format(new Date(files[i].lastModified())), attr, size, files[i].getName());
	      }
	      
	      int dirCount = subDirList.size(); // 폴더 안의 하위폴더 개수 구하기
	      int fileCount = files.length - dirCount; // 폴더안의 파일 개수
	      
	      System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리");
	      System.out.println();
	      
	      for (int i = 0; i < subDirList.size(); i++) {
			//하위 폴더의 내용들도 출력하기 위해 현재 메서드를 재귀호출하여 처리한다.
	    	 displayFileLis(files[subDirList.get(i)]);
		}
	}
}
