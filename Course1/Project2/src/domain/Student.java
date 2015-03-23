package domain;
import java.security.InvalidParameterException;
import java.util.ArrayList;


public class Student{
	@Override
	public String toString() {
		return "Student [SID=" + SID + ", name=" + name + "]";
	}

	private int SID;
	private String name;
	private String surname;
	private ArrayList<Integer> registeredCourseIDs = new ArrayList<Integer>();


	public Student(int ID,String name, String surname) {
		setSID(ID);
		setName(name);
		setSurname(surname);
	}
	
	public boolean register(int courseID){
		if(!registeredCourseIDs.contains(courseID)){
			registeredCourseIDs.add(courseID);
			return true;
		}else{
			System.out.println("A student can only register to a course once.");
			return false;
		}
	}
	
	public boolean isRegisteredTo(int courseID){
		return registeredCourseIDs.contains(courseID);
	}
	
	public void unregister(int courseID){
		registeredCourseIDs.remove(courseID);
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

	public ArrayList<Integer> getRegisteredCourseIDs() {
		return registeredCourseIDs;
	}
	
}
