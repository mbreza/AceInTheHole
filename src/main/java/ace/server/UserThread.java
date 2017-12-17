package ace.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ace.service.UserManager;

public class UserThread extends Thread{
	
	private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    UserDAO userManager;
	
    public UserThread(Socket socket) {
        this.socket = socket;            
    }
    
    public void run() {
        try {

            in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            while (true) {
            	
                String userName = in.readLine();
                String password = in.readLine();
                
                userManager = new UserManager();
                if(userManager.logIn(userName, password)){
                	out.println(1);
                	break;
                }else {
                	out.println(0);
                }          
            }
            
            

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            
        }
    }
	
}
