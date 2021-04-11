package Components_Middle;
import java.io.IOException;
import Framework.GeneralFilter;
public class EEMiddleFilter extends GeneralFilter { // 기능코드 : EE인 학생을 필터링해주는 기능
	@Override
	public void specificComputationForFilter() throws IOException {
		int byte_read=0;
		int checkBlank = 4;		// checkBlank default값을 Students.txt파일의 EE가 위치한 Blank값인 4로 설정한다.
		int databyte = 0;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[64];
		boolean isEE = false;
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
					// blank수와 buffer의 index수를 비교하여 'E'와 'E'를 판별해 EE가 맞다면 isEE를 true로 설정한다.
					if (numOfBlank == checkBlank && buffer[idx - 3] == 'E' && buffer[idx - 2] == 'E') {
						isEE = true;
					}
				}
				if (isEE == true) {		// isEE가 true라면 buffer값을 out에 write한다.
					for (int i = 0; i < idx; i++) {
						out.write((char) buffer[i]);
					}
					isEE = false;		// isEE를 false로 재설정한다.
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
