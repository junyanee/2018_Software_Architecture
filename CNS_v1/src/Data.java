/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-16
 * @version 1.0
 * @description
 *      모든 학생들과 과목들의 정보를 가지고 있는 Component
 */
public class Data extends UnicastRemoteObject implements RMIDataInterface {		// 모든 학생들과 과목들의 정보를 가지고 있음

    private static final String DATA_NAME = "Data";

    protected StudentsList studentsList;
    protected CoursesList coursesList;

    public Data(String sStudentFileName, String sCourseFileName)
           throws RemoteException, FileNotFoundException, IOException {

        this.studentsList = new StudentsList(sStudentFileName);
        this.coursesList  = new CoursesList(sCourseFileName);
    }

    /**
     * 모든 학생정보를 반환한다. 
     * @return
     */
    public ArrayList getAllStudentRecords() {
        return this.studentsList.vStudent;
    }

    /**
     * 모든 과목정보를 반환한다.
     * @return
     */
    public ArrayList getAllCourseRecords() {
        return this.coursesList.vCourse;
    }

    /**
     * 입력받은 학번을 가진 학생정보를 반환한다.
     * @return
     */
    public Student getStudentRecord(String sSID) {
        for (int i=0; i<this.studentsList.vStudent.size(); i++) {
            Student objStudent = (Student) this.studentsList.vStudent.get(i);
            if (objStudent.match(sSID)) {
                return objStudent;
            }
        }
        return null;
    }
    
    /**
     * 입력받은 학번을 가진 학생의 이름을 반환한다.
     * @return
     */
    public String getStudentName(String sSID) {
        for (int i=0; i<this.studentsList.vStudent.size(); i++) {
            Student objStudent = (Student) this.studentsList.vStudent.get(i);
            if (objStudent.match(sSID)) {
                return objStudent.getName();
            }
        }
        return null;
    }

    /**
     * 입력받은 ID를 가진 과목정보를 반환한다.
     * @return
     */
    public Course getCourseRecord(String sCID) {
        for (int i=0; i<this.coursesList.vCourse.size(); i++) {
            Course objCourse = (Course) this.coursesList.vCourse.get(i);
            if (objCourse.match(sCID)) {
                return objCourse;
            }
        }
        return null;
    }
    
    /**
     * 입력받은 ID를 가진 과목의 이름을 반환한다.
     * @return
     */
    public String getCourseName(String sCID) {
        for (int i=0; i<this.coursesList.vCourse.size(); i++) {
            Course objCourse = (Course) this.coursesList.vCourse.get(i);
            if (objCourse.match(sCID)) {
                return objCourse.getName();
            }
        }
        return null;
    }

    /**
     * 입력받은 학번을 가진 학생에게 입력받은 ID을 가진 과목을 등록한다.
     */
    public void makeARegistration(String sSID, String sCID) {
        Student objStudent = this.getStudentRecord(sSID);
        Course  objCourse  = this.getCourseRecord(sCID);

        if (objStudent != null && objCourse != null) {
            objStudent.registerCourse(objCourse);
            objCourse.registerStudent(objStudent);
        }
    }

    public static void main(String args[]) {
        if (args.length != 2) {
            System.err.println("Incorrect number of parameters");
            System.err.println("Usage: java Data <StudentFile> <CourseFile>");
            System.exit(1);
        }

        if (!new File(args[0]).exists()) {	// Students.txt파일이 없으면 에러 메시지를 띄운다.
            System.err.println("Could not find " + args[0]);
            System.exit(1);
        }
        if (new File(args[1]).exists() == false) {	// Courses.txt파일이 없으면 에러 메시지를 띄운다.
            System.err.println("Could not find " + args[1]);
            System.exit(1);
        }

        try {
            Data objData = new Data(args[0], args[1]);
            Naming.bind(DATA_NAME, objData);	// 
            System.out.println("Data node is ready to serve.");

            System.out.println("Press Enter to terminate.");
            System.in.read();

            Naming.unbind(DATA_NAME);	// 
            System.out.println("Data node is leaving, bye. Press ctrl-c if the delay is too long.");
        }
        catch (java.rmi.ConnectException e) {	// RMI 연결 오류 예외 처리
            System.err.println("Java RMI error: check if rmiregistry is running.");
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Unexpected exception at " + DATA_NAME);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
