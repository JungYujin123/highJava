package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T15_ObjectStreamTest {
	public static void main(String[] args) {
	
	  //Member 인스턴스 생성
	  Member mem1 = new Member("홍길동", 20, "대전");
	  Member mem2 = new Member("일지매", 30, "경기");
	  Member mem3 = new Member("이몽룡", 40, "강원");
	  Member mem4 = new Member("성춘향", 20, "광주");
		
	  ObjectOutputStream oos = null;
	  
	  try {
		  
		  //객체를 파일에 저장하기
		  
		  //출력용 스트림 객체 생성
		  //성능 향상을 위해 BufferedOutputStream 사용, 없어도 실행 가능
		  oos = new ObjectOutputStream
				  (new BufferedOutputStream(new FileOutputStream
						  ("d:/D_Other/memObj.bin")));
		  
		  //쓰기 작업
		  oos.writeObject(mem1); //직렬화
		  oos.writeObject(mem2); //직렬화
		  oos.writeObject(mem3); //직렬화
		  oos.writeObject(mem4); //직렬화
		
		  System.out.println("쓰기 작업 완료...");
		  
		  oos.close();
	  }catch (IOException ex) {
		ex.printStackTrace();
	}finally {
		try {
		oos.close();
	}catch (IOException e) {
		e.printStackTrace();
	}
	}
	  ObjectInputStream ois = null;
	  
	  try {
		  //저장된 객체를 읽어와 출력하기
		  
		  //입력용 스트림 객체 생성
		 ois = new ObjectInputStream(
				 new BufferedInputStream(
						 new FileInputStream("d:/D_Other/memObj.bin")));
		  Object obj= null;
		  
		  while((obj = ois.readObject()) != null) {
			  //마지막에 다다르면 EOF(end of file) Exception 발생함. 파일 다 읽었다는 뜻
			  
			  //읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
			  Member mem = (Member) obj;
			  System.out.println("이름 : " + mem.getName());
			  System.out.println("나이 : " + mem.getAge());
			  System.out.println("주소 : " + mem.getAddr());
			  System.out.println("------------------------");
		  }
	  
		  
	  }catch (ClassNotFoundException | IOException  ex) {
		ex.printStackTrace();
		  System.out.println("출력 작업 끝...");
	}finally {
		try {
			ois.close();
		}	catch (IOException ex) {
			ex.printStackTrace();
		}
		}
  }
}

/**
 * 회원정보 VO
 */

class Member implements Serializable {
	// 자바는 Serializable인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있음,
	// 구현하지 않으면 java.io.NotSerializableException 발생함.

	/*
	 * transient => 직렬화가 되지 않을 멤버변수에 지정한다.(*[[
	 * f static 필드도 직렬화가 되지 않는다.) 
	 * 직렬화가 되지 않는 멤버변수는 기본값으로 저장된다. (참조형변수 : null, 숫자형 변수 :0)
	 */

	private transient String name;
	private int age;
	private String addr;

	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
