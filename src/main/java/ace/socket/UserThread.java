package ace.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.ssl.SSLSocket;

import ace.server.UserDAO;

public class UserThread extends Thread {

	private SSLSocket socket;
	private BufferedReader in;
	private PrintWriter out;
	UserDAO userManager;
	String navigate;
	UserSocket userSocket;

	public UserThread(SSLSocket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			userSocket = new UserSocket();

			while (true) {

				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				navigate = in.readLine();

				if (navigate.equals("logIn")) {
					String userName = in.readLine();
					String password = in.readLine();
					String value = userSocket.logIn(userName, password);
					out.println(value);
				}

				if (navigate.equals("createUser")) {
					String login = in.readLine();
					String password = in.readLine();
					String email = in.readLine();
					String value = userSocket.createUser(login, password, email);
					out.println(value);
				}

				navigate = "";
				in.close();
				out.close();
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
