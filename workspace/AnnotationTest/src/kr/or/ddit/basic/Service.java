package kr.or.ddit.basic;


public class Service {
	
	@PrintAnnotation	
 public void method1() {
	 System.out.println("메서드1에서 출력되었습니다.");
 }
	@PrintAnnotation("%") //'value='생략 가능
 public void method2() {
	 System.out.println("메서드2에서 출력되었습니다.");
 }
	@PrintAnnotation(value="#", count=25) //두개일때는 'value=' 생략 불가
 public void method3() {
	 System.out.println("메서드3에서 출력되었습니다.");
 }
}
