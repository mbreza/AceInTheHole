package ace.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MyRMIServer{

	public static void main(String[] args) throws Exception {

    	//System.setProperty("java.rmi.server.hostname", "83.0.171.108");
    	System.setProperty("java.security.policy","file:/home/xyz/AceInTheHole/AceInTheHole/resources/rmi_connection.policy");
    	
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
        	String name = "Calculator";
            MyCalc engine = new MyCalcImpl();
            MyCalc stub = (MyCalc) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry(1099);
            System.out.println("Registering Calculator Object");
            registry.rebind(name, stub);
       
        } catch (Exception e) {
            System.err.println("Exception:" + e);
            e.printStackTrace();
        }
    }
}
