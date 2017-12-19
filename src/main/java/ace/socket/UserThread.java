package ace.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import ace.server.UserDAO;

public class UserThread extends Thread {

	private Socket socket;
	private BufferedReader in;
	UserDAO userManager;
	String navigate;
	UserSocket userSocket;

	public UserThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			userSocket = new UserSocket();

			while (true) {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				navigate = in.readLine();

				if (navigate == "logIn") {
					userSocket.logIn();
				}

				if (navigate == "createUser") {
					userSocket.createUser();
				}

				navigate = "";
				in.close();
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
