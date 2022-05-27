package kr.or.ddit.basic;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *  XML DOM을 이용한 XML 문서 파싱 예제(레시피 정보 조회 API)
 */
public class T03_DOMParsingExam {
   public void parse(){
      try {
         // DOM Document 객체 생성하기 위한 메서드
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = dbf.newDocumentBuilder();

         // uri정보를 넣음
         String svcKey = "Grid_20150827000000000227_1";  // 레시피 재료 정보 조회 서비스
         String apiKey = "1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c"; // 개인별 발급.
         String startIdx = "1";     // 레시피 재료 시작 순번
         String endIdx = "5";      // 레시피 재료 종료 순번
         String recipeId = "195428";   // 래시피가 궁금한 음식 ID

         URL url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
               + "/xml/"+ svcKey + "/"+startIdx +"/" + endIdx
               +"?RECIPE_ID=" +  recipeId);
         
         System.out.println(url);
         
         // DOM  파서로부터 입력받은 파일을 파싱하도록 요청
         Document xmlDoc = builder.parse(url.toString());
         
         //루트 엘리먼트 객체 가져오기
         Element root = xmlDoc.getDocumentElement();
         System.out.println("루트 엘리먼트 태그명 : "+ root.getTagName());
         
         //전체 레시피 수를 가져오기
         String totalCnt = root.getElementsByTagName("totalCnt").item(0).getTextContent();
         endIdx = totalCnt; //마지막 번호를 전체번호로 설정
         
         url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
                 + "/xml/"+ svcKey + "/"+startIdx +"/" + endIdx
                 +"?RECIPE_ID=" +  recipeId);
         
         xmlDoc = builder.parse(url.toString());
         
         root = xmlDoc.getDocumentElement();
         
         //하위 엘리먼트 접근하기
         NodeList rowNodeList = root.getElementsByTagName("row");
         
         String code = root.getElementsByTagName("code").item(0).getTextContent();
         
         if(code.equals("INFO-000")) { //정상이면...
        	 for(int i=0; i<rowNodeList.getLength(); i++) {
        		 Element rowEl = (Element)rowNodeList.item(i);
        		 
        		 
        		 String rowNum = 
        				 rowEl.getElementsByTagName("ROW_NUM").item(0).getTextContent();
        		 String irdntTyNm = 
        				 rowEl.getElementsByTagName("IRDNT_TY_NM").item(0).getTextContent();
        		 String irdntNm = 
        				 rowEl.getElementsByTagName("IRDNT_NM").item(0).getTextContent();
        		 String irdntCpcty = 
        				 rowEl.getElementsByTagName("IRDNT_CPCTY").item(0).getTextContent();
        		
        		 String formatStr = String.format("%3s %8s %10s %10s %8s", rowNum, recipeId, irdntTyNm, irdntNm, irdntCpcty);
        	 System.out.println(formatStr);
        	 System.out.println("---------------------------------------------");
        	 
        	 
        	 }
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   public static void main(String[] args) {
      new T03_DOMParsingExam().parse();
   }
}
