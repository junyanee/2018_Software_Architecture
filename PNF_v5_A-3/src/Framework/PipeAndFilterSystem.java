package Framework;

import Components_Middle.CSDeleteCourseMiddleFilter;
import Components_Middle.CSMiddleFilter;
import Components_Middle.SNumMiddleFilter;
import Components_Sink.SinkFilter;
import Components_Source.SourceFilter;

/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */
/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description Filter들의 input/output port를 연결하고 실제로 구동을 시키는 main 메소드가 위치한 클래스.
 */
public class PipeAndFilterSystem { // main함수가 위치하여 실제 구동에 있어서의 변경은 이 클래스에서 발생한다.

	public static void main(String[] args) { // SourceFilter->SNumMiddleFilter->CSMiddleFilter->CSDeleteCourseMiddleFilter->SinkFilter 순으로 연결된다.
		try {
			CommonForFilter sourceFilter = new SourceFilter("Students.txt");
			CommonForFilter SNumMiddleFilter = new SNumMiddleFilter();
			CommonForFilter CSMiddleFilter = new CSMiddleFilter();
			CommonForFilter CSDeleteCourseMiddleFilter = new CSDeleteCourseMiddleFilter();
			CommonForFilter sinkFilter = new SinkFilter("Output.txt");

			sourceFilter.connectOutputTo(SNumMiddleFilter);
			SNumMiddleFilter.connectOutputTo(CSMiddleFilter);
			CSMiddleFilter.connectOutputTo(CSDeleteCourseMiddleFilter);
			CSDeleteCourseMiddleFilter.connectOutputTo(sinkFilter);

			Thread thread1 = new Thread(sourceFilter);
			Thread thread3 = new Thread(SNumMiddleFilter);
			Thread thread4 = new Thread(CSMiddleFilter);
			Thread thread5 = new Thread(CSDeleteCourseMiddleFilter);
			Thread thread6 = new Thread(sinkFilter);

			thread1.start();
			thread3.start();
			thread4.start();
			thread5.start();
			thread6.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
