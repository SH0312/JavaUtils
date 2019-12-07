package sh.utils.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO 메시지가 개인에게만 전송중 모두에게 전송하게 만들기
// TODO 귓속말 기

public class TcpIpSocketServer {
	private static Logger logger = LoggerFactory.getLogger(TcpIpSocketServer.class);

	/**
	 * @author sh
	 * @param args TCPserver soket 통신
	 */
	public static void main(String[] args) {

		String socketIp = "127.0.0.1";
		int socketPort = 9090;
		logger.info(socketPort + "포트로 소캣 서버 생성");
		ServerSocket serverSocket = null;

		while (true) {
			try {
				// 9090 포트로 소 생성
				serverSocket = new ServerSocket();
				serverSocket.bind(new InetSocketAddress(socketIp, socketPort));

				// 접속 대기
				Socket socket = serverSocket.accept();
				InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

				// 접속 정보
				logger.info("[server] connected by client");
				logger.info("[server] Client IP " + socketAddress.getHostString());
				logger.info("[server] Client PORT " + socketAddress.getPort());

				ChatThread chatStart = new ChatThread(socket);
				chatStart.start();

			} catch (IOException e) {
				logger.error(e.getMessage());
			} finally {
				try {
					if (!serverSocket.isClosed())
						serverSocket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
class ChatThread extends Thread{
	private Logger logger = LoggerFactory.getLogger(ChatThread.class);
	Socket socket;

	public ChatThread(Socket socket) {
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

		try {
			while (true) {
				// client ->server
				is = socket.getInputStream();
				isr = new InputStreamReader(is, "UTF-8");
				br = new BufferedReader(isr);

				// server -> client
				os = socket.getOutputStream();
				osw = new OutputStreamWriter(os, "UTF-8");
				pw = new PrintWriter(osw, true);

				bf = br.readLine(); // Blocking
				if (bf == null) {
					// 정상종료 : remote socket close()
					// 메소드를 통해서 정상적으로 소켓을 닫은 경우
					logger.info("[server] closed by client");
					break;
				}
				logger.info("[server] recived : " + bf);
				pw.println(bf);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
