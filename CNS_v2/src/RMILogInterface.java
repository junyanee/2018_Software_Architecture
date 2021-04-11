import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMILogInterface extends Remote {	// Log 클래스의 함수를 가진 interface 클래스
	public void log(String msg) throws RemoteException;
	public void close() throws RemoteException;
}
