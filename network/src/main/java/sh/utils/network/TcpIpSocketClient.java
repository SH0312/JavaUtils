package sh.utils.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sh.utils.network.thread.ClientReceiveThread;
import sh.utils.network.thread.ClientSendThread;

public class TcpIpSocketClient {
	private static Logger logger = LoggerFactory.getLogger(TcpIpSocketClient.class);
	public static Socket socket = new Socket();
	// TODO 다중 접속 처리
	public static void main(String[] args) {
		socket = new Socket();
		InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 9090);
		
		try {
			socket.connect(addr);
			logger.info("[client] connected with server");

			ClientReceiveThread clientReceiveThread = new ClientReceiveThread();
			ClientSendThread clientSendThread = new ClientSendThread();

			clientReceiveThread.start();
			clientSendThread.start();
		} catch (IOException e) {
			logger.error(e.getMessage());
//			e.printStackTrace();
		}

	}
}
