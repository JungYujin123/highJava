package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketServer {
   public static void main(String[] args) throws IOException{
	//TCP 소켓 통신을 하기위해 ServerSoket 생성
	ServerSocket server =  new ServerSocket(7777);
	System.out.println("서버가 접속을 기다립니다...");
	
	//accept()메서드는 Client에서 연결 요청이 올때까지 계속 기다린다.
	//연결 요청이 오면 Soket객체를 생성해서 Client의 Soket과 연결한다. 
	Socket socket = server.accept();
	
	//------------------------------------------------
	//그 이후는 클라이언트와 연결된 후의 작업을 진행하면 된다. 
	System.out.println("접속한 클라이언트 정보");
	System.out.println("주소 : " +socket.getInetAddress());

	//Client에 메세지 보내기
	
	//Outputstream객체를 통하여 전송한다.
	//접속한 Soket의 getOutputstram()메서드를 이요한다.
	OutputStream out = socket.getOutputStream();
	
	DataOutputStream dos = new DataOutputStream(out);
	dos.writeUTF("어서오세요...");//메세지 보내기
	System.out.println("메세지를  보냈습니다. ");
	
   dos.close();
   
   server.close(); //서버소켓닫기
}
}
