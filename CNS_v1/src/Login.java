
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Login extends UnicastRemoteObject implements RMILoginInterface {
	private static final String LOGIN_NAME = "Login";
	private static final String LOGIC_NAME = "Logic";

	protected RMILogicInterface rmiLogicNode;

	public boolean loginSuccess;

	/**
	 * Constructor. Login 객체가 생성될 때, 입력값으로 받은 string name을 rmi로부터 lookup한다.
	 * 
	 * @param sDataName
	 */
	public Login(String sLogicName) throws RemoteException, NotBoundException, MalformedURLException {
		this.rmiLogicNode = (RMILogicInterface) Naming.lookup(sLogicName);
	}

	@Override
	public void validate(String id, String pw) throws RemoteException {		// 등록된 ID, PW와 일치하는지 확인하는 함수
		try {
			Client client = new Client(id, pw);
			if (client.idTrue.equals("123")&&client.pwTrue.equals("234")) {	// ID가 123, PW가 234이면 로그인 성공
				loginSuccess = true;
			} else {
				loginSuccess = false;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		if (args.length != 0) {
			System.out.println("Incorrect number of parameters");
			System.out.println("Usage: java Login");
			System.exit(1);
		}

		try {
			Login objLogin = new Login(LOGIC_NAME);
			// rmi에 "Login" name 등록
			Naming.rebind(LOGIN_NAME, objLogin);
			System.out.println("Login node is ready to serve.");

			System.out.println("Press Enter to terminate.");
			System.in.read();

			Naming.unbind(LOGIN_NAME);
			System.out.println("Login node is leaving, bye. Press ctrl-c if the delay is too long.");
		} catch (java.rmi.ConnectException e) {
			System.err.println("Java RMI error: check if rmiregistry is running.");
			System.exit(1);
		} catch (java.rmi.NotBoundException e) {
			System.err.println("Java RMI error: check if logic node is running.");
			System.exit(1);
		} catch (Exception e) {
			System.out.println("Unexpected exception at " + LOGIN_NAME);
			e.printStackTrace();
			System.exit(1);
		}
	}

}
