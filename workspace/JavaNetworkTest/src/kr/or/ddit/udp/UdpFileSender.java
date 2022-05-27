package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//선생님한테 사진파일 보내기
//리시버에서 콘솔 준비
public class UdpFileSender {
   public static void main(String[] args) throws InterruptedException {
	String receiveIp = "192.168.44.128";
	int port = 8888;
	
	File file = new File("d:/D_Other/aaa.jpg");
	if(!file.exists()) {
		System.out.println("파일이 존재하지 않습니다.");
	     System.exit(0);
	}
	
	DatagramSocket ds = null;
	
	long fileSize = file.length();
	long totalReadBytes = 0;
	
	long startTime = System.currentTimeMillis();
	
	try {
		ds = new DatagramSocket();
		InetAddress receiveAddr = InetAddress.getByName(receiveIp);
		
		String str = "start"; //전송 시작을 알려주기 위한 문자열1
		
		DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, receiveAddr, port);
		ds.send(dp);
		
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[1000];
		
		//파일명 전송2
		str = file.getName();
		dp= new DatagramPacket(str.getBytes(), str.getBytes().length, receiveAddr, port);
		ds.send(dp);
		
		//총 파일 크기 정보를 전송3
		str = String.valueOf(fileSize);
		dp= new DatagramPacket(str.getBytes(), str.getBytes().length, receiveAddr, port);
		ds.send(dp);
		
		while(true) {
			//패킷정송간의 시간 간격을 주기 위해 (무한루프 돌며 데이터 보내기)
			Thread.sleep(10);
			
			int readBytes = fis.read(buffer, 0, buffer.length);
			if(readBytes==-1) {
				break;
			}
			dp=new DatagramPacket(buffer, readBytes, receiveAddr, port);
			ds.send(dp);
			
			totalReadBytes += readBytes;
			System.out.println("진행상태: " + totalReadBytes + "/" +fileSize + "Bytes(" + (totalReadBytes*100/fileSize)+"%)");
		}
		long endTime = System.currentTimeMillis();
		long diffTime = endTime - startTime;
		double transferSpeed = fileSize / diffTime;
		
		System.out.println("걸린시간 : " + diffTime + "(ms)");
		System.out.println("평균 전송속도: " +transferSpeed + "Byte/ms");
		System.out.println("전송완료...");
		
		fis.close();
		ds.close();
		
	}catch (IOException ex) {
         ex.printStackTrace();
	}
}
}
