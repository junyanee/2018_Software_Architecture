
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMILoginInterface extends Remote {
//	 public boolean logout(String id) throws RemoteException;
	 public void validate(String id, String pw) throws RemoteException;
}
