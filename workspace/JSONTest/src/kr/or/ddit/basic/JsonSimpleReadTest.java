package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonSimpleReadTest {
public static void main(String[] args) throws IOException, ParseException {
	FileReader fr = new FileReader("./myJsonFile.txt");
	JSONParser parser = new JSONParser();
	
	Object obj = parser.parse(fr);
	
	JSONObject jsonData = (JSONObject) obj;
	
	String name = (String) jsonData.get("name");
	String job = (String) jsonData.get("job");
	long age = (long) jsonData.get("age");
	String addr = (String) jsonData.get("addr");
	
	System.out.println("name =" + name);
	System.out.println("job =" + job);
	System.out.println("age =" + age);
	System.out.println("addr =" + addr);
	
	JSONArray memList = (JSONArray) jsonData.get("memList");
	System.out.println("--------------------------------------");
	
	Iterator<JSONObject> it = memList.iterator();
	
	while(it.hasNext()) {
		JSONObject mv = it.next();
		System.out.printf("이름 : %s, \t직업:  %s,\t나이: %d, \t주소  : %s\n "
				,mv.get("name"),mv.get("job"),mv.get("age"),mv.get("addr"));
	}
}
}
