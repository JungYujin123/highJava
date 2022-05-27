package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class list {

	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();

		list.add(new Student("202102", "홍길동", 50, 60, 70));
		list.add(new Student("202103", "성춘향", 70, 80, 90));
		list.add(new Student("202101", "이순신", 100, 90, 80));
		list.add(new Student("202104", "감강찬", 90, 80, 60));

		System.out.println("정렬 전:");
		for (Student st : list) {
			System.out.println(st);
		}
		System.out.println("-----------------------");

		Collections.sort(list);
		System.out.println("학번의 오름차순으로 정렬 후 : ");
		for (Student st : list) {
			System.out.println(st);
		}
		System.out.println("-----------------------");

		Collections.sort(list, new SortNumDesc());
		System.out.println("총점의 내림차순으로 정렬 후:");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setRank(i + 1);
			System.out.println(list.get(i));
		}
			
		System.out.println("-----------------------");

	}

}

// 총점의 내림차순
class SortNumDesc implements Comparator<Student> {

	@Override
	public int compare(Student st1, Student st2) {
		return new Integer(st1.getSumScore()).compareTo(st2.getSumScore()) * -1;
	}

}

class Student implements Comparable<Student> {

	private String id;
	private String name;
	private int koreaScore;
	private int englishScore;
	private int mathScore;
	private int sumScore;
	private int rank;

	public Student(String id, String name, int koreaScore, int englishScore, int mathScore) {
		super();
		this.id = id;
		this.name = name;
		this.koreaScore = koreaScore;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.sumScore = koreaScore + englishScore + mathScore;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKoreaScore() {
		return koreaScore;
	}

	public void setKoreaScore(int koreaScore) {
		this.koreaScore = koreaScore;
	}

	public int getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(int englishScore) {
		this.englishScore = englishScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getSumScore() {
		return sumScore;
	}

	public void setSumScore(int sumScore) {
		this.sumScore = koreaScore + englishScore + mathScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", koreaScore=" + koreaScore + ", englishScore=" + englishScore
				+ ", mathScore=" + mathScore + ", sumScore=" + sumScore + ", rank=" + rank + "]";
	}

	// 학번 오름차순
	@Override
	public int compareTo(Student st) {
		return getId().compareTo(st.getId());
	}

}
