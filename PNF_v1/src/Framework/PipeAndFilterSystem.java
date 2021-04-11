package Framework;
import Components_Middle.MiddleFilter;
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

    public static void main(String[] args) {
        try {
            CommonForFilter filter1 = new SourceFilter("Students.txt");
            CommonForFilter filter2 = new SinkFilter("Output.txt");
            CommonForFilter filter3 = new MiddleFilter();
            
            filter1.connectOutputTo(filter3);
            filter3.connectOutputTo(filter2);
            
            Thread thread1 = new Thread(filter1);
            Thread thread2 = new Thread(filter2);
            Thread thread3 = new Thread(filter3);
            
            thread1.start();
            thread2.start();
            thread3.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
