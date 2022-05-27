package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TcpFileClient {
   // 클라이언트는 서버에 접속하여 서버가 보내주는파일을
   // D드라이브의 C_Lib폴더에 저장한다.
   private Socket socket;
   private InputStream in;
   private FileOutputStream fos;
   
   public void clientStart() {
      try {
         socket = new Socket("192.168.44.2", 7777);
         System.out.println("파일 다운로드 시작...");
         
         // 저장할 때 사용할 스트림 객체 생성
         fos = new FileOutputStream("d:/C_Lib/aaa.jpg");
         
         in = socket.getInputStream();
         
         BufferedInputStream bis = new BufferedInputStream(in);
         BufferedOutputStream bos = new BufferedOutputStream(fos);
         
         int c = 0;
         while((c = bis.read()) != -1) {
            bos.write(c);
         }
         
         bis.close();
         bos.close();
         
         System.out.println("파일 다운로드 완료!!!");
      } catch (IOException ex) {
         ex.printStackTrace();
      } finally {
         if(in != null) {
            try {in.close();} catch (IOException ex) {}
         }
         if(fos != null) {
            try {fos.close();} catch (IOException ex) {}
         }
         if(socket != null) {
            try {socket.close();} catch (IOException ex) {}
         }
      }
   }
   
   public static void main(String[] args) {
      new TcpFileClient().clientStart();
   }

}