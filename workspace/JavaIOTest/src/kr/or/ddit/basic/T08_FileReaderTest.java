package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

public class T08_FileReaderTest {
 public static void main(String[] args) {
	
	 //FileReader(문자기반 스트림)을 이용한 파일 내용 읽기
	 FileReader fr = null;
	 
	 try {
		 fr = new FileReader("d:/D_Other/testChar.txt");
		 
		 int c;
		 
		 while((c=fr.read()) !=-1) {
			 System.out.print((char)c);
		 }
		 fr.close();
		 
	 }catch (IOException ex) {
		ex.printStackTrace();
	}
}
}
