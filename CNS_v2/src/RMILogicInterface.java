
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-16
 * @version 1.0
 */
public interface RMILogicInterface extends Remote { //������ ���� �Ʒ��� ���� ȣ���� ��
    public String getAllStudents() 
        throws RemoteException;

    public String getAllCourses() 
        throws RemoteException;

    public String getRegisteredStudents(String sCID) 
        throws RemoteException;

    public String getRegisteredCourses(String sSID) 
        throws RemoteException;

    public String getCompletedCourses(String sSID) 
        throws RemoteException;

    public String makeARegistration(String sSID, String sCID) 
        throws RemoteException;
    
}
