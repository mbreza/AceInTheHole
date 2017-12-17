package ace.server;

public interface UserDAO{
	public Integer addUser(String login, String password, String email, int ID);
	public String getAllUsers();
	public boolean logIn(String login, String password);
}