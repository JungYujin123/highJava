package kr.or.ddit.reflection;

/*
 * Class오브젝트(오브젝트 내부의 세부정보를 담고있는)를 생성하기
 */
public class T01_ClassObjectCreationExam {

/*
 *  Java Reflection에 대하여...
 *  
 *  1. 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드, 생서아에 대한 정보를 가져오거나 수정할 수 있고, 
 *  새로운 객체를 생성하거나 메서드를 실행할 수 있다.
 *  2. Reflection API는 java.lang.reflect 패키지와 java.lang.class를 통해 제공된다.
 *  3. java.lang.clss의 주요 메서드
 *  -getName(),getSuperClass(),getInterfaces(),getModifiers()등.
 *  4. java.lang.reflect 패키지의 주요 클래스
 *  -Field, Method, Constructor, Modifier 등.
 *  5. Rdflection API를 이용하면 클래스의  private메서드나 변수에 접근가능
 *  (보안 위협)
 *  6.실행시 약간의 오버헤드가 발생하므로 가능하면 꼭 필요한 경우에만 사용해야 한다.	
 */
	
	public static void main(String[] args) throws ClassNotFoundException {
		//첫번째방법: Class.forName()메서드 이용
	 Class<?> klass = Class.forName("kr.or.ddit.reflection.T01_ClassObjectCreationExam.class");
	
	  //두번째방법 : getClass()메서드 이용
	 T01_ClassObjectCreationExam obj = new T01_ClassObjectCreationExam();
	 klass = obj.getClass();
	 
	 //세번째 방법: .class이용
	 klass =T01_ClassObjectCreationExam.class;
	}
}
