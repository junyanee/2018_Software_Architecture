package Component.Data;
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-07
 * @version 1.0
 * @description
 *      모든 Student들을 갖고 있는 리스트 클래스.
 */
public class StudentsList {
	protected ArrayList vStudent;
	
	/**
	 * Constructor. 모든 Student들의 정보를 갖고 있는 파일을 읽어들여 객체에 저장한다.
	 * 
	 * @param sStudentFileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public StudentsList(String sStudentFileName) throws FileNotFoundException, IOException {

	        BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));

	        this.vStudent = new ArrayList();

	        while (objStudentFile.ready()) {
	        	String stuInfo = objStudentFile.readLine();
	        	if(!stuInfo.equals("")){
	        		this.vStudent.add(new Student(stuInfo));
	        	}
	        }

	        objStudentFile.close();
	}
	
	/**
	 * 모든 Student들을 배열 타입으로 반환한다.
	 * @return
	 */
	public ArrayList getAllStudentRecords() {
        return this.vStudent;
    }
	
	/**
	 * 입력받은 ID 값을 갖고 있는 Student 객체가 존재하는지 확인한다.
	 * 
	 * @param sSID
	 * @return
	 */
	public boolean isRegisteredStudent(String sSID) {
        for (int i=0; i<this.vStudent.size(); i++) {
            Student objStudent = (Student) this.vStudent.get(i);
            if (objStudent.match(sSID)) {
                return true;
            }
        }
        return false;
    }
}
