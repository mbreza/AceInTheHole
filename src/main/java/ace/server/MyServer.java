package ace.server;

import java.io.PrintWriter;
import java.net.ServerSocket;

import java.util.HashSet;

import ace.socket.UserThread;



public class MyServer{
	
	private static final int PORT = 1099;
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();


	public static void main(String[] args) throws Exception {
	
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        
        
        try {
            while (true) {
                new UserThread(listener.accept()).start();
            }
        } finally {
        	listener.close();
        }
		
		
    }
    
}
