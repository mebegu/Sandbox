package utility;

import java.io.FileNotFoundException;
import java.util.Iterator;
import domain.*;

public class InfoStorage{

	private static InfoStorage instance;
	
	private HashList<Student, HashList<Course, Double>> registrationList;
	private HashList<Integer, Course> allCourses;
	private HashList<Integer, Student> allStudents;
	
	public static InfoStorage getInstance(){
		if(instance == null)
			instance = new InfoStorage();
		return instance;
	}
	

	private InfoStorage() {
		registrationList = new HashList<Student, HashList<Course, Double>>();
		allCourses = new HashList<Integer, Course>();
		allStudents = new HashList<Integer, Student>();
	}

	private void addStudent(Student student){
		allStudents.put(student.getSID(), student);
	}
	
	public Student[] getAllStudents(){
		Student[] sList = new Student[allStudents.size()];
		Iterator<Student> values = allStudents.values();

		
		int count = 0;
		
		while(values.hasNext()){
			sList[count] = values.next();
			count++;
		}
		
			
		return sList;
	}
	
	public void addCourse(Course course){
		allCourses.put(course.getCIN(), course);
	}
	
	
	public Course[] getAllCourses(){
		Course[] cList = new Course[allCourses.size()];
		Iterator<Course> values = allCourses.values();
		System.out.println("Size: "+allCourses.size());
		allCourses.printStructure();
		
		int count = 0;
		
		while(values.hasNext()){
			cList[count] = values.next();
			System.out.println("count: "+count+" value "+cList[count]);
			count++;
		}
			
		return cList;
	}
	
	public int register(int courseID, int studentID, double grade){
		
		if(allCourses.getValue(courseID) == null)
			return -1;
		
		if(allStudents.getValue(studentID) == null)
			return -2;
		
		if(registrationList.getValue(allStudents.getValue(studentID))== null){
			HashList<Course, Double> courseList = new HashList<Course, Double>();
			courseList.put(allCourses.getValue(courseID), grade);
			registrationList.put(allStudents.getValue(studentID), courseList);
		}else{
			registrationList.getValue(allStudents.getValue(studentID)).put(allCourses.getValue(courseID), grade);
		}
		
		return 0;
	}
	
	public double getGPA(int studentID){
		double gpa = 0;
		int totalCredit = 0;
		double totalGrade = 0;
		
		Student student = allStudents.getValue(studentID);
		
		if(student == null)
			return -1;
		
		HashList<Course, Double> registeredCourses = registrationList.getValue(allStudents.getValue(studentID));
		
		if(registeredCourses == null)
			return -2;
		
		
		Iterator<HashListNode<Course, Double>> iter = registeredCourses.entrySet();
		
		while(iter.hasNext()){
			HashListNode<Course, Double> pair = iter.next();
			double grade = pair.getValue();
			int credit = pair.getKey().getCredit();
			totalCredit += credit;
			totalGrade += (credit*grade);
		}
		
		if(totalCredit == 0)
			return -3;
		
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

	public void loadCourseList() {
		
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

	public void loadStudentList() {
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


	public boolean deleteStudent(int studentID) {
		if(allStudents.getValue(studentID) == null)
			return false;
		
		registrationList.remove(allStudents.getValue(studentID));
		allStudents.remove(studentID);
		return true;
	}


	public boolean deleteCourse(int courseID) {
		if(allCourses.getValue(courseID) == null)
			return false;
		
		Iterator<HashList<Course, Double>> iter = registrationList.values();
		
		while(iter.hasNext())
			iter.next().remove(allCourses.getValue(courseID));
		
		allCourses.remove(courseID);
		
		return true;
	}


	public void printAllDatabase() {
		System.err.println("allStudents: "+allStudents);
		allStudents.printStructure();
		System.err.println("\n\nallCourses: "+allCourses);
		allCourses.printStructure();
		System.err.println("\n\nregistrationList: "+registrationList);
		registrationList.printStructure();
		
	}
	
	

}
