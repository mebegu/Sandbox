package utility;

import java.io.FileNotFoundException;
import java.util.Iterator;
import domain.*;

/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 *
 * 
 */
public class InfoStorage{

	/**
	 * private instance of InfoStorage class.
	 */
	private static InfoStorage instance;

	/**
	 * Two level Hash Map that contains registration information.
	 */
	private HashList<Student, HashList<Course, Double>> registrationList;
	/**
	 * Hash Map that contains all courses's information on the system.
	 */
	private HashList<Integer, Course> allCourses;
	/**
	 * Hash Map that contains all courses's information on the system.
	 */
	private HashList<Integer, Student> allStudents;

	/**
	 * @return The instance of InfoStorage
	 */
	public static InfoStorage getInstance(){
		if(instance == null)
			instance = new InfoStorage();
		return instance;
	}


	/**
	 * Constructs a InfoStorage object with given parameters.
	 * Initializes the Hash Maps.
	 */
	private InfoStorage() {
		registrationList = new HashList<Student, HashList<Course, Double>>();
		allCourses = new HashList<Integer, Course>();
		allStudents = new HashList<Integer, Student>();
	}


	/**
	 * Adds given student to allStudents Hash Map.
	 * @param student
	 */
	private void addStudent(Student student){
		allStudents.put(student.getSID(), student);
	}

	/**
	 * @return allStudents Hash List's values as array.
	 */
	public Student[] getAllStudents(){
		Student[] sList = new Student[allStudents.size()];
		Iterator<Student> values = allStudents.values();

		int count = 0;
		while(values.hasNext())
			sList[count++] = values.next();

		return sList;
	}

	/**
	 * Adds given course to allCourses Hash Map.
	 * @param course
	 */
	private void addCourse(Course course){
		allCourses.put(course.getCIN(), course);
	}


	/**
	 * @return allCourses Hash List's values as array.
	 */
	public Course[] getAllCourses(){
		Course[] cList = new Course[allCourses.size()];
		Iterator<Course> values = allCourses.values();

		int count = 0;
		while(values.hasNext())
			cList[count++] = values.next();

		return cList;
	}

	/**
	 * Registers given student to given course with given grade.
	 * @param courseID
	 * @param studentID
	 * @param grade
	 * @return Result of operation.
	 * 
	 * <code> return value meanings</code>
	 * <code> -1 => There exists no such course with given courseID </code>
	 * <code> -2 => There exists no such student with given studentID </code>
	 * <code> 0 => Registration successful </code>
	 */
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

	/**
	 * Returns
	 * @param studentID
	 * @return Result of operation.
	 * 
	 * <code> return value meanings</code>
	 * <code> -1 => There exists no such student with given studentID </code>
	 * <code> -2 => There exists no such student with given studentID on the registration list </code>
	 * <code> -3 => There exists no registered course for such student with given studentID </code>
	 * <code> Otherwise => GPA of student </code>
	 */
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

	/**
	 * Edits the course with given ID. Title, Credit, Semester will be edited.
	 * @param courseID
	 * @param courseTitle
	 * @param courseCredit
	 * @param courseSemester
	 * @return True, if successful. If course does not exist, returns false.
	 */
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

	/**
	 * Edits the course with given ID. Title, Credit, Semester will be edited.
	 * @param studentID
	 * @param studentName
	 * @param studentSurname
	 * @return True, if successful. If student does not exist, returns false.
	 */
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

	/**
	 * Loads courses data from directory.
	 */
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

	/**
	 * Loads students data from directory.
	 */
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


	/**
	 * Removes the student with given ID.
	 * @param studentID
	 * @return True, if successful. If student does not exist, returns false.
	 */
	public boolean deleteStudent(int studentID) {
		if(allStudents.getValue(studentID) == null)
			return false;

		registrationList.remove(allStudents.getValue(studentID));
		allStudents.remove(studentID);
		return true;
	}


	/**
	 * Removes the course with given ID.
	 * @param courseID
	 * @return True, if successful. If course does not exist, returns false.
	 */
	public boolean deleteCourse(int courseID) {
		if(allCourses.getValue(courseID) == null)
			return false;

		Iterator<HashList<Course, Double>> iter = registrationList.values();

		while(iter.hasNext())
			iter.next().remove(allCourses.getValue(courseID));

		allCourses.remove(courseID);

		return true;
	}


	/**
	 * Prints the structure of fundamental Hash Maps of InfoStructure onto console.
	 */
	public void printAllStorage() {
		System.err.println("allStudents: "+allStudents);
		allStudents.printStructure();
		System.err.println("\n\nallCourses: "+allCourses);
		allCourses.printStructure();
		System.err.println("\n\nregistrationList: "+registrationList);
		registrationList.printStructure();

	}



}
