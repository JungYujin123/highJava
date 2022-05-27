package Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


/*
      호텔 운영을 관리하는 프로그램 작성.(Map이용)
 - 키값은 방번호 
 
(단, 종료시 파일로 저장하고 프로그램 실행시 파일로부터 데이터를 불러올 수 있도록 처리한다.)
 */

public class hotelObj {

	private Scanner scan;
	
	//hotelMap 선언
	private HashMap<String, hotelreservation> hotelMap;

	
	public hotelObj() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
    
	
	
   //메뉴 선택 메소드 
	public void displayMenu() {
		System.out.println();
		System.out.println();
		System.out.println("*******************************");
		System.out.println("        어떤 업무를 하시겠습니까?       ");
		System.out.println(" 1.체크인 2.체크아웃 3.객실상태 4.업무종료    ");
		System.out.println("*******************************");
		System.out.print("메뉴선택 =>");
		
	}
	
	//시작 메소드
	public void serviceStart() {

		System.out.println("*******************************");
		System.out.println("          호텔 문을 열었습니다.       ");
		System.out.println("*******************************");
		
		     // 저장된 객체를 읽어와 출력(역직렬화)
				ObjectInputStream ois = null;
				

				Object obj = null;
				try {
					ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/hotelObjyj.bin")));

					while ((obj = ois.readObject()) != null) {
						
						//캐스팅해서 obj을 HashMap으로  형변환
						hotelMap = (HashMap<String, hotelreservation>) obj;
		
					}

					if (ois != null) {
						ois.close();
					}
					
				}catch(IOException ex) {
				//ex.printStackTrace();
				} catch (ClassNotFoundException ex) {
				//ex.printStackTrace();
				}
		
				
         // 메뉴 선택 스위치 구문
		menu: while (true) {

			displayMenu(); // 메뉴 출력

			int menuNum = scan.nextInt(); // 메뉴 번호 입력

			switch (menuNum) {
			case 1:
				checkin();
				break;
			case 2:
				checkout();
				break;
			case 3:
				roomCondition();
				break;
			case 4:
				closed();
				break menu;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			}
		}
				
				

	}
	
   //4.업무 종료 메소드
	private void closed() {
		System.out.println("*******************************");
		System.out.println("         호텔 문들 닫았습니다.        ");
		System.out.println("*******************************");

	
		// 객체를 파일에 저장(직렬화)
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/D_Other/hotelObjyj.bin")));
			
			oos.writeObject(hotelMap);
			
			
			oos.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	
	}
   //3. 객실 상태 메소드
	private void roomCondition() {
		Set<String> keySet = hotelMap.keySet();

		if (keySet.size() == 0) {
			System.out.println("체크인된 객실이 없습니다.");

		} else {
			Iterator<String> it = keySet.iterator();

			int cnt = 0;
			while (it.hasNext()) {
				cnt++;
				String roomNum = it.next();
				hotelreservation r = hotelMap.get(roomNum);
				System.out.println("방번호: " + r.getRoomNum() + "," + "투숙객: " + r.getName());

			}
		}
	}

	 //2. 체크아웃 메소드
	private void checkout() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 =>");
		String roomNum = scan.next();

		if (hotelMap.remove(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
			return;
		} else {
			System.out.println("체크아웃 되었습니다.");
		}
	}
    
	//1. 체크인 메소드
	private void checkin() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 =>");
		String roomNum = scan.next();

		if (hotelMap.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		}

		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next();

		hotelMap.put(roomNum, new hotelreservation(name, roomNum));

		System.out.println("체크인 되었습니다.");
		System.out.println();
		System.out.println("************************************");
	}

	 //메인 메소드
	public static void main(String[] args) {
	
		new hotelObj().serviceStart();

		

	}
}
// Serializable 구현한  예약 리스트 메소드
class hotelreservation implements Serializable {
	private String name;
	private String roomNum;

	public hotelreservation(String name, String roomNum) {
		this.name = name;
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	@Override
	public String toString() {
		return "reservation [name=" + name + ", roomNum=" + roomNum + "]";
	}

}
