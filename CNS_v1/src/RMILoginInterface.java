
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMILoginInterface extends Remote {	// Login 클래스의 함수를 가지고 있는 interface클래스
	 public void validate(String id, String pw) throws RemoteException;
}
