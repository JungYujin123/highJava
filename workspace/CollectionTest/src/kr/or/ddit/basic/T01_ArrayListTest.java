package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class T01_ArrayListTest {
	public static void main(String[] args) {

		// DEFAULT_CAPACITY = 10;
		List list1 = new ArrayList();
		//인터페이스 자료 기반 따라서
		//List list1 = new LinkedList();도 사용가능

		// add()메서드를 사용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k');
		list1.add(true);
		list1.add(12.34);

		// size() => 데이터 개수
		System.out.println("size => " + list1.size());
		System.out.println("list1 => " + list1);

		// get으로 데이터 꺼내오기
		System.out.println("1번째자료: " + list1.get(1));

		// 데이터 끼워넣기도 같다.
		list1.add(0, "zzz");
		System.out.println("list1 => " + list1);

		// 데이터 변경하기(set메서드)
		String temp = (String) list1.set(0, "YYY");
		System.out.println("temp =>" + temp);
		System.out.println("list1 => " + list1);

		// 삭제하기
		list1.remove(0); //인덱스 가능
		System.out.println("삭제 후 : " + list1);

		list1.remove("bbb");
		list1.remove(12.34);
		list1.remove(new Integer(111));
		//list1.remove(111); 값도 가능하나 인트만 오류, 인덱스값이랑 구별 어렵기 때문
		System.out.println("bbb 삭제 후: " + list1);
		System.out.println("=================================================");

		// 제네릭을 지정하여 선언할 수 있다.
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");

		
		//인덱스가 필요시에는 향상된 for문 대신
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
			System.out.println("------------------------------------------");

			for (String s : list2) {
				System.out.println(s);
			}
			System.out.println("---------------------------------------");

			// contains(비교객체); => 리스트에 '비교객체'가 있으면 true 없으면 false
			System.out.println(list2.contains("DDD"));
			System.out.println(list2.contains("ZZZ"));

			// indexOf(비교객체) => 리스트에서 '비교객체'를 찾아 '비교객체'가 있는 index값을 반환한다. 없으면 -1 반환함.
			System.out.println("DDD의 index값: " + list2.indexOf("DDD"));
			System.out.println("ZZZ의 index값: " + list2.indexOf("ZZZ"));

			// toArray() => 리스트 안의 데이터들을 배열로 변환하여 반환한다.
			// 기본적으로 object형 배열로 반환한다.
			Object[] strArr = list2.toArray();
			System.out.println("배열의 갯수 : " + strArr.length);
			System.out.println("---------------------------------------");

		}
	}
}
