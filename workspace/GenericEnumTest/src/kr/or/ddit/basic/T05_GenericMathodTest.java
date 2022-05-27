package kr.or.ddit.basic;

class Util2 {
	//제너릭 메소드- T를 extends Number하여 넘버의 자식멤버만 사용 가능(제한) - doubleValue, integer 등 
	public static <T extends Number> int compare(T t1,T t2) {
		
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}
/*
 * 제한된 타입 파라미터(Bounded Parameter) 예제
 */

public class T05_GenericMathodTest {
	public static void main(String[] args) {
		int result1 = Util2.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);
		System.out.println(result2);
	}

}
