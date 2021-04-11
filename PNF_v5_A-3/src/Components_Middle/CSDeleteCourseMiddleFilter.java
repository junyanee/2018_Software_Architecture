package Components_Middle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import Framework.GeneralFilter;

public class CSDeleteCourseMiddleFilter extends GeneralFilter {		// 기능코드 : 17651이나 17652과목을 삭제하는 기능

	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read = 0;
		int databyte = 0;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		String byteToString = "";

		while (true) {
			while (databyte != '\n' && databyte != -1) {
				databyte = in.read();

				if (databyte == ' ') {		// databyte가 blank이면 numOfBlank를 증가시킨다.
					numOfBlank++;
				}
				if (databyte != -1) {		// databyte가 비어있지 않으면 buffer에 저장한다.
					buffer[idx++] = (byte) databyte;
				}
			}
			byteToString = new String(buffer, 0, idx);
			if (byteToString != null) {
				byteToString = byteToString.replaceAll("17651", "");	// 17651과목을 삭제한다.
				byteToString = byteToString.replaceAll("17652", "");	// 17652과목을 삭제한다.
				out.write(byteToString.getBytes());		// 17651과 17652과목이 삭제된 정보를 write한다.
			}
			if (byte_read == -1) {		// byte_read가 비어있으면(=다 읽었다면) return한다.
				return;
			}
			idx = 0;
			numOfBlank = 0;
			databyte = '\0';
		}
	}
}
