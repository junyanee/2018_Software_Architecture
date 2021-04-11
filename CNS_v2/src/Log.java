import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log extends UnicastRemoteObject implements RMILogInterface {
	private static final String LOG_NAME = "Log";

	protected RMILogInterface rmiLogNode;

	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd a HH:mm:ss");	// 이 형식대로 timestamp 기록
	FileWriter write;

	public Log(String sLogName) throws RemoteException, MalformedURLException, NotBoundException, IOException {

		try {
			write = new FileWriter("log.txt", true);	// log.txt파일에 로그 기록
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 로그 기록
	public void log(String msg) {

		try {
			write.write(msg);
			write.write(sdf.format(new Date()) + "\n");
			write.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]){

		try {
			Log objLog = new Log(LOG_NAME);
			// rmi에 "Log" name 등록
			Naming.bind(LOG_NAME, objLog);
			System.out.println("Log node is ready to serve.");

			System.out.println("Press Enter to terminate.");
			System.in.read();

			Naming.unbind(LOG_NAME);
			System.out.println("Log node is leaving, bye. Press ctrl-c if the delay is too long.");
		} catch (java.rmi.ConnectException e) {
			System.err.println("Java RMI error: check if rmiregistry is running.");
			System.exit(1);
		} catch (java.rmi.NotBoundException e) {
			System.err.println("Java RMI error: check if login node is running.");
			System.exit(1);
		} catch (Exception e) {
			System.out.println("Unexpected exception at " + LOG_NAME);
			e.printStackTrace();
			System.exit(1);
		}

	}
}
