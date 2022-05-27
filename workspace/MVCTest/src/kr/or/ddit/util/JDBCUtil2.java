package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;

/* db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 방법1) properties객체 이용하기
 */
public class JDBCUtil2 {

	  static Properties prop;   // Properties객체 변수 선언
	   
	   // static블록은 클래스 인스턴스가 만들어질 때 한번만 실행됨
	   static {
	      
	      prop = new Properties();
	      
	      try {
	         FileInputStream fis =  new FileInputStream("res/db.properties");
	         prop.load(fis);
	         fis.close();
	         
	         Class.forName(prop.getProperty("driver"));
	         System.out.println("드라이버 로딩 완료~!");
	      } catch (IOException | ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	   }
	   
	   
	   public static Connection getConnection() {
	      
	      try {
	         return DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"), prop.getProperty("pass"));
	      } catch (SQLException e) {
	         e.printStackTrace();
	         return null;
	      }
	   }
	   
	   public static void disConnection(
				Connection conn,
				Statement stmt,
				PreparedStatement pstmt,
				ResultSet rs) {
			if(rs != null) try {rs.close();}catch (SQLException ex) {}
			if(pstmt != null) try {pstmt.close();}catch (SQLException ex) {}
			if(stmt != null) try {stmt.close();}catch (SQLException ex) {}
			if(conn != null) try {conn.close();}catch (SQLException ex) {}
				
			
		}
	   
	}