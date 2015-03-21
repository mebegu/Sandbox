package utility;

public class InfoStorage{
	private String[] studentTXT;
	private String[] courseTXT;
	private String[] instructionsTXT;
	private static InfoStorage instance;
	
	public static InfoStorage getInstance(){
		if(instance == null)
			instance = new InfoStorage();
		return instance;
	}
	
	public static InfoStorage newInstance(){
		if(instance == null)
			instance = new InfoStorage();
		
		instance.courseTXT = FileIO.getInstance().read(FileIO.studentData);
		instance.courseTXT = FileIO.getInstance().read(FileIO.studentData);
		instance.instructionsTXT = FileIO.getInstance().read(FileIO.instructions);
		
		return instance;
	}

	private InfoStorage() {}

	public String[] getStudentTXT() {
		return studentTXT;
	}

	public String[] getCourseTXT() {
		return courseTXT;
	}

	public String[] getInstructionsTXT() {
		return instructionsTXT;
	}
	
	public void register(int courseID, int studentID, double grade){
		RegistrationList.getInstance().register(courseID, studentID, grade);
	}
	
	public double getGrade(int courseID, int studentID){
		return RegistrationList.getInstance().getGrade(courseID, studentID);
	}
	
	

}
