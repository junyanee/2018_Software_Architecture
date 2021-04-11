/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.FileWriter;
import java.io.IOException;

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
        int checkBlank = 4;
        int databyte = 0;  
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean isCS = false;
        
        try { //sink필터:바이트로 들어온것을 파일에쓰는기능만 한다.
            FileWriter fw = new FileWriter(this.filePath);
            while(true) {
            	//이부분이어렵다. 바이트8개를 붙여서 이것이 학번이라고 코딩해야한다. blank가 나올때까지 읽는것을 token이라고한다. blank를 token이라고 한다. numberofblank가 이 코드이다. 이걸 버퍼에 쌓아야한다.
                while(databyte != '\n' && databyte != -1) {
                    databyte = in.read();
                    if(databyte == ' '){
                        numOfBlank++;
                    }   
                    if(databyte != -1){
                        buffer[idx++] = (byte)databyte;
                    }
                    // CS를 고른다. Middle필터로 옮겨야한다.
                    if(numOfBlank == checkBlank && buffer[idx-3] == 'C' && buffer[idx-2] == 'S'){
                        isCS = true;
                    }
                }
                
                if(isCS == true) {
                    for(int i = 0; i<idx; i++) {
                        fw.write((char)buffer[i]);
                    }
                    isCS = false;
                }
                if(databyte == -1){
                    fw.close();
                    System.out.print( "::Filtering is finished; Output file is created." );
                    return;
                }
                idx = 0;
                numOfBlank = 0;
                databyte = '\0';
            }   
        } catch (Exception e) {
            closePorts();
            e.printStackTrace();
            
        }
    }

}
