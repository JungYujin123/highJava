package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16_NoneSerializablrParentTest {

/*
 * 부모 클래스가 Serializable 인터페이스를 구현하고 있지 않은 경우 부모객체의 필드값 처리 방법
 * 1. 부모 클래스가 Serializable인터페이스를 구현하도록 해야 한다.
 * 2. 자식 클래스에 writeObject()와 readObject()메서드를 이용하여 부모객체의 필드값을 처리할 수 있도록 직접 구현한다.
 */
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		   // 출력
	      FileOutputStream fos = new FileOutputStream("d:/D_Other/nonSerializableTest.bin");
	      ObjectOutputStream oos = new ObjectOutputStream(fos); // 보조이기떄문에 기반 필요(=fos)

	      Child child = new Child();

		child.setParentName("부모");
		child.setChildName("자식");
		oos.writeObject(child); //직렬화
		oos.flush(); //생략 가능
		oos.close();
		fos.close(); //생략 가능
		
		
		  FileInputStream fis = new FileInputStream("d:/D_Other/nonSerializableTest.bin");
	      ObjectInputStream ois = new ObjectInputStream(fis); // 보조이기때문에 기반 필요(=fis)
	      Child child2 = (Child) ois.readObject(); // 역직렬화 (ClassNotFoundException 던짐)

	      System.out.println("parentName : " + child2.getParentName());
	      System.out.println("childName : " + child2.getChildName());

	      ois.close();
	      oos.close();
	   }

	}

	class Parent {
	   // 부모 클래스에 Serializable 인터페이스를 구현하면 자식 클래스들도 강제로  Serializable 구현클래스가 되기 때문에
	   // 좋은 방법이 아님

	   private String parentName;
	   // 직렬화 안되어있어서 그냥 출력하면 transient 처리된 것처럼 null로 나옴

	   public String getParentName() {
	      return parentName;
	   }

	   public void setParentName(String parentName) {
	      this.parentName = parentName;
	   }

	}

	class Child extends Parent implements Serializable {

	   private String childName;

	   public String getChildName() {
	      return childName;
	   }

	   public void setChildName(String childName) {
	      this.childName = childName;
	   }
	   
	   private void writeObject(ObjectOutputStream out) throws IOException {
	      out.writeUTF(getParentName());   //부모필드값 수동 처리 (get으로 담겨 write 후 out 처리)
	      out.defaultWriteObject();      //원래의 writeObject기능 수행
	   }
	   
	   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	      setParentName(in.readUTF());   //부모필드값 수동 처리 (in으로 담아 read읽고  set분출 처리) 
	      in.defaultReadObject();         //원래의 readObject기능 수행
	   }


	
	
}