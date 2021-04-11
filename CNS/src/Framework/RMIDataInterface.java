package Framework;
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Component.Data.Course;
import Component.Data.Student;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-16
 * @version 1.0
 */
public interface RMIDataInterface extends Remote {
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
