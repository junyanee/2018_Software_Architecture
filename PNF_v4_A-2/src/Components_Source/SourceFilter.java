package Components_Source;
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import Framework.GeneralFilter;
/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description 입력값 파일에서 텍스트를 읽어들여서 output port에 기록하는 클래스.
 */
public class SourceFilter extends GeneralFilter{	// 기능코드 : 파일의 값을 읽어 byte로 변환 후 전달하는 기능
    private String filePath;
    
    public SourceFilter(String inputFilePath){
        this.filePath = inputFilePath;
    }    

    @Override
    public void specificComputationForFilter() throws IOException {
        int byte_read;
        try {
            @SuppressWarnings("resource")
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(filePath)));	// 파일을 input으로 받아 메모리에 저장함
            for(;;) {
                byte_read = br.read();
                if (byte_read == -1) 
                { return; }
                out.write(byte_read);	// 필터에 전달하기 위해 읽은 파일의 값을 out에 write한다.
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
}
