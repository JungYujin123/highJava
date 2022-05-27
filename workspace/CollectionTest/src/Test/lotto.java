package Test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class lotto {

	 public static void main(String[] args) {
	      Scanner scanner = new Scanner(System.in);

	      while(true) {
	    	  
	      System.out.println("--------------------------------------");
	      System.out.println("1.Lotto 구입");
	      System.out.println("2.프로그램 종료");
	      System.out.println("======================================");

	      System.out.print("메뉴선택 : ");
	      int menu = scanner.nextInt();

	      switch (menu) {
	      case 1:
	         System.out.println("Lotto 구입 시작");
	         System.out.println();
	         System.out.println("1000원에 로또번호 하나입니다.");
	         System.out.print("금액 입력 :  ");
	         int cash = scanner.nextInt();
	         int count = cash/1000;
	         System.out.println();
	         System.out.println("행운의 로또번호는 아래와 같습니다.");	         
	         for (int i = 0; i <count; i++) {      
	         Set<Integer> lottoNum = new HashSet<>();	           
	         while (lottoNum.size() < 6) {
	            int num = (int) (Math.random() * 45 + 1);
	            lottoNum.add(num);
	         }

	         System.out.println("로또번호 : " + lottoNum);
	         }
	         System.out.println("받음금액은 " + cash + "원이고 거스름돈은 "+ cash%1000  +"원입니다.");
             break;
	      case 2:
	    	  System.out.println("감사합니다.");
	    	  
	    	  System.exit(0);
	      }

	   }
	 }
}
