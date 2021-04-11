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
public class PipeAndFilterSystem { //main함수. 변경은 여기서 일어남

    public static void main(String[] args) {//source->CS->insert->sink
        try {
            CommonForFilter sourceFilter1 = new SourceFilter("Students.txt");
            CommonForFilter CSMiddleFilter = new CSMiddleFilter();
            CommonForFilter insertCourseMiddleFilter = new InsertCourseMiddleFilter();
            CommonForFilter sinkFilter = new SinkFilter("Output.txt");
            
            sourceFilter1.connectOutputTo(CSMiddleFilter);
            CSMiddleFilter.connectOutputTo(insertCourseMiddleFilter);
            insertCourseMiddleFilter.connectOutputTo(sinkFilter);
            
            Thread thread1 = new Thread(sourceFilter1);
            Thread thread3 = new Thread(CSMiddleFilter);
            Thread thread4 = new Thread(insertCourseMiddleFilter);
            Thread thread5 = new Thread(sinkFilter);
            
            thread1.start();
            thread3.start();
            thread4.start();
            thread5.start();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
