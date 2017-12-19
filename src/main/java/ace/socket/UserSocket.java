package ace.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ace.server.UserDAO;
import ace.service.UserManager;

public class UserSocket {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	UserDAO userManager;

	public void logIn() {

		try {

			userManager = new UserManager();

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			String userName = in.readLine();
			String password = in.readLine();

			if (userManager.logIn(userName, password)) {
				out.println("1");
			} else {
				out.println("0");
				userName = "";
				password = "";
			}
			out.close();
			in.close();

		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void createUser() {
		try {

			userManager = new UserManager();

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			String login = in.readLine();
			String password = in.readLine();
			String email = in.readLine();

			userManager.addUser(login, password, email, 2);
			out.println("1");

			out.close();
			in.close();

		} catch (IOException e) {
			System.out.println(e);
		}

	}

}
