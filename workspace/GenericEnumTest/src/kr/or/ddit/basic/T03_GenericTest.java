package kr.or.ddit.basic;

public class T03_GenericTest {
	/*
	 * 제너릭 클래스 클래스를 만드는 방법
	 * 
	 * 형식) class 클래스명<제너릭타입글자> {
	 * 
	 * 제너릭타입글자 변수명; // 변수선언에 제너릭을 사용하는 경우
	 *     ...
	 * 
	 * 제너릭타입글자 메서드명(){ 
	 * //반환값이 있는 메서드에서 사용 
	 *     ... 
	 *     return 값; 
	 *    } 
	 *    ... 
	 *  } 
	 *  -- 제너릭타입글자 -- 
	 *  T=>Type 
	 *  K=> Key 
	 *  V=> Value 
	 *  E=> Element(자료구조에 들어가는 항목들을 나타낼때 사용)
	 */

	
	
	public static void main(String[] args) {
		
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		String rtnNg1 = (String) ng1.getVal(); //제너릭이 아니기 때문 캐스팅해줘야함
		System.out.println("문자열 반환값 rtnNg1 => "+ rtnNg1);
		
		Integer irtNg2 = (Integer) ng2.getVal();
		System.out.println("정수 반환값 irtnNg2 => "+ irtNg2);
		System.out.println();
		
		MyGeneric<String> mg1 = new MyGeneric<>(); //제너릭은 캐스팅 안해줘도 됨
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		 
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnNg1 = mg1.getVal();
		irtNg2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : " + rtnNg1);
		System.out.println("제너릭 정수형 반환값 : " + irtNg2);
	}
}



class NonGenericClass {
	private Object val;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}

}

//Object이 아닐 시 타입별 클래스 만들어주어야 함
//class NonGenericClass {
//	private String val;
//
//	public String getVal() {
//		return val;
//	}
//
//	public void setVal(String val) {
//		this.val = val;
//	}
//
//}
//
//class NonGenericClass {
//	private Integer val;
//
//	public Integer getVal() {
//		return val;
//	}
//
//	public void setVal(Integer val) {
//		this.val = val;
//	}
//
//}

class MyGeneric<T> {
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}

}
