package sh.utils.network.thread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sh.utils.network.TcpIpSocketServer;

//다중 접속을 위한 서버 쓰레드
public class ServerThread extends Thread {
	private Logger logger = LoggerFactory.getLogger(ServerThread.class);
	Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		OutputStreamWriter osw = null;
		String bf = null;
		PrintWriter pw = null;

		OutputStream senderOs = null;
		try {
			while (true) {
				is = socket.getInputStream();
				isr = new InputStreamReader(is, "UTF-8");
				br = new BufferedReader(isr);

				bf = br.readLine(); // Blocking
				
				//클라이언트로부터 수신된 메세지 확인시 로그 풀것 
//				logger.info("[server] recived : " + bf);
				
				if (bf == null) {
					// 정상종료 : remote socket close()
					// 메소드를 통해서 정상적으로 소켓을 닫은 경우
					logger.info("[server] closed by client");
					break;
				}
				//누가보냈는지 확인하기
				senderOs = socket.getOutputStream();
				int num = 0;
				for (int key : TcpIpSocketServer.ClientList.keySet()) {
					os = TcpIpSocketServer.ClientList.get(key).getOutputStream();
					if (os.equals(senderOs)) {
						num = key;
					}
				}
				//귓속말 기능
				if (bf.contains("<")) {
					int whisper = Integer.parseInt(bf.split("<")[0]);
					for (int key : TcpIpSocketServer.ClientList.keySet()) {
						if (whisper == key) {
							os = TcpIpSocketServer.ClientList.get(key).getOutputStream();
							osw = new OutputStreamWriter(os, "UTF-8");
							pw = new PrintWriter(osw, true);
							pw.println("["+num+" whisper]" + " - " + bf.split("<")[1]);
						}
					}
				}
				//전체 전송
				else {
					for (int key : TcpIpSocketServer.ClientList.keySet()) {
						os = TcpIpSocketServer.ClientList.get(key).getOutputStream();
						osw = new OutputStreamWriter(os, "UTF-8");
						pw = new PrintWriter(osw, true);
						pw.println(num + " - " + bf);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}