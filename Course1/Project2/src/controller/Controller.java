package controller;

import domain.*;
import utility.*;

public class Controller {
	
	private static Controller instance;
	
	public static Controller getInstance(){
		if(instance == null)
			instance = new Controller();
		return instance;
	}

	private Controller() {}
	
	public void eval(String line){
		if(line == null || line.equals(""))
			throw new RuntimeException("Instruction organization is wrong.");
		
		String[] operation = line.split(" ");
		
		if(!Character.isDigit(operation[0].charAt(0)))
			throw new RuntimeException("Instruction organization is wrong.");
		
		execute(operation);
		
			
	}
	
	

	private void execute(String[] operation) {
		switch (Integer.parseInt(operation[0])){
		case 1:
			addStudents(operation);
			break;
		case 2:
			addCourses(operation);
			break;
		case 3:
			editStudent(operation);
			break;
		case 4:
			editCourse(operation);
			break;
		case 5:
			printAllStudents();
			break;
		case 6:
			printAllCourses();
			break;
		case 7:
			delete(operation);
			break;
		case 8:
			register(operation);
			break;
		case 9:
			printGPA(operation);
			break;
		default:
			System.err.println("Operation category does not exits. : "+operation[0]);
			break;
	}
		
	}

	private void printGPA(String[] operation) {
		if(operation.length != 2)
			throw new RuntimeException("Parse error!");

		int studentID = Integer.parseInt(operation[1]);
		double gpa = InfoStorage.getInstance().getGPA(studentID);

		String[] result = new String[1];

		switch ((int)gpa){
		case -1:
			result[0] = "GPA cannot be calculated. Student with ID: "+studentID+" does not exists";
			break;
		case -2:
			result[0] = "GPA cannot be calculated. Registration list does not contain the student with ID: "+studentID;
			break;
		case -3:
			result[0] = "GPA cannot be calculated. Student with ID: "+studentID+" was not registered to any course.";
			break;
		default:
			result[0] = "GPA for Student with ID "+studentID+" : "+gpa+".";
			break;
		}


		FileIO.getInstance().bufferOutput(result);

	}

	private void register(String[] operation) {
		if(operation.length != 4)
			throw new RuntimeException("Parse error!");
		
		int studentID = Integer.parseInt(operation[1]);
		int courseID = Integer.parseInt(operation[2]);
		double grade = Double.parseDouble(operation[3]);
		
		String[] result = new String[1];
		
		switch (InfoStorage.getInstance().register(courseID, studentID, grade)) {
		case -1:
			result[0] = "Registration is not completed. There is no such course with ID: "+courseID;
			break;
		case -2:
			result[0] = "Registration is not completed. There is no such student with ID: "+studentID;
			break;
		default:
			result[0] = "student with ID: "+studentID+", registered to course with ID: "+courseID+". Grade: "+grade;
			break;
		}

		
		FileIO.getInstance().bufferOutput(result);
	}

	private void delete(String[] operation) {
		if(operation.length != 3)
			throw new RuntimeException("Parse error!");
		
		String[] result = new String[1];
		
		if(operation[1].equals("student")){
			
			if(InfoStorage.getInstance().deleteStudent(Integer.parseInt(operation[2])))
				result[0] = "Student with ID: "+operation[2]+" is deleted.";
			else
				result[0] = "Deletion is not completed. Student with ID: "+operation[2]+" already does not exists";
			
		}else if(operation[1].equals("course")){
			
			if(InfoStorage.getInstance().deleteCourse(Integer.parseInt(operation[2])))
				result[0] = "Course with ID "+operation[2]+" is deleted.";
			else
				result[0] = "Deletion is not completed. Course with ID: "+operation[2]+" already does not exists";
			
		}else{
			throw new RuntimeException("Parse error!");
		}
		
		FileIO.getInstance().bufferOutput(result);
	}

	private void printAllCourses() {
		Course[] courses = InfoStorage.getInstance().getAllCourses();
		
		String[] result;
		
		switch (courses.length) {
		
		case 0:
			result = new String[1];
			result[0] = "There exists no course in system.";
			break;
			
		default:
			result = new String[courses.length];
			for(int i = 0; i<courses.length; i++)
				result[i] = courses[i].toString();
			break;
			
			
		}

		
		FileIO.getInstance().bufferOutput(result);
	}

	private void printAllStudents() {
		Student[] students = InfoStorage.getInstance().getAllStudents();
		
		String[] result;
		
		switch (students.length) {
		
		case 0:
			result = new String[1];
			result[0] = "There exists no student in system.";
			break;
			
		default:
			result = new String[students.length];
			for(int i = 0; i<students.length; i++)
				result[i] = students[i].toString();
			break;
			
			
		}
		
		
		FileIO.getInstance().bufferOutput(result);
		
	}

	private void editCourse(String[] operation) {
		if(operation.length != 5)
			throw new RuntimeException("Parse error!");
		
		int courseID = Integer.parseInt(operation[1]);
		String courseTitle = operation[2];
		int courseCredit = Integer.parseInt(operation[3]);
		String courseSemester = operation[4];
		
		if(InfoStorage.getInstance().editCourse(courseID, courseTitle, courseCredit, courseSemester))
		{
			String[] result = new String[1];
			result[0] = "Editing course with ID: "+operation[2]+" is Completed.";
			
			FileIO.getInstance().bufferOutput(result);
		}else{
			String[] result = new String[1];
			result[0] = "Editing is not completed. Course with ID: "+operation[2]+" does not exists";
			FileIO.getInstance().bufferOutput(result);
		}
	}

	private void editStudent(String[] operation) {
		if(operation.length != 4)
			throw new RuntimeException("Parse error!");
		
		int studentID = Integer.parseInt(operation[1]);
		String studentName = operation[2];
		String studentSurname = operation[3];
		
		if(InfoStorage.getInstance().editStudent(studentID, studentName, studentSurname))
		{
			String[] result = new String[1];
			result[0] = "Editing student with ID: "+operation[2]+" is completed.";
			
			FileIO.getInstance().bufferOutput(result);
		}else{
			String[] result = new String[1];
			result[0] = "Editing is not completed. Student with ID: "+operation[2]+" does not exists";
			FileIO.getInstance().bufferOutput(result);
		}
		
	}

	private void addCourses(String[] operation) {
		if(operation.length != 2)
			throw new RuntimeException("Parse error!");
		
		String fileName = operation[1];
		
		FileIO.setInstance(FileIO.courseData, fileName);
		InfoStorage.getInstance().loadCourseList();
		
		String[] result = new String[1];
		result[0] = "Course data from selected directory is proccessed into system.";
		
		FileIO.getInstance().bufferOutput(result);
	}

	private void addStudents(String[] operation) {
		if(operation.length != 2)
			throw new RuntimeException("Parse error!");
		
		String fileName = operation[1];
		
		FileIO.setInstance(FileIO.studentData, fileName);
		InfoStorage.getInstance().loadStudentList();
		
		String[] result = new String[1];
		result[0] = "Student data from selected directory is proccessed into system.";
		
		FileIO.getInstance().bufferOutput(result);
		
	}

}
