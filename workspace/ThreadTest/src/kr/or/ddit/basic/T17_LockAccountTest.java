package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * 은행의 입출금을 스레드로 처리하는 예제
 * (lock객체를 이용한 동기화 처리)
 *
 */
public class T17_LockAccountTest {

	/*
	 *  락(Lock) 기능을 제공하는 클래스
	 *  
	 *  ReentrantLock : Read 및  Write 구분없이 사용하기 위한 락 클래스로 동기화 처리를 위해 사용된다.
	 *                  (Synchronized를 이용한 동기화 처리보다  부가적인 기능이 제공됨.)
         RentrantReadWriteLock : Read 및 Write 락을 구분하여 사용 가능함.
         */
	
	public static void main(String[] args) {
	   ReentrantLock lock = new ReentrantLock(true); //락 객체 생성
	   
	   LockAccount lAcc = new LockAccount(lock); //공유객체 생성
	   
	   lAcc.deposit(10000);//입금처리

	   
	   BankThread2 th1 = new BankThread2(lAcc);
	   BankThread2 th2 = new BankThread2(lAcc);
	   
	   th1.start();
	   th2.start();
	}
	 
	 
}
//입출금을 담당하는 클래스
class LockAccount{
	private int balance; //잔액이 저장될 변수
	
	//Lock객체 생성 => 되도록이면 private final로 만든다.
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}

	public int getBalance() {
		return balance;
	}

	//입금하는 메서드
	public void deposit(int money) {
		//Lock객체의  lock()메서드가 동기화의 시작이고, unlock()메서드가 동기화의 끝을 나타낸다.
		//lock() 메서드로 동기화를 설정한 곳에서는 반드시 unlock()메서드로 해제를 해주어야 한다.
		lock.lock();//시작(락을 획득하기 전까지 BLOCKED 됨)
		balance += money; //동기화 처리 부분
		lock.unlock(); //해제
		
	}
	
	//츨금하는 메서드(출금 성공:true  출금 실해: false 반환)
	public boolean withdraw(int money) {
		lock.lock(); //락 획득

          boolean chk= false;
          
          //try~catch 블럭을 사용할 경우에는
          //unlock()메서드 호출을  finally 블럭에서 하도록 한다.
          try {
        	  if(balance>=money) {
        		  for (int i = 1; i < 1000000000; i++) {}
				   balance -= money;
				   System.out.println("메서드 안에서 balabnce = "+getBalance());
				   chk = true;
				
        	  }
          } catch (Exception ex) {
			chk = false;
		}finally {
			lock.unlock();//예외사항에는 락 반납
		}
	 return chk;
}
}

//은행업무를  처리하는 스레드
class BankThread2 extends Thread{
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println("스레드 안에서 result = "+ result + ", balance = " +lAcc.getBalance());
	}
}