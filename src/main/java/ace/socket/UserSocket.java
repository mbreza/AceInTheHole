package ace.socket;


import ace.server.UserDAO;
import ace.service.UserManager;

public class UserSocket {

	UserDAO userManager;

	public String logIn(String userName, String password) {

			userManager = new UserManager();;
			
			if (userManager.logIn(userName, password)) {
				return "1";
			} else {
				return "0";
			}



	}

	public String createUser(String login, String password, String email) {

			userManager = new UserManager();

			userManager.addUser(login, password, email, 2);
			return "1";
	}

}
