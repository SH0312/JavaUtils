package sh.utils.network.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sh.utils.network.TcpIpSocketClient;
//메세지 받는 쓰레드
public class ClientReceiveThread extends Thread {
	private Logger logger = LoggerFactory.getLogger(ClientReceiveThread.class);

	public void run() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {

			while (true) {

				is = TcpIpSocketClient.socket.getInputStream();
				isr = new InputStreamReader(is, "UTF-8");
				br = new BufferedReader(isr);

				String data = br.readLine();
				
				if ("exit".equals(data)|| data == null)
					break;

				// server -> client
//				logger.info(data);
				System.out.println(data);

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
		}
	}
}
