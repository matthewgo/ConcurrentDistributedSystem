package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiClientServerMain {
	private static List<Socket> socketsAcquired = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		int portNumber = 4441;

		ServerSocket serverSocket = new ServerSocket(portNumber);
		System.out.println("Running on port " + portNumber);
		while (true) {
			Socket socket = serverSocket.accept();
			socketsAcquired.add(socket);
			new ClientHandlerThread(socket).start();
		}

	}
}
