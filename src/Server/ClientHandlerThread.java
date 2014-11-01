package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandlerThread extends Thread {

	private Socket socket;
	PrintWriter out;
	BufferedReader in;
	String programId;
	private Monitor monitor;

	public ClientHandlerThread(Socket socket) throws IOException {
		this.socket = socket;
		monitor = Monitor.getInstance();
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	}

	@Override
	public void run() {

		String inputLine, outputLine;

		try {
			programId = in.readLine();
			System.out.println(programId);

			out.println("Hi, #" + programId + " dancer");

			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("Finished")) {
					monitor.signaling();
					out.println("Just wait");
				} else {
					monitor.waiting();
					out.println("Dancer: " + programId);
				}
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
