package domain;
import java.security.InvalidParameterException;


public class Student{
	@Override
	public String toString() {
		return SID+" "+name+" "+surname;
	}

	private int SID;
	private String name;
	private String surname;


	public Student(int ID,String name, String surname) {
		setSID(ID);
		setName(name);
		setSurname(surname);
	}

	public int getSID() {
		return SID;
	}

	private void setSID(int SID) {
		if(1000 <= SID && SID <= 9999)
			this.SID = SID;
		else
			throw new InvalidParameterException("Student ID number should has four digits that starts with a non-zero number.");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int hashCode(){
		return SID;
	}
	
}
