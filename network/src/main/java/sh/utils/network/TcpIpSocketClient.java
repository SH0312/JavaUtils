package sh.utils.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpIpSocketClient {
	private static Logger logger = LoggerFactory.getLogger(TcpIpSocketClient.class);

	// TODO 다중 접속 처리
	public static void main(String[] args) {
        Socket socket = new Socket();
        Scanner sc = new Scanner(System.in);
 
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
 
        OutputStream os = null;
        OutputStreamWriter osw = null;
        PrintWriter pw = null;
 
        try {
        	// 클라이언트 소켓 생성
            socket.connect(new InetSocketAddress("127.0.0.1", 9090));
            logger.info("[client] connected with server");
 
            while (true) {
 
                is = socket.getInputStream();
                isr = new InputStreamReader(is, "UTF-8");
                br = new BufferedReader(isr);
 
                os = socket.getOutputStream();
                osw = new OutputStreamWriter(os, "UTF-8");
                pw = new PrintWriter(osw, true);
 
                System.out.print(">>");
                String data = sc.nextLine();
 
                if ("exit".equals(data))
                    break;
 
                //client -> server
                pw.println(data);
                
                //server -> client
                data = br.readLine();
                logger.info("<< " + data);
 
            }
 
        } catch (IOException e) {
        	logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
            	logger.error(e.getMessage());
            }
            sc.close();
 
        }
	}
}
