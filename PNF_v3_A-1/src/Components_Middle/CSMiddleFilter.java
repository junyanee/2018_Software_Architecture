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
public class CSMiddleFilter extends GeneralFilter { // 기능코드 : CS인 학생을 필터링해주는 기능

	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read=0;
		int checkBlank = 4;		// checkBlank default값을 Students.txt파일의 CS가 위치한 Blank값인 4로 설정한다.
		int databyte = 0;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		boolean isCS = false;

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
					// blank수와 buffer의 index수를 비교하여 'C'와 'S'를 판별해 CS가 맞다면 isCS를 true로 설정한다.
					if (numOfBlank == checkBlank && buffer[idx - 3] == 'C' && buffer[idx - 2] == 'S') {
						isCS = true;
					}
				}
				if (isCS == true) {		// isCS가 true라면 buffer값을 out에 write한다.
					for (int i = 0; i < idx; i++) {
						out.write((char) buffer[i]);
					}
					isCS = false;		// isCS를 false로 재설정한다.
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
