package domain;
import java.security.InvalidParameterException;


/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 *
 * 
 */
public class Course{

	@Override
	public String toString() {
		return CIN+" "+title+" "+credit+" "+semester;
		//return "Course ID: "+CIN+". Course Title: "+title+". Course Credit: "+credit+". Course Credit: "+semester+".";
	}

	/**
	 * Course Identification Number
	 */
	private int CIN;
	/**
	 * Course Title
	 */
	private String title;
	/**
	 * Course Semester
	 */
	private String semester;
	/**
	 * Course Credit
	 */
	private int credit;


	/**
	 * Constructs a course object with given parameters.
	 * 
	 * @param CIN Course Identification Number
	 * @param title Course Title
	 * @param credit Course Credit
	 * @param semester Course Semester
	 */
	public Course(int CIN, String title, int credit, String semester) {
		setCIN(CIN);
		this.title = title;
		this.semester = semester;
		this.credit = credit;
	}

	/**
	 * @return Course Credit
	 */
	public int getCredit() {
		return credit;
	}

	/**
	 * Sets Course Credit
	 * @param credit
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}

	/**
	 * Sets Course Title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets Course Semester
	 * @param semester
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * @return Course Identification Number
	 */
	public int getCIN() {
		return CIN;
	}


	/**
	 * Sets Course Identification Number
	 * @param CIN
	 */
	private void setCIN(int CIN) {
		if(100 <= CIN && CIN <= 999)
			this.CIN = CIN;
		else
			throw new InvalidParameterException("Course ID number should has three digit that starts with a non-zero number.");
	}

	/**
	 * @return Course Title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return Course Semester
	 */
	public String getSemester() {
		return semester;
	}
	
	public int hashCode(){
		return CIN;
	}

}
