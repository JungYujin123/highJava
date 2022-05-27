package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class T06_FileStreamTest {
public static void main(String[] args) {
	
	//파일에 출력하기
	
	FileOutputStream fos = null;
	
	try {
		//출력용 OutPutStream 객체 생성
		fos = new FileOutputStream("d:/D_Other/out.txt");
		// 파일 새로 생성됨과 동시에 내용도 출력되어 있음
		
		for(char ch='a'; ch<='z'; ch++) {
			fos.write(ch);
		}
		System.err.println("파일에 쓰기 작업 완료...");
		
	}catch (IOException ex) {
		ex.printStackTrace();
	}finally {
		try {
		fos.close();
	}catch (IOException ex) {
		ex.printStackTrace();
	}
	}
}
}
