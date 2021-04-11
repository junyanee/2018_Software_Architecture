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
public class SNumMiddleFilter extends GeneralFilter { // 기능코드 : 학번이 2013인 학생을 필터링해주는 기능

	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read=0;
		int checkBlank = 1;		// checkBlank default값을 Students.txt파일의 학번이 위치한 Blank값인 1로 설정한다.
		int databyte = 0;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		boolean isSnum = false;

		try {
			while (true) {
				while (databyte != '\n' && databyte != -1) {
					databyte = in.read();
					if (databyte == ' ') {		// databyte가 blank이면 numOfBlank를 증가시킨다.
						numOfBlank++;
					}
					if (databyte != -1) {		// databyte가 비어있지 않으면 buffer에 저장한다.
						buffer[idx++] = (byte) databyte;
					}
					// blank수와 buffer의 index수를 비교하여 byte별로 2013이 맞다면 isSnum을 true로 설정한다.
					if (numOfBlank == checkBlank && buffer[idx - 9] == '2' && buffer[idx - 8] == '0'
							&& buffer[idx - 7] == '1' && buffer[idx - 6] == '3') {
						isSnum = true;
					}
				}
				if (isSnum == true) {	// 학번이 2013이 맞다면 buffer값을 out에 write한다.
					for (int i = 0; i < idx; i++) {
						out.write((char) buffer[i]);
					}
					isSnum = false;		// isSnum을 false로 재설정한다.
				}
				if (byte_read == -1) {		// byte_read가 비어있으면(=다 읽었다면) return한다.
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
