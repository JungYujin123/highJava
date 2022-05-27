package kr.or.ddit.basic;

import com.sun.swing.internal.plaf.metal.resources.metal;

public class T03_ThreaTest {

	public static void main(String[] args) {
	//스레드의 수행시간 체크해보기
	Thread th = new Thread(new MyRunner());
	
	//UTC(Universe Time Coordinated 협정 시계 표준시)를 사용하여 1970년 1월1일 0시0분0초를 기준으로
	//경과한 시간을 밀리세컨드(1/1000초)단위로 나타낸다. => 유닉스 타임스탬프
	long startTime =System.currentTimeMillis();
	
	th.start();
	
	try {
		th.join(); //현재 실행중인 스레드에서 작업중인 스레드(지금은 th스레드)가 종료될 때까지 기다린다.
	} catch (InterruptedException ex) {
		ex.printStackTrace();
	}
	long endTime = System.currentTimeMillis();
	System.out.println("경과 시간:" + (endTime - startTime));
}
}
class MyRunner implements Runnable{

	@Override
	public void run() {
	long sum= 0;
	for (long i = 1L; i <1000000000; i++) {
		sum+= i;
	}
	System.out.println("합계: "+sum);
		
	}
	
	
}
