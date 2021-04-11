package Components_Middle;

import java.io.IOException;

import Framework.GeneralFilter;

public class InsertCourseMiddleFilter extends GeneralFilter {	// 기능코드 : 12345나 23456을 듣지 않은 학생들의 정보에 해당 과목을 추가하는 기능

	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read = 0;
		int checkBlank1 = 5;	// checkBlank default값을 Students.txt파일의 12345가 위치한 Blank값인 5로 설정한다.
		int checkBlank2 = 6;	// checkBlank default값을 Students.txt파일의 23456이 위치한 Blank값인 6로 설정한다.
		int databyte = 0;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		boolean isCourse1 = false;
		boolean isCourse2 = false;
		String num1 = "12345 ";
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
				// blank수와 buffer의 index수를 비교하여 byte별로 12345를 판단해 12345가 맞다면 isCourse1을 true로 설정한다.
				if (numOfBlank == checkBlank1 && buffer[idx - 6] == '1' && buffer[idx - 5] == '2'
						&& buffer[idx - 4] == '3' && buffer[idx - 3] == '4' && buffer[idx - 2] == '5') {
					isCourse1 = true;
				}
				// blank수와 buffer의 index수를 비교하여 byte별로 23456를 판단해 23456가 맞다면 isCourse2을 true로 설정한다.
				if (numOfBlank == checkBlank2 && buffer[idx - 6] == '2' && buffer[idx - 5] == '3'
						&& buffer[idx - 4] == '4' && buffer[idx - 3] == '5' && buffer[idx - 2] == '6') {
					isCourse2 = true;
				}
			}
			if (isCourse1 == true && isCourse2 == true) {	// 12345와 23456과목을 모두 수강했다면 그대로 buffer의 값을 out에 write한다.
				for (int i = 0; i < idx; i++) {
					out.write((char) buffer[i]);
				}
				isCourse1 = false;	// isCourse1을 false로 재설정한다.
				isCourse2 = false;	// isCourse2를 false로 재설정한다.
			}
			else if (isCourse1 == true) {	// 12345과목만 수강했다면 수강하지 않은 과목인 23456을 write한다.
				out.write(num2.getBytes());
				for (int i = 0; i < idx; i++) {	// buffer에 저장되어있는 정보는 그대로 write한다.
					out.write((char) buffer[i]);		
				}
				isCourse1 = false;	// isCourse1을 false로 재설정한다.
			}
			else if (isCourse2 == true) {	// 23456과목만 수강했다면 수강하지 않은 과목인 12345를 write한다.
				out.write(num1.getBytes());
				for (int i = 0; i < idx; i++) {	// buffer에 저장되어있는 정보는 그대로 write한다.
					out.write((char) buffer[i]);					
				}
				isCourse2 = false;	// isCourse2를 false로 재설정한다.
			}
			else {	// 12345와 23456과목을 모두 수강하지 않았다면 12345와 23456과목을 모두 write한다.
				out.write(num1.getBytes());
				out.write(num2.getBytes());
				for (int i = 0; i < idx; i++) {	// buffer에 저장되어있는 정보는 그대로 write한다.
					out.write((char) buffer[i]);
				}
				isCourse1 = false;	// isCourse1을 false로 재설정한다.
				isCourse2 = false;	// isCourse2를 false로 재설정한다.
			}
			if (byte_read == -1) {	// byte_read가 비어있으면(=다 읽었다면) return한다.
				return;
			}
			idx = 0;
			numOfBlank = 0;
			databyte = '\0';
		}
	}
}
