package Component.Client;
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import Framework.RMILogicInterface;
/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-16
 * @version 1.0
 * @description
 *      사용자의 입력에 따라  학생과 과목을 조회, 등록을 할 수 있는 Text-based User Interface
 */
public class Client {
	
    private static final String CLIENT_NAME = "Client";
    private static final String LOGIC_NAME  = "Logic";


    protected RMILogicInterface rmiLogicNode;
    /**
 	* Constructor. Client 객체가 생성될 때, 입력값으로 받은 string name을 rmi로부터 lookup한다.
 	* @param sLoginId
 	*/
    public Client(String sLogicId)
            throws NotBoundException, MalformedURLException, RemoteException {
            this.rmiLogicNode = (RMILogicInterface) Naming.lookup(sLogicId);
        }
//    public Client(String sLogicName)
//        throws NotBoundException, MalformedURLException, RemoteException {
//        this.rmiLogicNode = (RMILogicInterface) Naming.lookup(sLogicName);
//    }
	
    /**
     * 사용자의 입력에 따라 학생과 과목을 조희, 등록할 수 있다.
     */
    public void execute()
        throws RemoteException, IOException {
        BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
        	//사용자가 입력가능한 메뉴 출력
        	System.out.println("Enter your ID..");
        	
        	System.out.println( "Registeration System\n" );
            System.out.println("1. List Students");
            System.out.println("2. List Courses");
            System.out.println("3. List students who registered for a course");
            System.out.println("4. List courses a student has registered for");
            System.out.println("5. List courses a student has completed");
            System.out.println("6. Register a student for a course");
            System.out.println("\n 0. Quit the system");
            String sChoice = objReader.readLine().trim();
            
            if (sChoice.equals("1")) {
                System.out.println("\n" + this.rmiLogicNode.getAllStudents());
                continue;
            }

            if (sChoice.equals("2")) {
                System.out.println("\n" + this.rmiLogicNode.getAllCourses());
                continue;
            }

            if (sChoice.equals("3")) {
                System.out.println("\nEnter course ID and press return >> ");
                String sCID = objReader.readLine().trim();

                System.out.println("\n" + this.rmiLogicNode.getRegisteredStudents(sCID));
                continue;
            }

            if (sChoice.equals("4")) {
                System.out.println("\nEnter student ID and press return >> ");
                String sSID = objReader.readLine().trim();

                System.out.println("\n" + this.rmiLogicNode.getRegisteredCourses(sSID));
                continue;
            }

            if (sChoice.equals("5")) {
                System.out.println("\nEnter student ID and press return >> ");
                String sSID = objReader.readLine().trim();

                System.out.println("\n" + this.rmiLogicNode.getCompletedCourses(sSID));
                continue;
            }

            if (sChoice.equals("6")) {
                System.out.println("\nEnter student ID and press return >> ");
                String sSID = objReader.readLine().trim();
                System.out.println("\nEnter course ID and press return >> ");
                String sCID = objReader.readLine().trim();

                System.out.println("\n" + this.rmiLogicNode.makeARegistration(sSID, sCID));
                continue;
            }
            if (sChoice.equalsIgnoreCase("0")) {
                break;
            }

            
        }

        objReader.close();
    }
    public static void main(String args[]) {
        if (args.length != 0) {
            System.out.println("Incorrect number of parameters");
            System.out.println("Usage: java Client");
            System.exit(1);
        }

        try {
            Client objClient = new Client(LOGIC_NAME);
            objClient.execute();
        }
        catch (java.rmi.ConnectException e) {
            System.err.println("Java RMI error: check if rmiregistry is running.");
            System.exit(1);
	}
        catch (java.rmi.NotBoundException e) {
            System.err.println("Java RMI error: check if logic node is running.");
            System.exit(1);
        }

        catch (Exception e) {
            System.out.println("Unexpected exception at " + CLIENT_NAME);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
