package Components_Middle;

import java.io.IOException;
import java.util.StringTokenizer;

import Framework.GeneralFilter;

public class InsertCourseMiddleFilter extends GeneralFilter {

	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read = 0;
		int checkBlank1 = 5;
		int checkBlank2 = 6;
		int databyte = 0;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		boolean isCourse1 = false;
		boolean isCourse2 = false;

		while (true) {
			String str1 = "12345";
			String str2 = "23456";
			byte[] buffer1 = str1.getBytes();
			byte[] buffer2 = str2.getBytes();
			while (databyte != '\n' && databyte != -1) {
				databyte = in.read();
				
				if (databyte == ' ') {
					numOfBlank++;
				}
				if (databyte != -1) {
					buffer[idx++] = (byte) databyte;
				}
				if (numOfBlank == checkBlank1 && buffer[idx - 6] == '1' && buffer[idx - 5] == '2'
						&& buffer[idx - 4] == '3' && buffer[idx - 3] == '4'
						&& buffer[idx - 2] == '5') {
					isCourse1 = true;
				}
				if (numOfBlank == checkBlank2 && buffer[idx - 6] == '2' && buffer[idx - 5] == '3'
						 && buffer[idx - 4] == '4' && buffer[idx - 3] == '5'
						 && buffer[idx - 2] == '6') {
					isCourse2 = true;
				}
			}
			if (isCourse1 == true && isCourse2 == true) {
				for (int i = 0; i < idx; i++) {
					out.write((char) buffer[i]);
				}
				isCourse1 = false;
				isCourse2 = false;
			}
			if (isCourse1 == false && isCourse2 == true) {
				for (int i = 0; i < idx; i++) {
					out.write(buffer1);
				}
				out.close();
				isCourse1 = false;
				isCourse2 = false;
			}
			if (isCourse1 == true && isCourse2 == false) {
				for (int i = 0; i < idx; i++) {
					out.write(buffer2);
					
				}
				out.close();
				isCourse1 = false;
				isCourse2 = false;
			}
			if (isCourse1 == false && isCourse2 == false) {
				for (int i = 0; i < idx; i++) {
					out.write(buffer1);
					out.write(buffer2);
					
				}
				out.close();
				isCourse1 = false;
				isCourse2 = false;
			}
			if (byte_read == -1) {
				return;
			}
			idx = 0;
			numOfBlank = 0;
			databyte = '\0';
		}
	}

}
