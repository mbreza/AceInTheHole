package ace.server;

import java.io.PrintWriter;
import java.util.HashSet;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import ace.socket.UserThread;



public class MyServer{
	
	private static final int PORT = 1099;
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	 public static final String KEYSTORE_LOCATION = "file:/home/mbreza/eclipse-workspace/client/resources/keystore";
	 public static final String KEYSTORE_PASSWORD = "wisniaposysa1";

	public static void main(String[] args) throws Exception {
		
		 System.setProperty("javax.net.ssl.keyStore", KEYSTORE_LOCATION);
		 System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);
		
		
        System.out.println("The chat server is running.");
        SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket listener = (SSLServerSocket) socketFactory.createServerSocket(PORT);
        //ServerSocket listener = new ServerSocket(PORT);
        //SSLSocket socket = (SSLSocket) serverSocket.accept();
        try {
            while (true) {
                new UserThread((SSLSocket) listener.accept()).start();
            }
        } finally {
        	listener.close(); 
        }
		
		
    }
    
}
