package utility;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import domain.*;

public class InfoStorage{

	private static InfoStorage instance;
	
	private HashList<HashList<Double>> registrationList;
	private HashList<Course> allCourses;
	private HashList<Student> allStudents;
	
	public static InfoStorage getInstance(){
		if(instance == null)
			instance = new InfoStorage();
		return instance;
	}
	

	private InfoStorage() {
		registrationList = new HashList<HashList<Double>>();
		allCourses = new HashList<Course>();
		allStudents = new HashList<Student>();
	}

	private void addStudent(Student student){
		allStudents.put(student.getSID(), student);
	}
	
	public Student[] getAllStudents(){
		Student[] sList = new Student[allStudents.toArrayList().size()];
		sList = allStudents.toArrayList().toArray(sList);
		return sList;
	}
	
	public void addCourse(Course course){
		allCourses.put(course.getCIN(), course);
	}
	
	
	public Course[] getAllCourses(){
		return allCourses.toArrayList().toArray(new Course[allCourses.toArrayList().size()]);
	}
	
	public void register(int courseID, int studentID, double grade){
		if(allStudents.getValue(studentID) != null && allStudents.getValue(studentID).register(courseID)){
			if(registrationList.getValue(studentID)== null){
				HashList<Double> courseList = new HashList<Double>();
				courseList.put(courseID, grade);
				registrationList.put(studentID, courseList);
			}else{
				registrationList.getValue(studentID).put(courseID, grade);
			}
		}
	}
	
	public double getGPA(int studentID){
		double gpa = 0;
		int totalCredit = 0;
		double totalGrade = 0;
		if(registrationList.getValue(studentID) == null)
			return -1;
		
		HashList<Double> registeredCourses = registrationList.getValue(studentID);
		ArrayList<Integer> registeredCourseIDs = allStudents.getValue(studentID).getRegisteredCourseIDs();
		
		for(int i=0; i<registeredCourseIDs.size(); i++ ){
			int courseID = registeredCourseIDs.get(i);
			double grade = registeredCourses.getValue(courseID);
			int credit = allCourses.getValue(courseID).getCredit();
			totalCredit += credit;
			totalGrade += (credit*grade);
		}
		
		gpa = totalGrade/totalCredit;
		
		return gpa;
	}

	public boolean editCourse(int courseID, String courseTitle, int courseCredit,
			String courseSemester) {
		
		if(allCourses.getValue(courseID) == null)
			return false;
		else{
			allCourses.getValue(courseID).setTitle(courseTitle);
			allCourses.getValue(courseID).setCredit(courseCredit);
			allCourses.getValue(courseID).setSemester(courseSemester);
			return true;
		}
	}

	public boolean editStudent(int studentID, String studentName,
			String studentSurname) {

		if(allStudents.getValue(studentID) == null)
			return false;
		else{
			allStudents.getValue(studentID).setName(studentName);
			allStudents.getValue(studentID).setSurname(studentSurname);
			return true;
		}
			
		
	}

	public void updateCourseList() {
		
		String[] courseTXT = null; 
		try {
			courseTXT = FileIO.getInstance().read(FileIO.courseData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<courseTXT.length; i++){
			String[] courseInfo = courseTXT[i].split(" ");
			addCourse(new Course(Integer.parseInt(courseInfo[0]), courseInfo[1], Integer.parseInt(courseInfo[2]), courseInfo[3]));
		}

	}

	public void updateStudentList() {
		String[] studentTXT = null; 
		try {
			studentTXT = FileIO.getInstance().read(FileIO.studentData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<studentTXT.length; i++){
			String[] studentInfo = studentTXT[i].split(" ");
			addStudent(new Student(Integer.parseInt(studentInfo[0]), studentInfo[1], studentInfo[2]));
		}
		
	}


	public void deleteStudent(int studentID) {
		allStudents.delete(studentID);
		System.err.println("allStudent: "+allStudents.getValue(studentID));
		registrationList.delete(studentID);
	}


	public void deleteCourse(int courseID) {
		allCourses.delete(courseID);
		Student[] allStudents = getAllStudents();
		for(int i=0; i<allStudents.length; i++){
			if(allStudents[i].isRegisteredTo(courseID)){
				registrationList.getValue(allStudents[i].getSID()).delete(courseID);
				this.allStudents.getValue(allStudents[i].getSID()).unregister(courseID);
			}
		}
	}


	public void printAllDatabase() {
		System.err.println("allStudents: "+allStudents);
		allStudents.printStructure();
		System.err.println("\n\nallCourses: "+allCourses);
		allCourses.printStructure();
		System.err.println("\n\nregistrationList: "+registrationList);
		registrationList.printStructure();
		
		for(int i=0; i<20; i++){
			if(registrationList.getValue(i)!=null){
				System.err.println("\nregistration list for student:" +i);
				registrationList.getValue(i).printStructure();
			}
		}
		
	}
	
	

}
