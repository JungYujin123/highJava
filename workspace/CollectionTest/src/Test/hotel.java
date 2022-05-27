package Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.sun.swing.internal.plaf.metal.resources.metal;

import kr.or.ddit.basic.T09_PhoneBookTest;

public class hotel {
	private Scanner scan;
	private Map<String, reservation> hotelMap;

	public hotel() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}

	public void displayMenu() {
		System.out.println();
		System.out.println();
		System.out.println("*******************************");
		System.out.println("        어떤 업무를 하시겠습니까?       ");
		System.out.println(" 1.체크인 2.체크아웃 3.객실상태 4.업무종료    ");
		System.out.println("*******************************");
		System.out.print("메뉴선택 =>");
		
	}
	
	
	public void serviceStart() {

		System.out.println("********************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("********************");
		

		menu:while (true) {
			
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
				close();
				break menu;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}

	private void close() {
		System.out.println("*******************");
		System.out.println("호텔 문들 닫았습니다.");
		System.out.println("*******************");

	}

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
				reservation r = hotelMap.get(roomNum);
				System.out.println("방번호: " + r.getRoomNum() + "," + "투숙객: " + r.getName());

			}
		}
	}

	private void checkout() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 =>");
		String roomNum = scan.next();

		if (hotelMap.remove(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
			return;
		} else{
		System.out.println("체크아웃 되었습니다.");
		}
	}
	


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

		hotelMap.put(roomNum, new reservation(name, roomNum));

		System.out.println("체크인 되었습니다.");
		System.out.println();
		System.out.println("************************************");
	}

	public static void main(String[] args) {

		new hotel().serviceStart();
	}
	
	
}

class reservation {
	private String name;
	private String roomNum;

	public reservation(String name, String roomNum) {
		super();
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
