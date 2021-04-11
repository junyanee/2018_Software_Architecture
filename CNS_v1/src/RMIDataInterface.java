
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-16
 * @version 1.0
 */
public interface RMIDataInterface extends Remote {	// Data 클래스의 함수를 가지고 있는 interface클래스
    public ArrayList getAllStudentRecords()
        throws RemoteException;

    public ArrayList getAllCourseRecords()
        throws RemoteException;

    public Student getStudentRecord(String sSID)
        throws RemoteException;

    public String getStudentName(String sSID)
        throws RemoteException;

    public Course getCourseRecord(String sCID)
        throws RemoteException;

    public String getCourseName(String sCID)
        throws RemoteException;

    public void makeARegistration(String sSID, String sCID)
        throws RemoteException;
}
