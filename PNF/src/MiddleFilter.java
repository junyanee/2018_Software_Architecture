/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.IOException;


/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description
 *      입력값으로 받은 byte들을 아무 기능도 하지 않고 그대로 전송하는 기능을 한다.
 */
public class MiddleFilter extends GeneralFilter{ //기능코드

    @Override
    public void specificComputationForFilter() throws IOException {
        int byte_read;
        
        for(;;) { //읽어서 out으로 넘겨주기만 한다. 아직 기능은 없다. 기능을 우리가 만들어야 한다. CS로 고치는기능을 넣어야한다.
            byte_read = in.read();
            //---------- 이부분을 고치기
            if (byte_read == -1) 
                return;
            //----------
            out.write(byte_read);
        }
    }    

}
