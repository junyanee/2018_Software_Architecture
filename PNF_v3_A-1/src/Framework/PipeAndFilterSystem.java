package Framework;
import Components_Middle.CSMiddleFilter;
import Components_Middle.InsertCourseMiddleFilter;
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
public class PipeAndFilterSystem {		// main함수가 위치하여 실제 구동에 있어서의 변경은 이 클래스에서 발생한다.

    public static void main(String[] args) {	// SourceFilter->CSMiddleFilter->InsertCourseMiddleFilter->SinkFilter 순으로 연결된다.
        try {
            CommonForFilter sourceFilter1 = new SourceFilter("Students.txt");
            CommonForFilter CSMiddleFilter = new CSMiddleFilter();
            CommonForFilter insertCourseMiddleFilter = new InsertCourseMiddleFilter();
            CommonForFilter sinkFilter = new SinkFilter("Output.txt");
            
            sourceFilter1.connectOutputTo(CSMiddleFilter);
            CSMiddleFilter.connectOutputTo(insertCourseMiddleFilter);
            insertCourseMiddleFilter.connectOutputTo(sinkFilter);
            
            Thread thread1 = new Thread(sourceFilter1);
            Thread thread2 = new Thread(CSMiddleFilter);
            Thread thread3 = new Thread(insertCourseMiddleFilter);
            Thread thread4 = new Thread(sinkFilter);
            
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
