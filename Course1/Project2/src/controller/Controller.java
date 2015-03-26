package controller;

import domain.*;
import utility.*;


/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 *
 * 
 */
public class Controller {

	/**
	 * private instance of Controller class.
	 */
	private static Controller instance;

	/**
	 * @return The instance of Controller
	 */
	public static Controller getInstance(){
		if(instance == null)
			instance = new Controller();
		return instance;
	}

	private Controller() {}


	/**
	 * Takes a instruction line as string and evaluates it, then converts it to a string array which be executed later.
	 * 
	 * @param line The instruction that will be evaluated.
	 */
	public void eval(String line){
		if(line == null || line.equals(""))
			throw new RuntimeException("Instruction organization is wrong.");

		String[] operation = line.split(" ");

		if(!Character.isDigit(operation[0].charAt(0)))
			throw new RuntimeException("Instruction organization is wrong.");

		execute(operation);


	}



	/**
	 * <br>Takes a instruction line as string array.
	 * <br>Sends the instruction to associated method.
	 * 
	 * @param operation The instruction that will be executed.
	 */
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

	/**
	 * <br>Takes a instruction line as string array.
	 * <br>Requests GPA of selected student, from InfoStorage. Buffers the result to FileIO.
	 * 
	 * @param operation The instruction that will be executed.
	 */
	private void printGPA(String[] operation) {
		if(operation.length != 2)
			throw new RuntimeException("Parse error!");

		int studentID = Integer.parseInt(operation[1]);
		double gpa = InfoStorage.getInstance().getGPA(studentID);

		String[] result = new String[1];

		switch ((int)gpa){
		case -1:
			//result[0] = "GPA cannot be calculated. Student with ID: "+studentID+" does not exists";
			result[0] = "No student with SID = "+studentID+"!";
			break;
		case -2:
			//result[0] = "GPA cannot be calculated. Registration list does not contain the student with ID: "+studentID;
			result[0] = "Registration list does not contain student with SID = "+studentID+"!";
			break;
		case -3:
			//result[0] = "GPA cannot be calculated. Student with ID: "+studentID+" was not registered to any course.";
			result[0] = "There is no course registration of student with SID = "+studentID+"!";
			break;
		default:
			result[0] = "GPA for SID = "+studentID+" : "+(Math.floor(gpa * 100) / 100)+".";
			break;
		}


		FileIO.getInstance().bufferOutput(result);

	}

	/**
	 * <br>Takes a instruction line as string array.
	 * <br>Request the registration of selected student to selected course with given grade, from InfoStorage. Buffers the result to FileIO.
	 * 
	 * @param operation The instruction that will be executed.
	 */
	private void register(String[] operation) {
		if(operation.length != 4)
			throw new RuntimeException("Parse error!");

		int studentID = Integer.parseInt(operation[1]);
		int courseID = Integer.parseInt(operation[2]);
		double grade = Double.parseDouble(operation[3]);

		String[] result = new String[1];

		switch (InfoStorage.getInstance().register(courseID, studentID, grade)) {
		case -1:
			//result[0] = "Registration is not completed. There is no such course with ID: "+courseID;
			result[0] = "No Course with CIN = "+courseID+"!";
			break;
		case -2:
			//result[0] = "Registration is not completed. There is no such student with ID: "+studentID;
			result[0] = "No student with SID = "+studentID+"!";
			break;
		default:
			//result[0] = "student with ID: "+studentID+", registered to course with ID: "+courseID+". Grade: "+grade;
			result[0] = "Completed.";
			break;
		}


		FileIO.getInstance().bufferOutput(result);
	}

