package Components_Middle;

/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.IOException;

import Framework.GeneralFilter;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-15
 * @version 1.0
 * @description 입력값으로 받은 byte들을 아무 기능도 하지 않고 그대로 전송하는 기능을 한다.
 */
public class MiddleFilter extends GeneralFilter { // 기능코드

	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read=0;
		int checkBlank = 4;
		int databyte = 0;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		boolean isCS = false;

		try {
			while (true) {
				while (databyte != '\n' && databyte != -1) {
					databyte = in.read();
					if (databyte == ' ') {
						numOfBlank++;
					}
					if (databyte != -1) {
						buffer[idx++] = (byte) databyte;
					}
					if (numOfBlank == checkBlank && buffer[idx - 3] == 'C' && buffer[idx - 2] == 'S') {
						isCS = true;
					}
				}
				if (isCS == true) {
//					System.out.println(idx + "*");
					for (int i = 0; i < idx; i++) {
						out.write((char) buffer[i]);
					}
					isCS = false;
				}
				if (byte_read == -1) {
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
