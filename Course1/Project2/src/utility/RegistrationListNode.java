package utility;
import java.security.InvalidParameterException;


class RegistrationListNode {
	private int studentID;
	private int courseID;
	private double grade;

	protected RegistrationListNode(int courseID, int studentID, double grade) {
		this.studentID = studentID;
		this.courseID = courseID;
		setGrade(grade);
	}

	protected double getGrade() {
		return grade;
	}

	private void setGrade(double grade) {
		if(0 <= grade && grade <= 4)
			this.grade = grade;
		else
			throw new InvalidParameterException("Grade can only be between 0 and 4");
	}

	protected int getStudentID() {
		return studentID;
	}

	protected int getCourseID() {
		return courseID;
	}

}
