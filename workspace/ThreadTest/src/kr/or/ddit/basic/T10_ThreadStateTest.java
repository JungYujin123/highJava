package kr.or.ddit.basic;

import com.sun.swing.internal.plaf.metal.resources.metal;

public class T10_ThreadStateTest {

	/*
	 * <스레드의 상태>
	 * 
	 * NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
	 * RUNNABLE : 실행 중 또는 실행 가능한 상태 
	 * BLOCKED : 동기화불럭에 의해서 일시정지된 상태(LOCK이 풀릴때까지 기다리는 상태)
	 * WAITING, TIMED_WAITING : 스레드의 작업이 종료되지는 않았지만 실행 가능하지는 않은 일시정지 상태. TIMED_WAITING은 일시정지 시간이 지정된 경우임.
	 * TERMINATED : 스레드의 작업이 종료된 상태
	 */

	public static void main(String[] args) {
		StatePrintTread spt = new StatePrintTread(new TargetThread());
		spt.start();
	}
}

//스레드의 상태를 출력하는 스레드 클래스
class StatePrintTread extends Thread {
	private Thread targetThread; //상태를 모니터링할 대상 스레드
	
	public StatePrintTread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			//스레드의 상태값 구하기
			Thread.State state = targetThread.getState(); // 스레드 상태를 구현해주는 식
			System.out.println("대상 스레드의 상태값 : "+ state);
			
			//NEW상태인지 검사
			if(state ==Thread.State.NEW) {
				targetThread.start();
			}
		     //타겟 스레드가 종료 상태인지 검사
			if(state == Thread.State.TERMINATED) {
				break;
			}
			try {
				Thread.sleep(500);
			}catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			}
		}
		
	}


//모니터링할 대상 스레드
class TargetThread extends Thread {
	@Override
	public void run() {
		for (int i = 1; i < 1000000000L; i++) {}//시간 지연용
		try {
			Thread.sleep(1500);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		for (int i = 1; i < 1000000000L; i++) {}//시간 지연용
	}
}