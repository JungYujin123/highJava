package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
  public static void main(String[] args) {
	
	  System.out.println("static변수값: " + PrintAnnotation.id);
	  
	 // Retention기능을 이용한 메서드 실행하기
	  // 선언된 메서드 목록 가져오기
	  Method[] declaredMethods = Service.class.getDeclaredMethods();
	  
	  for(Method m : declaredMethods) {
		  System.out.println(m.getName());//메서드명 출력
		  
		  Annotation[] annos = m.getDeclaredAnnotations();
		  
		  for(Annotation ano : annos) {
			  if(ano.annotationType().getSimpleName().equals("PrintAnnotation")) {
				  PrintAnnotation printAnn = (PrintAnnotation)ano;
				  
				  for(int i=0; i<printAnn.count(); i++) {
					  //count 값 만큼 value값 출력하기
					  System.out.print(printAnn.value());
				  }
			  }
		  }
		  System.out.println(); //줄바꿈 처리
		  
		  //메서드 실행하기
		  Class<?> clazz = Service.class;
	     try {
	    	 //객체 생성하기
	    	 Service service = (Service) clazz.newInstance();
	    	 
	    	 //메서드 실행하기
	    	 m.invoke(service);
	    	 
	     }catch (InstantiationException ex) {
			ex.printStackTrace();       
	     }catch (IllegalAccessException e) {
			 e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}}
}
}
