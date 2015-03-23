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
			System.err.println("Operation category does not exits.");
			break;
	}
		
	}

	private void printGPA(String[] operation) {
		if(operation.length != 2)
			throw new RuntimeException("Parse error!");
		
		int studentID = Integer.parseInt(operation[1]);
		double gpa = InfoStorage.getInstance().getGPA(studentID);
		
		String[] lines = new String[1];
		lines[0] = "GPA for SID = "+studentID+" : "+gpa+".";
		
		FileIO.getInstance().write(lines);
		
	}

	private void register(String[] operation) {
		if(operation.length != 4)
			throw new RuntimeException("Parse error!");
		
		int studentID = Integer.parseInt(operation[1]);
		int courseID = Integer.parseInt(operation[2]);
		double grade = Double.parseDouble(operation[3]);
		
		InfoStorage.getInstance().register(courseID, studentID, grade);
		
		String[] lines = new String[1];
		lines[0] = "Completed.";
		
		FileIO.getInstance().write(lines);
	}

	private void delete(String[] operation) {
		if(operation.length != 3)
			throw new RuntimeException("Parse error!");
		
		if(operation[1].equals("student"))
			InfoStorage.getInstance().deleteStudent(Integer.parseInt(operation[2]));
		else if(operation[1].equals("course"))
			InfoStorage.getInstance().deleteCourse(Integer.parseInt(operation[2]));
		else
			throw new RuntimeException("Parse error!");
	}

	private void printAllCourses() {
		Course[] courses = InfoStorage.getInstance().getAllCourses();
		
		String[] lines = new String[courses.length];
		
		for(int i = 0; i<courses.length; i++)
			lines[i] = courses[i].toString();
		
		
		FileIO.getInstance().write(lines);
	}

	private void printAllStudents() {
		Student[] students = InfoStorage.getInstance().getAllStudents();
		
		String[] lines = new String[students.length];
		
		for(int i = 0; i<students.length; i++)
			lines[i] = students[i].toString();
		
		
		FileIO.getInstance().write(lines);
		
	}

	private void editCourse(String[] operation) {
		if(operation.length != 5)
			throw new RuntimeException("Parse error!");
		
		int courseID = Integer.parseInt(operation[1]);
		String courseTitle = operation[2];
		int courseCredit = Integer.parseInt(operation[3]);
		String courseSemester = operation[4];
		
		InfoStorage.getInstance().editCourse(courseID, courseTitle, courseCredit, courseSemester);
		
		String[] lines = new String[1];
		lines[0] = "Completed.";
		
		FileIO.getInstance().write(lines);
		
	}

	private void editStudent(String[] operation) {
		if(operation.length != 4)
			throw new RuntimeException("Parse error!");
		
		int studentID = Integer.parseInt(operation[1]);
		String studentName = operation[2];
		String studentSurname = operation[3];
		
		InfoStorage.getInstance().editStudent(studentID, studentName, studentSurname);
		
		String[] lines = new String[1];
		lines[0] = "Completed.";
		
		FileIO.getInstance().write(lines);
		
	}

	private void addCourses(String[] operation) {
		if(operation.length != 2)
			throw new RuntimeException("Parse error!");
		
		String fileName = operation[1];
		
		FileIO.setInstance(FileIO.courseData, fileName);
		InfoStorage.getInstance().updateCourseList();
		
		String[] lines = new String[1];
		lines[0] = "Completed.";
		
		FileIO.getInstance().write(lines);
	}

	private void addStudents(String[] operation) {
		if(operation.length != 2)
			throw new RuntimeException("Parse error!");
		
		String fileName = operation[1];
		
		FileIO.setInstance(FileIO.studentData, fileName);
		InfoStorage.getInstance().updateStudentList();
		
		String[] lines = new String[1];
		lines[0] = "Completed.";
		
		FileIO.getInstance().write(lines);
		
	}

}
