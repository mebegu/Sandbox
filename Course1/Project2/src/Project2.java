
import java.io.IOException;

import controller.Controller;
import utility.FileIO;


/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 * <br>
 * <br>
 * <br><b>Student and Course Information System</b>
 * <br>
 * <br>Student information system is used to record and retrieve information on students and the
 * <br>courses. Each student has a unique student identification number (SIN), a first name, and a last
 * <br>name. SIN is a unique number for each student, and it consists of 4 digits, starting with a nonzero
 * <br>number.
 * <br>
 * <br>Similarly, for each course, there is a unique course identification number (CIN), title, number of
 * <br>credits, and the semester information. CIN is a unique number for each course, consisting of 3
 * <br>digits. First digit of CIN should be a non-zero number. Furthermore, there exists a registration
 * <br>list that records the student identification number, course identification number, and final grade
 * <br>of the student in this course.
 * <br>
 * <br>To use the system, an hashTableSize.txt file that specifies the capacity of hash maps on system,
 * <br>And an instructions.txt file that contains the instruction that wanted to processed 
 * <br>should be prepared.
 * <br>
 * <br>hashTableSize.txt's file name or directory should be given as first argument to program. (e.g. args[0])
 * <br>instructions.txt's file name or directory should be given as second argument to program. (e.g. args[1])
 * <br>
 * <br>This information system has the following functionalities:
 * <br>1) Register new student(s) 
 * <br> <code>Instruction format [1 fileName] (e.g. "1 students.txt")</code>
 * <br>2) Add new course(s)
 * <br> <code>Instruction format [2 fileName] (e.g. "1 courses.txt")</code>
 * <br>3) Edit student information
 * <br> <code>Instruction format [3 studentID name surname] (e.g. "3 1234 John Doe")</code>
 * <br>4) Edit course information
 * <br> <code>Instruction format [4 courseID title credit semester] (e.g. "4 321 COMP 3 SPRING15")</code>
 * <br>5) List all students
 * <br> <code>Instruction format [5] (e.g. "5")</code>
 * <br>6) List all courses
 * <br> <code>Instruction format [6] (e.g. "6")</code>
 * <br>7) Delete a student or a course
 * <br> <code>Instruction format [7 course/student courseID/studentID] (e.g. "7 student 1234")</code>
 * <br>8) Register a student to a course
 * <br> <code>Instruction format [8 studentID courseID grade] (e.g. "8 1234 321 3.5")</code>
 * <br>9) Calculate GPA score for a student
 * <br> <code>Instruction format [9 studentID] (e.g. "9 1234")</code>
 * 
 */

public class Project2 {

	
	/**
	 * <br>Main method of the Project2 program.
	 * <br>Takes a instructions file from directory as input, 
	 * <br>It processes the instructions, 
	 * <br>Then gives a output result.
	 * 
	 * @param args 
	 * <br>Arguments list that has the name of file which contains location of hash map size file and instructions file.
	 * <code>args[0] => The location of file that contain hash map size information.</code>
	 * <code>args[1] => The location of file that contain instructions.</code>
	 */
	public static void main(String[] args) {
		FileIO.setInstance(FileIO.hashTableSize, args[0]);
		FileIO.setInstance(FileIO.instructions, args[1]);

		String[] instructions = null;

		try {

			instructions = FileIO.getInstance().read(FileIO.instructions);

			for(int i=0; i<instructions.length; i++)
				Controller.getInstance().eval(instructions[i]);

			FileIO.getInstance().printOutput();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Program executed successfully");
		
	}

}
