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
public class DOMParsingExam {
   public void parse(){
      try {
         // DOM Document 객체 생성하기 위한 메서드
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = dbf.newDocumentBuilder();

         //http://api.odcloud.kr/api/15096653/v1/uddi:2139c182-b749-49d3-909b-df42a9ad26d3?page=1&perPage=10&returnType=XML&serviceKey=e%2F1bKpWr%2FeV0vH9px08ZCX8pH%2F1kur7Oq16kmYYcu%2BSces7JdfdLRsmU%2F%2F4FFnGFInbcWrRAzLktqtTyQc0xyA%3D%3D
         // uri정보를 넣음
         //http://api.odcloud.kr/api/15096653/v1/uddi:2139c182-b749-49d3-909b-df42a9ad26d3?page=1&perPage=10&returnType=XML&serviceKey=e%2F1bKpWr%2FeV0vH9px08ZCX8pH%2F1kur7Oq16kmYYcu%2BSces7JdfdLRsmU%2F%2F4FFnGFInbcWrRAzLktqtTyQc0xyA%3D%3D
         String serviceKey = "e%2F1bKpWr%2FeV0vH9px08ZCX8pH%2F1kur7Oq16kmYYcu%2BSces7JdfdLRsmU%2F%2F4FFnGFInbcWrRAzLktqtTyQc0xyA%3D%3D";  // 레시피 재료 정보 조회 서비스
         String page = "1";     // 레시피 재료 시작 순번
         String perPage = "2";      // 레시피 재료 종료 순번
         String returnType = "XML";   // 래시피가 궁금한 음식 ID

         URL url = new URL("http://api.odcloud.kr/api/15096653/v1/uddi:2139c182-b749-49d3-909b-df42a9ad26d3?"
        		  + "page=" + page + "&" +"perPage="+ perPage + "&" +"returnType=" + returnType + "&serviceKey="+serviceKey
               );
         
         System.out.println(url);
         
         // DOM  파서로부터 입력받은 파일을 파싱하도록 요청
         Document xmlDoc = builder.parse(url.toString());
         
         //루트 엘리먼트 객체 가져오기
         Element root = xmlDoc.getDocumentElement();
         System.out.println("루트 엘리먼트 태그명 : "+ root.getTagName());
         
         //전체 레시피 수를 가져오기
         perPage = root.getElementsByTagName("totalCount").item(0).getTextContent();
         
         url = new URL("http://api.odcloud.kr/api/15096653/v1/uddi:2139c182-b749-49d3-909b-df42a9ad26d3?"
       		  + "page=" + page + "&" +"perPage="+ perPage + "&" +"returnType=" + returnType + "&serviceKey="+serviceKey
              );
         
         xmlDoc = builder.parse(url.toString());
         
         root = xmlDoc.getDocumentElement();
         
         //하위 엘리먼트 접근하기
         

         NodeList rowNodeList = root.getElementsByTagName("item");
       
         String code = "INFO-000";
         
         if(code.equals("INFO-000")) { //정상이면...
        	 for(int i=0; i<rowNodeList.getLength(); i++) {
        		 Element rowEl = (Element)rowNodeList.item(i);
        		 
        		 
        		 String rowNum = 
        				 rowEl.getElementsByTagName("col").item(0).getTextContent();
        		 String irdntTyNm = 
        				 rowEl.getElementsByTagName("col").item(1).getTextContent();
        		 String irdntNm = 
        				 rowEl.getElementsByTagName("col").item(2).getTextContent();
        		 String irdntCpcty = 
        				 rowEl.getElementsByTagName("col").item(3).getTextContent();
        		 
        		
        		 String formatStr = String.format("%3s %10s %10s %8s", rowNum, irdntTyNm, irdntNm, irdntCpcty);
        	 System.out.println(formatStr);
        	 System.out.println("---------------------------------------------");
        	 
        	 
        	 }
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   public static void main(String[] args) {
      new DOMParsingExam().parse();
   }
}
