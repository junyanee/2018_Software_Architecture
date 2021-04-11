package Component.Logic;
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Component.Data.Course;
import Component.Data.Student;
import Framework.RMIDataInterface;
import Framework.RMILogicInterface;
/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-16
 * @version 1.0
 * @description
 *      Client로 부터 받은 요청을 Data Component에 요청하는 Component
 */
public class Logic extends UnicastRemoteObject implements RMILogicInterface {

    private static final String LOGIC_NAME = "Logic";
    private static final String DATA_NAME  = "Data";

    protected RMIDataInterface rmiDataNode;

    /**
     * Constructor. Logic 객체가 생성될 때, 입력값으로 받은 string name을 rmi로부터 lookup한다.
     * @param sDataName
     */
    public Logic(String sDataName)
           throws RemoteException, NotBoundException, MalformedURLException {
        this.rmiDataNode = (RMIDataInterface) Naming.lookup(sDataName);
    }

    /**
     * 등록된 모든 학생정보를 반환한다.
     * @return
     */
    public String getAllStudents()
                  throws RemoteException {
    	ArrayList vStudent = this.rmiDataNode.getAllStudentRecords();

        String sReturn = "";
        for (int i=0; i<vStudent.size(); i++) {
            sReturn += (i == 0 ? "" : "\n") + ((Student) vStudent.get(i)).toString();
        }
        return sReturn;
    }

    /**
     * 등록된 모든 과목정보를 반환한다.
     * @return
     */
    public String getAllCourses()
                  throws RemoteException {
    	ArrayList vCourse = this.rmiDataNode.getAllCourseRecords();

        String sReturn = "";
        for (int i=0; i<vCourse.size(); i++) {
            sReturn += (i == 0 ? "" : "\n") + ((Course) vCourse.get(i)).toString();
        }
        return sReturn;
    }

    /**
     * 입력받은 ID에 해당하는 과목을 등록한 학생정보를 반환한다.
     * @return
     */
    public String getRegisteredStudents(String sCID)
                  throws RemoteException {
        Course objCourse = this.rmiDataNode.getCourseRecord(sCID);
        if (objCourse == null) {
            return "Invalid course ID or course section";
        }
        ArrayList vStudent = objCourse.getRegisteredStudents();

        String sReturn = "";
        for (int i=0; i<vStudent.size(); i++) {
            sReturn += (i == 0 ? "" : "\n") + ((Student) vStudent.get(i)).toString();
        }
        return sReturn;
    }

    /**
     * 입력받은 학번에 해당하는 학생이 등록한 과목정보들을 반환한다.
     * @return
     */
    public String getRegisteredCourses(String sSID)
                  throws RemoteException {
        Student objStudent = this.rmiDataNode.getStudentRecord(sSID);
        if (objStudent == null) {
            return "Invalid student ID";
        }
        ArrayList vCourse = objStudent.getRegisteredCourses();

        String sReturn = "";
        for (int i=0; i<vCourse.size(); i++) {
            sReturn += (i == 0 ? "" : "\n") + ((Course) vCourse.get(i)).toString();
        }
        return sReturn;
    }

    /**
     * 입력받은 학번에 해당하는 학생이 수료한 과목정보를 반환한다.
     * @return
     */
    public String getCompletedCourses(String sSID)
                  throws RemoteException {
        Student objStudent = this.rmiDataNode.getStudentRecord(sSID);
        if (objStudent == null) {
            return "Invalid student ID";
        }
        ArrayList vCourseID = objStudent.getCompletedCourses();

        String sReturn = "";
        for (int i=0; i<vCourseID.size(); i++) {
            String sCID = (String) vCourseID.get(i);
            String sName = this.rmiDataNode.getCourseName(sCID);
            sReturn += (i == 0 ? "" : "\n") + sCID + " " + (sName == null ? "Unknown" : sName);
        }
        return sReturn;
    }

    /**
     * 입력받은 학번에 해당하는 학생이 입력받은 ID에 해당하는 과목을 등록한다.
     * @return
     */
    public String makeARegistration(String sSID, String sCID)
                  throws RemoteException {
        Student objStudent = this.rmiDataNode.getStudentRecord(sSID);
        Course objCourse = this.rmiDataNode.getCourseRecord(sCID);
        if (objStudent == null) {
            return "Invalid student ID";
        }
        if (objCourse == null) {
            return "Invalid course ID or course section";
        }

        ArrayList vCourse = objStudent.getRegisteredCourses();
        for (int i=0; i<vCourse.size(); i++) {
            if (((Course) vCourse.get(i)).conflicts(objCourse)) {
                return "Registration cconflicts";
            }
        }

        this.rmiDataNode.makeARegistration(sSID, sCID);
        return "Successful!";
    }

    public static void main(String args[]) {
        if (args.length != 0) {
            System.out.println("Incorrect number of parameters");
            System.out.println("Usage: java Logic");
            System.exit(1);
        }

        try {
            Logic objLogic = new Logic(DATA_NAME);
            //rmi에 "Logic" name 등록
            Naming.rebind(LOGIC_NAME, objLogic);
            System.out.println("Logic node is ready to serve.");

            System.out.println("Press Enter to terminate.");
            System.in.read();

            Naming.unbind(LOGIC_NAME);
            System.out.println("Logic node is leaving, bye. Press ctrl-c if the delay is too long.");
        }
        catch (java.rmi.ConnectException e) {
            System.err.println("Java RMI error: check if rmiregistry is running.");
            System.exit(1);
	}
        catch (java.rmi.NotBoundException e) {
            System.err.println("Java RMI error: check if data node is running.");
            System.exit(1);
        }
        catch (Exception e) {
            System.out.println("Unexpected exception at " + LOGIC_NAME);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
