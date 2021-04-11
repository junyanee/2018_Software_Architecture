import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-07
 * @version 1.0
 * @description
 *      1개의 Student의 attribute들을 저장하기 위한 클래스.
 */
public class Student implements Serializable{
	protected String studentId;
    protected String name;
    protected String department;
    protected ArrayList completedCoursesList;
    protected int studentBalance;
    protected ArrayList vRegistered;
    /**
     * Constructor. Student 객체가 생성될 때, 입력값으로 받은 String 객체를 parsing하여 각 attribute에 맞는 값들을 획득한다.
     * @param inputString
     */
    public Student(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
        	this.studentId = stringTokenizer.nextToken();
        	this.name = stringTokenizer.nextToken();
        	this.name = this.name + " " + stringTokenizer.nextToken();
        	this.department = stringTokenizer.nextToken();

        	this.completedCoursesList = new ArrayList();
        	while (stringTokenizer.hasMoreTokens()) {
        		this.completedCoursesList.add(stringTokenizer.nextToken());
        	}
        	this.vRegistered = new ArrayList();
    }

    /**
     * 해당 Student 객체가 입력된 ID 값을 갖고 있는지 여부를 확인한다.
     * @param studentId
     * @return
     */
    public boolean match(String studentId) {
        return this.studentId.equals(studentId);
    }
    /**
     * 해당 Student 객체의 name 값을 반환한다. 
     * @return
     */
    public String getName() {
        return this.name;
    }
    /**
     * 해당 Student가 등록한 Course를 반환한다. 
     * @return
     */
    public ArrayList getRegisteredCourses() {
        return this.vRegistered;
    }   
    /**
     * 해당 Student 객체의 completed course 리스트를 반환한다. 
     * @return
     */
    public ArrayList getCompletedCourses() {
        return this.completedCoursesList;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * 해당 Student 객체를 저장(혹은 출력) 가능한 String 객체로 변환하여 반환한다.
     */
    public String toString() {
        String stringReturn = this.studentId + " " + this.name + " " + this.department;
        for (int i = 0; i < this.completedCoursesList.size(); i++) {
            stringReturn = stringReturn + " " + this.completedCoursesList.get(i).toString();
        }
        return stringReturn;
    }
    /**
     * 해당 Student 객체의 balance 값을 반환한다.
     * @return
     */
    public int getBalance() {
		return this.studentBalance;
	}
	/**
	 * 해당 Student 객체의 balance 값을 감소시킨다.
	 */
	public void decrementBalance() {
		this.studentBalance--;
	}
	/**
	 * 해당 Student 객체가 Course를 등록한다.
	 * @param objCourse
	 */
	public void registerCourse(Course objCourse) {
        this.vRegistered.add(objCourse);
    }
}
