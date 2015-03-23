package domain;
import java.security.InvalidParameterException;


public class Course{
	
	@Override
	public String toString() {
		return "Course [CIN=" + CIN + ", title=" + title + "]";
	}

	private int CIN;
	private String title;
	private String semester;
	private int credit;

	
	public Course(int CIN, String title, int credit, String semester) {
		setCIN(CIN);
		this.title = title;
		this.semester = semester;
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
		if(100 <= CIN && CIN <= 999)
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
	
}
