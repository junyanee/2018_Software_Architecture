package Components_Sink;
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */
import java.io.FileWriter;
import java.io.IOException;
import Framework.GeneralFilter;
/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description
 *      입력값으로 들어온 byte들을 읽어 CS 값만 출력값 파일에 기록하는 클래스.
 */
public class SinkFilter extends GeneralFilter{	// 기능코드 : byte로 들어온 정보를 파일에 쓰는 기능
    private String filePath;
    
    public SinkFilter(String outputFilePath) {
        this.filePath = outputFilePath;
    }

    @Override
    public void specificComputationForFilter() throws IOException {
    	int byte_read;
        
    	try {
            FileWriter fw = new FileWriter(this.filePath);	// 결과를 파일에 쓰기 위해 FileWriter를 선언한다.
            while(true) {
            	byte_read = in.read();
            	if(byte_read == -1) {	// byte_read가 비어있으면(=파일을 다 읽었으면) FileWriter를 닫는다.
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
}
