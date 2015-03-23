package utility;

import java.io.FileNotFoundException;
import domain.*;

public class InfoStorage{
	private String[] studentTXT;
	private String[] courseTXT;
	private String[] instructionsTXT;
	private static InfoStorage instance;
	
	private HashList<HashList<Double>> registrationList;
	private HashList<Course> allCourses;
	private HashList<Student> allStudents;
	
	public static InfoStorage getInstance(){
		if(instance == null)
			instance = new InfoStorage();
		return instance;
	}
	
	public static InfoStorage newInstance(){
		if(instance == null)
			instance = new InfoStorage();
		
		try {
			instance.studentTXT = FileIO.getInstance().read(FileIO.studentData);
			instance.courseTXT = FileIO.getInstance().read(FileIO.courseData);
			instance.instructionsTXT = FileIO.getInstance().read(FileIO.instructions);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return instance;
	}

	private InfoStorage() {
		registrationList = new HashList<HashList<Double>>();
		allCourses = new HashList<Course>();
		allStudents = new HashList<Student>();
	}

	public String[] getStudentTXT() {
		return studentTXT;
	}

	public String[] getCourseTXT() {
		return courseTXT;
	}

	public String[] getInstructionsTXT() {
		return instructionsTXT;
	}
	
	public void addStudent(Student student){
		allStudents.put(student.getSID(), student);
	}
	
	public void addCourse(Course course){
		allCourses.put(course.getCIN(), course);
	}
	
	public void register(int courseID, int studentID, double grade){
		if(registrationList.getValue(courseID)== null){
			HashList<Double> classList = new HashList<Double>();
			classList.put(studentID, grade);
			registrationList.put(courseID, classList);
		}else{
			registrationList.getValue(courseID).put(studentID, grade);
		}
		
	}
	
	public double getGrade(int courseID, int studentID){
		if(registrationList.getValue(courseID)== null){
			return registrationList.getValue(courseID).getValue(studentID);
		}else{
			throw new NullPointerException();
		}
	}
	
	

}
