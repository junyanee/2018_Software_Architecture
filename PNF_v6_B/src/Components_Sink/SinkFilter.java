package Components_Sink;
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.FileWriter;
import java.io.IOException;

import Framework.CommonForFilter;
import Framework.GeneralFilter;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description
 *      입력값으로 들어온 byte들을 읽어 CS 값만 출력값 파일에 기록하는 클래스.
 */
public class SinkFilter extends GeneralFilter{ //기능코드
    private String filePath;
    
    public SinkFilter(String outputFilePath) {
        this.filePath = outputFilePath;
    }

    @Override
    public void specificComputationForFilter() throws IOException {
    	int byte_read;
        
    	try { //sink필터:바이트로 들어온것을 파일에쓰는기능만 한다.
            FileWriter fw = new FileWriter(this.filePath);
            while(true) {
            	byte_read = in.read();
            	//System.out.println(byte_read);
            	if(byte_read == -1) {
            		fw.close();
            		System.out.print( "::Filtering is finished; Output file is created." );
            		return;
            	}
            	System.out.print((char)byte_read);
            	fw.write((char)byte_read);
            	fw.flush();
            }
    	}
    	catch (Exception e) {
            closePorts();
            e.printStackTrace();
            
        }
    }

	@Override
	public void connectOutputTo(CommonForFilter filter) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectInputTo(CommonForFilter filter) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
