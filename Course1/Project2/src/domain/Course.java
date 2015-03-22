package domain;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import utility.InfoStorage;


public class Course {
	
	private int CIN;
	private String title;
	private String semester;
	private int credit;
	private ArrayList<Integer> registeredStudentIDs = new ArrayList<Integer>();

	
	public Course(int CIN, int credit, String title, String semester) {
		setCIN(CIN);
		this.title = title;
		this.semester = semester;
		addCourse(this);
	}
	
	public boolean register(int studentID){
		if(!registeredStudentIDs.contains(studentID)){
			registeredStudentIDs.add(studentID);
			return true;
		}else{
			System.out.println("A course cannot have multiple same students.");
			return false;
		}
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getCIN() {
		return CIN;
	}
	
	
	private void setCIN(int CIN) {
		if(1000 <= CIN && CIN <= 9999)
			this.CIN = CIN;
		else
			throw new InvalidParameterException("Course ID number should has three digit that starts with a non-zero number.");
	}
	
	public String getTitle() {
		return title;
	}

	public String getSemester() {
		return semester;
	}
	
	
	private static Course[] allCourses;
	
	private static void addCourse(Course course){
		if(allCourses == null)
			allCourses = new Course[InfoStorage.getInstance().getCourseTXT().length*2];
		
		int key = course.getCIN() % allCourses.length;
		int count = 0;
		
		while(allCourses[key] != null && count < allCourses.length*2) {
			key++;
			count++;
			key = key % allCourses.length*2;
		}
		
		if(count < allCourses.length*2)
			allCourses[key] = course;
		else
			throw new RuntimeException("Course list is full.");
	}
	
	public static Course getCourse(int ID){
		int key = ID % allCourses.length;
		int count = 0;
		
		while(allCourses[key].getCIN() != ID && count < allCourses.length) {
			key++;
			count++;
			key = key % allCourses.length;
		}
		
		if(count < allCourses.length)
			return allCourses[key];
		else
			throw new RuntimeException("Course with such ID does not exists.");
	}
	
	public static Course[] getAllCourses(){
		return allCourses;
	}
	
	


}
