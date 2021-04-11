package Components_Source;
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.CommonForFilter;
import Framework.GeneralFilter;


/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description
 *      입력값 파일에서 텍스트를 읽어들여서 output port에 기록하는 클래스.
 */
public class SourceFilter extends GeneralFilter{ //기능코드
    private String filePath;
    
    public SourceFilter(String inputFilePath){
        this.filePath = inputFilePath;
    }    

    @Override
    public void specificComputationForFilter() throws IOException {
        int byte_read;
        try { //source필터:파일을 input으로 받아 메모리에 저장하는 기능. 파일의값을읽어서 out에 write를해준다(파일의 값을 바이트로바꿔서 다른필터에 전달) /파일을읽는것만해야함
            @SuppressWarnings("resource")
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(filePath)));
            for(;;) {
                byte_read = br.read();
                if (byte_read == -1) 
                { return; }
                out.write(byte_read);
            }
        } 
        catch (IOException e) {
            if (e instanceof EOFException) return;
            else System.out.println(e);
        }
        finally {
        	try {
        		out.close();
        	} 
        	catch (IOException e) {
        		e.printStackTrace();} 
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
