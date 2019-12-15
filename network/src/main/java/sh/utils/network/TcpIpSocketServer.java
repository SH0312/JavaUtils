package sh.utils.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sh.utils.network.thread.ServerThread;

public class TcpIpSocketServer {
	private static Logger logger = LoggerFactory.getLogger(TcpIpSocketServer.class);

	public static List<Socket> ClientLista = new ArrayList<Socket>();
	public static Map<Integer, Socket> ClientList = new HashMap<Integer, Socket>();
	public static int CleintCnt = 0;

	/**
	 * @author sh
	 * @param args TCPserver soket 통신
	 */
	public static void main(String[] args) {

		String socketIp = "127.0.0.1";
		int socketPort = 9090;
		ServerSocket serverSocket = null;

		while (true) {
			try {
				
				// 접속 대기
				serverSocket = new ServerSocket();
				serverSocket.bind(new InetSocketAddress(socketIp, socketPort));
				Socket socket = serverSocket.accept();
				InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

				// 접속 정보
				logger.info("[Server] connected by client");
				logger.info("[Server] Client IP " + socketAddress.getHostString());
				logger.info("[Server] Client Name " + socketAddress.getHostName());
				logger.info("[Server] Client PORT " + socketAddress.getPort());

				ServerThread serverThread = new ServerThread(socket);
				ClientList.put(++CleintCnt, socket);
				ClientLista.add(socket);
				serverThread.start();

			} catch (IOException e) {
				logger.error(e.getMessage());
				break;
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


