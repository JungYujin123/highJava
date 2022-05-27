package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {

	 private DatagramSocket socket;
	 public void start() throws IOException{
		 //포트 8888번을 사용하여 수신하는 소켓을 생성한다.
		 socket = new DatagramSocket(8888);
		 
		 //패킷 송수신을 위한 객체변수 선언
		 DatagramPacket inPacket, outPacket;
		 
		 byte[] msg; //패킷 송수신을 위한 바이트배열 선언
		 
		 while(true) {
			 //데이터를 수신하기 위한 패킷을 생성한다.
			 msg = new byte[1];
			 inPacket = new DatagramPacket(msg, msg.length);
			 
			 System.out.println("패킷 수신 대기중...");
			 
			 //패킷을 통해 데디터를 수신(receive)한다.
			 socket.receive(inPacket);
			 
			 System.out.println("패킷 수신 완료...");
			 
			 //수신한 패킷으로 부터 client의 IP주소와 Port번호를 얻어온다.
			 InetAddress address = inPacket.getAddress();
			 int port = inPacket.getPort();
			 
			 //서버의 현재 시간을 시분초 형태([hh:mm:ss])로 반환한다.
			 SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			 
			 //현재 시간을 포맷 문자열로 가져오기
			 String time = sdf.format(new Date());
			 
			 msg=time.getBytes();//문자열을 byte배열로 반환
			 
			 //패킷을 생성해서 client에게 전송(send)한다.(바이트배열, 바이트배열 길이, 아이피주소, 포트번호)
			 outPacket = new DatagramPacket(msg, msg.length, address, port);
			 
			 socket.send(outPacket);//전송시작
		 }
	 }
	 public static void main(String[] args) throws IOException {
		new UdpServer().start();
	}
	
}
