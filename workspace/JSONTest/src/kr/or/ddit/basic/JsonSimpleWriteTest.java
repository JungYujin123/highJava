package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonSimpleWriteTest {

	/*
	 * JSON (Javascript Object Notation) 에 대하여...
	 * 
	 * 자바스크립트 오브젝트 표기방식을 이용한 문자열(텍스트)을 말함.
	 * 
	 * -JSON에서 value값으로 사용가능한 데이터 타입
	 * 1. string
	 * 2. number
	 * 3. object (JSON object)
	 * 4. array
	 * 5. boolean
	 * 6. null
	 *
	 */
	
	public static void main(String[] args) throws IOException {
		
		//JSON 데이터 생성하기
		JSONObject mv = new JSONObject();
		
		mv.put("name", "홍길동");
		mv.put("job", "학생");
		mv.put("age", 30);
		mv.put("addr", "대전시 중구 대흥동 대덕인재개발원");
	
        //JSonArray 데이터 생성
		JSONArray memList = new JSONArray();
	
		JSONObject mv2 = new JSONObject();
	
		mv2.put("name", "이순신");
		mv2.put("job", "자영업");
		mv2.put("age", 40);
		mv2.put("addr", "서울시 영등포구 여의도동");
		memList.add(mv2);
		
		mv2 = new JSONObject();
		
		mv2.put("name", "강감찬");
		mv2.put("job", "회사원");
		mv2.put("age", 20);
		mv2.put("addr", "부산시 해운대구");
		memList.add(mv2);
		
        mv2 = new JSONObject();
		
		mv2.put("name", "이몽룡");
		mv2.put("job", "무직");
		mv2.put("age", 30);
		mv2.put("addr", "대구시 북구");
		memList.add(mv2);
		
        mv2 = new JSONObject();
		
		mv2.put("name", "정도전");
		mv2.put("job", "농업");
		mv2.put("age", 60);
		mv2.put("addr", "인천시 부평구");
		memList.add(mv2);
		
		mv.put("memList", memList);
		
		// JSON 파일 생성, mv 안에 list를 담음
		
		FileWriter fw = new FileWriter("./myJsonFile.txt");
		fw.write(mv.toString());
		fw.flush();
		fw.close();
		
	
	}
}
