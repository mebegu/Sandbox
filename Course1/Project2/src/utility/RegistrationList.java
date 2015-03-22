package utility;

import java.io.FileNotFoundException;

import domain.Course;
import domain.Student;



class RegistrationList {
	
	
	private RegistrationListNode [] memo;
	private int size;
	private static RegistrationList instance;
	
	protected static RegistrationList getInstance(){
		if(instance == null)
			instance = new RegistrationList();
		return instance;
	}

	private RegistrationList() {
		try {
			size = Integer.parseInt(FileIO.getInstance().read(FileIO.hashTableSize)[0]);
		} catch (NumberFormatException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		memo = new RegistrationListNode[size];
	}
	
	
	protected void register(int courseID, int studentID, double grade){
		int key = courseID % memo.length;

		int count = 0;
		
		while(memo[key] != null && count < memo.length) {
			key++;
			count++;
			key = key % memo.length;
		}
		
		if(count < memo.length){
			if(Student.getStudent(studentID).register(courseID) && Course.getCourse(courseID).register(studentID))
				memo[key] = new RegistrationListNode(courseID, studentID, grade);
		}
		else
			throw new RuntimeException("Registration list is full.");
	}
	
	protected double getGrade(int courseID, int studentID){
		int key = courseID % memo.length;
		int count = 0;
		
		while(memo[key].getCourseID() != courseID && memo[key].getStudentID() != studentID && count < memo.length) {
			key++;
			count++;
			key = key % memo.length;
		}
		
		if(count < memo.length)
			return memo[key].getGrade();
		else
			throw new RuntimeException("Course with such ID does not exists.");
	}
	

}
