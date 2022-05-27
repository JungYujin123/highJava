package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 성능향상을 위한 보조 스트림 예제
 * (바이트 기반의 Buffered 스트림)
 */
public class T11_BufferedIOTest {
 public static void main(String[] args) {
	
	 FileOutputStream fos = null; 
	 BufferedOutputStream bos = null; //보조 스트림
	  
	 try {
		 //fos = new FileOutputStream ("d:/D_Other/bufferTest.txt");
		 fos = new FileOutputStream ("d:/D_Other/bufferTest02.txt", true);
		 //버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192byte(8KB)로 설정된다.
		 //true 추가하면  fos가 옆에 추가됨, 또는 bos자리에  fos로 변경하면 추가가 아닌 덮어쓰기 가능
		 
		 //버퍼크기가 5인 스트림 생성
		 bos = new BufferedOutputStream(fos, 5);
		 //fos = new BufferedOutputStream(fos, 5);로 덮어쓰기
		 
		 for (int i = '1'; i <= '9'; i++) {
			//숫자 자체를 문자로 저장하기 위해 '사용함
			 //for문 9번 돌지만 5까지 차면 write 호출하며 다시 6번째부터 9까지 데이터 쌓는데 5을 못채워 남아만 있기때문에 flush 호출
			 bos.write(i);
		}
		 bos.flush(); //작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.(close시 자동으로 호출됨)
     	//fos.flush();로 덮어쓰기
		 
	   System.out.println("작업 끝...");
	 }catch (IOException ex) {
		ex.printStackTrace();
	}finally {
		try {
			bos.close(); //보조스트림 close하면 기반 스트림은 자동으로 같이 꺼지기 떄문에 변경 안해도 됨
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
}
