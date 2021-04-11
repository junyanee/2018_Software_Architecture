package Framework;
import Components_Middle.PreCourseMiddleFilter;
import Components_Sink.SinkFilter;
import Components_Source.SourceFilter;

/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */


/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description
 *      Filter들의 input/output port를 연결하고 실제로 구동을 시키는 main 메소드가 위치한 클래스.
 */
public class PipeAndFilterSystem { //main함수. 변경은 여기서 일어남

    public static void main(String[] args) {//source->CS->insert->sink
        try {
            CommonForFilter studentsSourceFilter = new SourceFilter("Students.txt");
            CommonForFilter coursesSourceFilter = new SourceFilter("Courses.txt");
            
            CommonForFilter PreCourseMiddleFilter = new PreCourseMiddleFilter();
            
            CommonForFilter satisfiedFilter = new SinkFilter("SatisfiedOutput.txt");
            CommonForFilter nsatisfiedFilter = new SinkFilter("NsatisfiedOutput.txt");
            
            studentsSourceFilter.connectOutputTo(PreCourseMiddleFilter);
            coursesSourceFilter.connectOutputTo(PreCourseMiddleFilter);
            
            PreCourseMiddleFilter.connectOutputTo(satisfiedFilter);
            PreCourseMiddleFilter.connectOutputTo(nsatisfiedFilter);
            
            Thread thread1 = new Thread(studentsSourceFilter);
            Thread thread2 = new Thread(coursesSourceFilter);
            Thread thread3 = new Thread(PreCourseMiddleFilter);
            Thread thread4 = new Thread(satisfiedFilter);
            Thread thread5 = new Thread(nsatisfiedFilter);
            
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
