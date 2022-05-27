package kr.or.ddit.basic;

public class T03_LambdaTest {
  static int stVar = 0;
  private String name= "aaa";
  
  public void testMethod(final int temp) {
	  final int localVar = 50;
	  int kor = 100;
	  
	  /*
	   * 람다식 내부에서 사용되는 지역변수는 모두 final이어야 한다.
	   * 보통은 final을 붙이지 않으면 컴파일러가 자동으로 붙여준다.
	   * 단, 지역변수의 값을 변경하는 식이 있을 경우에는 자동으로
	   * final을 붙여주지 않는다.
	   */
	  //temp = 500;
	  //localVal=2000;
	  kor=400;
	  
	  //람다식에서 지역변수 사용하기
	  LambdaTestInf1 lam1 =
			  ()->{
				  //파라미터값
				  System.out.println("temp = " + temp);
				  //지역변수
				  System.out.println("localVar = " + localVar);
				  //지역변수
				 //System.out.println("kor = " + kor); final 자동으로 붙어 변경했기 때문에 오류
				  //정적변수
				  System.out.println("stVar = " + stVar);
				  //멤버변수, 인스턴스변수
				  System.out.println("name = " + this.name);
			  };
			 lam1.test();
  }
  public static void main(String[] args) {
	new T03_LambdaTest().testMethod(200);
}
	
}
  

