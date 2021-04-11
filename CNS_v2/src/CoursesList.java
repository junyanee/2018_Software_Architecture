import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-07
 * @version 1.0
 * @description
 *      모든 Course들을 갖고 있는 리스트 클래스. 
 */
public class CoursesList {
    protected ArrayList vCourse;
    /**
     * Constructor. 모든 Course들의 정보를 갖고 있는 파일을 읽어들여 객체에 저장한다.
     * 
     * @param sCourseFileName
     * @throws FileNotFoundException
     * @throws IOException
     */
    public CoursesList(String sCourseFileName) throws FileNotFoundException, IOException {
        BufferedReader objCourseFile  = new BufferedReader(new FileReader(sCourseFileName));
        
        this.vCourse  = new ArrayList();
        
        while (objCourseFile.ready()) {
            String courseInfo = objCourseFile.readLine();
            if(!courseInfo.equals("")) {
                this.vCourse.add(new Course(courseInfo));
            }
        }        
        objCourseFile.close();
    }
    /**
     * 모든 Course들을 배열 타입으로 반환한다.
     * @return
     */
    public ArrayList getAllCourseRecords() {
        return this.vCourse;
    }
    
    /**
     * 입력받은 ID 값을 갖고 있는 Course 객체가 존재하는지 확인한다.
     * 
     * @param courseId
     * @return
     */
    public boolean isRegisteredCourse(String courseId) {
        for (int i = 0; i < this.vCourse.size(); i++) {
            Course course = (Course) this.vCourse.get(i);
            if(course.match(courseId)) {
                return true;
            }
        }
        return false;
    }
}
