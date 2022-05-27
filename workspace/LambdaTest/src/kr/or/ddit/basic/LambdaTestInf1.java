package kr.or.ddit.basic;


// 함수적 인터페이스 =>추상메서드가 1개뿐인 인터페이스

 @FunctionalInterface 
 public interface LambdaTestInf1 {
	//반환갑이 없고 매개변수도 없는 추상 메서드 선언
	public void test();
	/*public void test2(); 추상메서드 2개 안됨*/
 }
  interface LambdaTestInf2 {
   //반환갑이 없고 매개변수는 있는 추상 메서드 선언
	public void test(int a);		
  }
   interface LambdaTestInf3 {
	//반환갑이 있고 매개변수도 있는 추상 메서드 선언
	public int test(int a, int b);
}
