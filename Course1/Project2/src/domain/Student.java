package domain;
import java.security.InvalidParameterException;


/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 *
 * 
 */
public class Student{
	@Override
	public String toString() {
		return SID+" "+name+" "+surname;
	}

	/**
	 * Student Identification Number
	 */
	private int SID;
	/**
	 * Student Name
	 */
	private String name;
	/**
	 * Student Surname
	 */
	private String surname;


	/**
	 * Constructs a student object with given parameters.
	 * 
	 * @param ID Student Identification Number
	 * @param name Student Name
	 * @param surname Student Surname
	 */
	public Student(int ID,String name, String surname) {
		setSID(ID);
		setName(name);
		setSurname(surname);
	}

	/**
	 * @return Student Identification Number
	 */
	public int getSID() {
		return SID;
	}

	/**
	 * Sets Student Identification Number
	 * @param SID
	 */
	private void setSID(int SID) {
		if(1000 <= SID && SID <= 9999)
			this.SID = SID;
		else
			throw new InvalidParameterException("Student ID number should has four digits that starts with a non-zero number.");
	}

	/**
	 * @return Student Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets Student Name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Student Surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets Student Surname
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int hashCode(){
		return SID;
	}
	
}
