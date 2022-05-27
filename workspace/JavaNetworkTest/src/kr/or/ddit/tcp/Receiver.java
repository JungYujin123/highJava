package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 이 클래스는 소켓에서 메세지를 받아 화면에 출력받는 역할을 담당한다. 
 * @author PC-23
 *
 */
public class Receiver extends Thread{
    private DataInputStream dis;
    
    public Receiver(Socket socket) {
    	try {
    		dis = new DataInputStream(
    				socket.getInputStream());
    	}catch (IOException ex) {
			ex.printStackTrace();
		}
    }

	@Override
	public void run() {
		while(dis !=null)
			try {
			System.out.println(dis.readUTF());
			}catch (IOException ex) {
				ex.printStackTrace();
			}
	}
    
}
