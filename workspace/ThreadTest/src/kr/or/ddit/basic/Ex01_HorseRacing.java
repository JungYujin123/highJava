package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class Ex01_HorseRacing {

	public static List<horse> list = new ArrayList<>();

	public static void main(String[] args) {
		horse[] hs = new horse[] {
				new horse("01번말"),
				new horse("02번말"),
				new horse("03번말"),
				new horse("04번말"),
				new horse("05번말"),
				new horse("06번말"),
				new horse("07번말"),
				new horse("08번말"),
				new horse("09번말"),
				new horse("10번말"),
		};
		
		for (horse h: hs) {
			h.start();
		}
		for (horse h : hs) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("경기 끝....");
		System.out.println("----------------------");
		System.out.println();
		System.out.println("경기 결과");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setRank(i+1);
			System.out.println(list.get(i).getRank() + "위 : " + list.get(i).getHname());
		}
	}
}

class horse extends Thread {
	String hname;
	String hstate = ">";
	int rank;

	public horse(String hname) {
		this.hname = hname;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			hstate += '-';
		}
		
		System.out.println(hname + " : " + hstate);
		for (int i = 1; i <= 49 ; i++) {
			hstate = horserun(hstate);
			System.out.println(hname +" : "+ hstate);
			try {
				Thread.sleep((int)Math.random()* 5000 + 200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(hname +" : -"+ hstate.substring(0, 49)+ " >");
		System.out.println(hname + "레이스종료....");
		Ex01_HorseRacing.list.add(this);
	}
	
	// 왼쪽에 "-"을 붙인 뒤 0~50까지 잘라 한칸 이동한것 처럼 표현하는 메소드
	public String horserun(String state) {
		String tmp = "-" + state;
		String tmp2 = tmp.substring(0, 50);
		return tmp2;
	}
	
	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

	public String getHstate() {
		return hstate;
	}

	public void setHstate(String hstate) {
		this.hstate = hstate;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}
