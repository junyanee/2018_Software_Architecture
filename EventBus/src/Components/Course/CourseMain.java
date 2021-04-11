package Components.Course;
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
public class CourseMain {
    public static void main(String[] args) {
        EventBusUtil eventBusInterface = new EventBusUtil();
        Event event = null;
        EventQueue eventQueue = null;
        boolean done = false;
        CourseComponent coursesList = null;
        
        if(eventBusInterface.getComponentId() != -1) {
	    	System.out.println("CourseMain (ID:" + eventBusInterface.getComponentId() + ") is successfully registered...");
	    } else {
	    	System.out.println("CourseMain is failed to register. Restart this component.");
	    }
	    
        try {
            coursesList = new CourseComponent("Courses.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        while(!done) {
            eventQueue = eventBusInterface.getEventQueue();
            
            for(int i = 0; i < eventQueue.getSize(); i++) {
                event = eventQueue.getEvent();
                System.out.println("Received an event(ID: " + event.getEventId() + ")...");
                
                if(event.getEventId() == EventId.ListCourses) {
                    String returnString = "";
                    for(int j = 0; j < coursesList.vCourse.size(); j++) {
                        returnString += ((Course) coursesList.vCourse.get(j)).toString() + "\n";
                    }
                    
                    System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
                    System.out.println("\n ** Message: \n" + returnString + "\n  ...");
                    eventBusInterface.sendEvent(new Event(EventId.ClientOutput, returnString));
                    
                } else if(event.getEventId() == EventId.RegisterCourses) {
                	String courseInfo = event.getMessage();
                	Course course = new Course(courseInfo);
                    if(coursesList.isRegisteredCourse(course.courseId) == false){
                        coursesList.vCourse.add(new Course(courseInfo));
                        System.out.println("A new course is successfully added...");
                		System.out.println("\"" + courseInfo + "\"");
                    } else{
                    	System.out.println("\n ** Sending an event(ID:" + EventId.ClientOutput + ") with");
                        System.out.println("\n ** Message: This course is already registered.  ...");
                        eventBusInterface.sendEvent(new Event(EventId.ClientOutput, "This course is already registered."));
                    }
                    
                } else if(event.getEventId() == EventId.QuitTheSystem) {
                    eventBusInterface.unRegister();
                    done = true;
                }
            }
        }     
        System.out.println("Shut down the component....");
    }
}
