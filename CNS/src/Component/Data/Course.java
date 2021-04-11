package Component.Data;
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-07
 * @version 1.0
 * @description
 *      1개 Course의 attribute들을 저장하기 위한 클래스.
 */
public class Course implements Serializable{ //엔티티클래스

    protected String courseId;
    protected String instructor;
    protected String name;
    protected ArrayList prerequisiteCoursesList;
    protected ArrayList vRegistered;

    /**
     * Constructor. Course 객체가 생성될 때, 입력값으로 받은 String 객체를 parsing하여 각 attribute에 맞는 값들을 획득한다.
     * @param inputString
     */
    public Course(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);

        this.courseId = stringTokenizer.nextToken();
        this.instructor = stringTokenizer.nextToken();
        this.name = stringTokenizer.nextToken();
        
        this.prerequisiteCoursesList = new ArrayList();
        while(stringTokenizer.hasMoreTokens()) {
            this.prerequisiteCoursesList.add(stringTokenizer.nextToken());
        }
        this.vRegistered = new ArrayList();
    }

    /**
     * 해당 Course 객체가 입력된 ID 값을 갖고 있는지 여부를 확인한다.
     * @param courseId
     * @return
     */
    public boolean match(String courseId) {
        return this.courseId.equals(courseId);
    }
    
    /**
     * 해당 Course 객체의 name 값을 반환한다. 
     * @return
     */
    public String getName() {
        return this.name;
    }
    
    public ArrayList getRegisteredStudents() {
        return this.vRegistered;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * 해당 Course 객체를 저장(혹은 출력) 가능한 String 객체로 변환하여 반환한다.
     */
    public String toString() {
        String stringReturn = this.courseId + " " + this.instructor + " " + this.name;
        
        for(int i = 0; i < this.prerequisiteCoursesList.size(); i++) {
            stringReturn += " " + this.prerequisiteCoursesList.get(i).toString();
        }
        
        return stringReturn;
    }
    
    public void registerStudent(Student objStudent) {
        this.vRegistered.add(objStudent);
    }
    
    /**
     * 해당 Course객체와 입력된 Course가 같은지 확인한다.
     * @param objCourse
     * @return 같으면 true, 다르면 false
     */
    public boolean conflicts(Course objCourse) {
        if (this.courseId.equals(objCourse.courseId)) {
            return true;
        }
        return false;
    }
}
