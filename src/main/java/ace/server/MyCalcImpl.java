package ace.server;

import java.rmi.RemoteException;
//import ace.server.MyCalc;
//import java.rmi.server.UnicastRemoteObject;

public class MyCalcImpl implements MyCalc {

	@Override
    public int add(int a, int b) throws RemoteException {
        return (a + b);
    }
}
