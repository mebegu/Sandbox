package domain;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import utility.InfoStorage;


public class Student {
	private int SID;
	private String name;
	private String surname;
	private ArrayList<Integer> registeredCourseIDs = new ArrayList<Integer>();


	public Student(int ID,String name, String surname) {
		setSID(ID);
		setName(name);
		setSurname(surname);
		addStudent(this);
	}
	
	public boolean register(int courseID){
		if(!registeredCourseIDs.contains(courseID)){
			registeredCourseIDs.add(courseID);
			return true;
		}else{
			System.out.println("A student can only register to a course once.");
			return false;
		}
	}

	public int getSID() {
		return SID;
	}

	private void setSID(int SID) {
		if(1000 <= SID && SID <= 9999)
			this.SID = SID;
		else
			throw new InvalidParameterException("Student ID number should has four digits that starts with a non-zero number.");
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	private void setSurname(String surname) {
		this.surname = surname;
	}
	
	public double calculateGPA(){
		double gpa = 0;
		int totalCredit = 0;
		double totalGrade = 0;
		
		for(int i=0; i<registeredCourseIDs.size(); i++ ){
			int courseID = registeredCourseIDs.get(i);
			double grade = InfoStorage.getInstance().getGrade(courseID, SID);
			int credit = Course.getCourse(courseID).getCredit();
			totalCredit += credit;
			totalGrade += (credit*grade);
		}
		
		gpa = totalGrade/totalCredit;
		
		return gpa;
	}
	
	
	private static Student[] allStudents;
	
	private static void addStudent(Student student){
		if(allStudents == null)
			allStudents = new Student[InfoStorage.getInstance().getStudentTXT().length*2];
		
		int key = student.getSID() % allStudents.length*2;
		int count = 0;
		
		while(allStudents[key] != null && count < allStudents.length*2) {
			key++;
			count++;
			key = key % allStudents.length*2;
		}
		
		if(count < allStudents.length*2)
			allStudents[key] = student;
		else
			throw new RuntimeException("Student list is full.");
	}
	
	public static Student getStudent(int ID){
		int key = ID % allStudents.length;
		int count = 0;
		
		while(allStudents[key].getSID() != ID && count < allStudents.length) {
			key++;
			count++;
			key = key % allStudents.length;
		}
		
		if(count < allStudents.length)
			return allStudents[key];
		else
			throw new RuntimeException("Student with such ID does not exists.");
	}
	
	public static Student[] getAllStudents(){
		return allStudents;
	}

}
