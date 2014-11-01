package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class ClientMain {

	public static CustomFrame frame;
	private static MattClientProtocol p;

	public static void main(String[] args) throws UnknownHostException,
			IOException, InterruptedException {

		String hostName = "localhost";
		int portNumber = 4441;
		Random rand = new Random();
		String programId = "" + rand.nextInt(999);
		p = new MattClientProtocol(programId);
		frame = new CustomFrame(programId);

		Socket echoSocket = new Socket(hostName, portNumber);
		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				echoSocket.getInputStream()));

		out.println(programId);

		String fromServer;
		while ((fromServer = in.readLine()) != null) {
			if (p.canRequest() == true) {
				out.println(p.sendIWannaDance());
			}
			String s = p.handleMessages(fromServer);

			if (s != null)
				frame.label.setText(s);

			if (s.equals("I'm dancing centerstage!!")) {
				Thread.sleep(5000);
				frame.label.setText("YAA WHEN WILL IT BE MY TURN?!?!?!");
				out.println("Finished");
			}

		}
		echoSocket.close();
	}
}