	/**
	 * <br>Takes a instruction line as string array.
	 * <br>Request deletion of selected student or course, from InfoStorage. Buffers the result to FileIO.
	 * 
	 * @param operation The instruction that will be executed.
	 */
	private void delete(String[] operation) {
		if(operation.length != 3)
			throw new RuntimeException("Parse error!");

		String[] result = new String[1];

		if(operation[1].equals("student")){

			if(InfoStorage.getInstance().deleteStudent(Integer.parseInt(operation[2])))
				result[0] = "Completed.";
				//result[0] = "Student with ID: "+operation[2]+" is deleted.";
			else
				result[0] = "No student with SID = "+operation[2]+"!";
				//result[0] = "Deletion is not completed. Student with ID: "+operation[2]+" already does not exists";

		}else if(operation[1].equals("course")){

			if(InfoStorage.getInstance().deleteCourse(Integer.parseInt(operation[2])))
				result[0] = "Completed.";
				//result[0] = "Course with ID "+operation[2]+" is deleted.";
			else
				result[0] = "No course with CIN = "+operation[2]+"!";
				//result[0] = "Deletion is not completed. Course with ID: "+operation[2]+" already does not exists";

		}else{
			throw new RuntimeException("Parse error!");
		}

		FileIO.getInstance().bufferOutput(result);
	}


	/**
	 * <br>Request existed course array, from InfoStorage. Buffers the result to FileIO.
	 * 
	 */
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

	/**
	 * <br>Request existed student array, from InfoStorage. Buffers the result to FileIO.
	 */
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


	/**
	 * <br>Takes a instruction line as string array.
	 * <br>Request editing of selected course as given instruction, from InfoStorage. Buffers the result to FileIO.
	 * 
	 * @param operation The instruction that will be executed.
	 */
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
			//result[0] = "Editing course with ID: "+operation[2]+" is Completed.";
			result[0] = "Completed.";

			FileIO.getInstance().bufferOutput(result);
		}else{
			String[] result = new String[1];
			//result[0] = "Editing is not completed. Course with ID: "+operation[2]+" does not exists";
			result[0] = "No course with CIN = "+courseID+"!";
			
			FileIO.getInstance().bufferOutput(result);
		}
	}

	/**
	 * <br>Takes a instruction line as string array.
	 * <br>Request editing of selected student as given instruction, from InfoStorage. Buffers the result to FileIO.
	 * 
	 * @param operation The instruction that will be executed.
	 */
	private void editStudent(String[] operation) {
		if(operation.length != 4)
			throw new RuntimeException("Parse error!");

		int studentID = Integer.parseInt(operation[1]);
		String studentName = operation[2];
		String studentSurname = operation[3];

		if(InfoStorage.getInstance().editStudent(studentID, studentName, studentSurname))
		{
			String[] result = new String[1];
			//result[0] = "Editing student with ID: "+operation[2]+" is completed.";
			result[0] = "Completed.";

			FileIO.getInstance().bufferOutput(result);
		}else{
			String[] result = new String[1];
			//result[0] = "Editing is not completed. Student with ID: "+operation[2]+" does not exists";
			result[0] = "No student with SID = "+studentID+"!";
			
			FileIO.getInstance().bufferOutput(result);
		}

	}

	/**
	 * <br>Takes a instruction line as string array.
	 * <br>Request loading of course information from given location on directory, from InfoStorage. Buffers the result to FileIO.
	 * 
	 * @param operation The instruction that will be executed.
	 */
	private void addCourses(String[] operation) {
		if(operation.length != 2)
			throw new RuntimeException("Parse error!");

		String fileName = operation[1];

		FileIO.setInstance(FileIO.courseData, fileName);
		InfoStorage.getInstance().loadCourseList();

		String[] result = new String[1];
		//result[0] = "Course data from selected directory is proccessed into system.";
		result[0] = "Completed.";
		
		FileIO.getInstance().bufferOutput(result);
	}

	/**
	 * <br>Takes a instruction line as string array.
	 * <br>Request loading of student information from given location on directory, from InfoStorage. Buffers the result to FileIO.
	 * 
	 * @param operation The instruction that will be executed.
	 */
	private void addStudents(String[] operation) {
		if(operation.length != 2)
			throw new RuntimeException("Parse error!");

		String fileName = operation[1];

		FileIO.setInstance(FileIO.studentData, fileName);
		InfoStorage.getInstance().loadStudentList();

		String[] result = new String[1];
		//result[0] = "Student data from selected directory is proccessed into system.";
		result[0] = "Completed.";

		FileIO.getInstance().bufferOutput(result);

	}

}
