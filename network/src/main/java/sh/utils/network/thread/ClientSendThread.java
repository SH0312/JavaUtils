package sh.utils.network.thread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sh.utils.network.TcpIpSocketClient;


//메세지 보내는 쓰레드
public class ClientSendThread extends Thread  {
	private Logger logger = LoggerFactory.getLogger(ClientSendThread.class);

	public void run() {
        Scanner sc = new Scanner(System.in);
 
        OutputStream os = null;
        OutputStreamWriter osw = null;
        PrintWriter pw = null;
 
        try {
            while (true) {
                String data = sc.nextLine();
            	
                os = TcpIpSocketClient.socket.getOutputStream();
                osw = new OutputStreamWriter(os, "UTF-8");
                pw = new PrintWriter(osw, true);

 
                if ("exit".equals(data))
                    break;
                pw.println(data);
            }
 
        } catch (IOException e) {
        	logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (TcpIpSocketClient.socket != null && !TcpIpSocketClient.socket.isClosed()) {
                	TcpIpSocketClient.socket.close();
                }
            } catch (IOException e) {
            	logger.error(e.getMessage());
            }
            sc.close();
 
        }
	}
}
