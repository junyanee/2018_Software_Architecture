package Components_Middle;

import java.io.IOException;

import Framework.GeneralFilter;

public class EEInsertCourseMiddleFilter extends GeneralFilter {		// 기능코드 : 23456을 듣지 않은 학생들의 정보에 해당 과목을 추가하는 기능

	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read = 0;
		int checkBlank = 6;		// checkBlank default값을 Students.txt파일의 23456이 위치한 Blank값인 6로 설정한다.
		int databyte = 0;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		boolean isCourse = false;
		String num2 = "23456 ";

		while (true) {
			while (databyte != '\n' && databyte != -1) {
				databyte = in.read();

				if (databyte == ' ') {	// databyte가 blank이면 numOfBlank를 증가시킨다.
					numOfBlank++;
				}
				if (databyte != -1) {	// databyte가 비어있지 않으면 buffer에 저장한다.
					buffer[idx++] = (byte) databyte;
				}
				// blank수와 buffer의 index수를 비교하여 byte별로 23456을 판단해 23456이 맞다면 isCourse를 true로 설정한다.
				if (numOfBlank == checkBlank && buffer[idx - 6] == '2' && buffer[idx - 5] == '3'
						&& buffer[idx - 4] == '4' && buffer[idx - 3] == '5' && buffer[idx - 2] == '6') {
					isCourse = true;
				}
			}
			if (isCourse == true) {		// 23456과목을 수강했다면 그대로 buffer의 값을 out에 write한다.
				for (int i = 0; i < idx; i++) {
					out.write((char) buffer[i]);
				}
				isCourse = false;	// isCourse를 false로 재설정한다.
			}
			else {		// 23456과목을 수강하지 않았다면 23456과목을 write한다.
				out.write(num2.getBytes());
				for (int i = 0; i < idx; i++) {		// buffer에 저장되어있는 정보는 그대로 write한다.
					out.write((char) buffer[i]);
				}
				isCourse = false;	// isCourse를 false로 재설정한다.
			}
			if (byte_read == -1) {	// byte_read가 비어있으면(=다 읽었으면) return한다.
				return;
			}
			idx = 0;
			numOfBlank = 0;
			databyte = '\0';
		}
	}
}
