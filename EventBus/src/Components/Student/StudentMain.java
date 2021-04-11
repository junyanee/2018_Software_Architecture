package Components.Student;
/**
 * Copyright(c) 2018 All rights reserved by JU Consulting
 */

import Common.EventPackage.Event;
import Common.EventPackage.EventBusUtil;
import Common.EventPackage.EventId;
import Common.EventPackage.EventQueue;


/**
 * @author Jungho Kim
 * @date 2018
 * @version 1.1
 * @description
 */
public class StudentMain {
	public static void main(String args[]) {
		EventBusUtil eventBusInterface = new EventBusUtil();
		Event event = null;
	    EventQueue eventQueue = null;
	    boolean done = false;
	    StudentComponent studentsList = null;
	    
	    if(eventBusInterface.getComponentId() != -1) {
	    	System.out.println("StudentMain (ID:" + eventBusInterface.getComponentId() + ") is successfully registered...");
	    } else {
	    	System.out.println("StudentMain is failed to register. Restart this component.");
	    }
	    
	    try {
	    	studentsList = new StudentComponent("Students.txt");
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	    while(!done) {
            eventQueue = eventBusInterface.getEventQueue();
            
            for(int i = 0; i < eventQueue.getSize(); i++) {
                event = eventQueue.getEvent();
                System.out.println("Received an event(ID: " + event.getEventId() + ")...");
                
                if(event.getEventId() == EventId.ListStudents) {    
                    String returnString = "";
                    for (int j = 0; j < studentsList.vStudent.size(); j++) {
                        returnString += (j == 0 ? "" : "\n") + ((Student) studentsList.vStudent.get(j)).toString();
                    }
                    
                    System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
                    System.out.println("\n ** Message: \n" + returnString + "\n  ...");
                    eventBusInterface.sendEvent(new Event(EventId.ClientOutput, returnString));
                }else if(event.getEventId() == EventId.RegisterStudents){
            		String studentInfo = event.getMessage();
        			System.out.println("Not null");
        			Student student = new Student(studentInfo);
                	if(studentsList.isRegisteredStudent(student.studentId) == false){
                		studentsList.vStudent.add(new Student(studentInfo));
                		System.out.println("A new student is successfully added...");
                		System.out.println("\"" + studentInfo + "\"");
                	} else{
                		System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
                        System.out.println("\n ** Message: This student is already registered.  ...");
                        eventBusInterface.sendEvent(new Event(EventId.ClientOutput, "This student is already registered."));
                	}
            		
                }else if(event.getEventId() == EventId.QuitTheSystem) {
                    eventBusInterface.unRegister();
                    done = true;
                }
            }
        }
	    System.out.println("Shut down the component....");
	}
}
