package controller;
public class Controller {
	
	private static Controller instance;
	
	public static Controller getInstance(){
		if(instance == null)
			instance = new Controller();
		return instance;
	}

	private Controller() {}
	
	public void eval(String line){
		if(line == null || line.equals(""))
			throw new RuntimeException("Instruction organization is wrong.");
		
		String[] operation = line.split(" ");
		
		if(!Character.isDigit(operation[0].charAt(0)))
			throw new RuntimeException("Instruction organization is wrong.");
		
		execute(operation);
		
			
	}
	
	

	private void execute(String[] operation) {
		switch (Character.getNumericValue(operation[0].charAt(0))){
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
			System.err.println("Operation category does not exits.");
			break;
	}
		
	}

	private void printGPA(String[] operation) {
		// TODO Auto-generated method stub
		
	}

	private void register(String[] operation) {
		// TODO Auto-generated method stub
		
	}

	private void delete(String[] operation) {
		// TODO Auto-generated method stub 
		
	}

	private void printAllCourses() {
		// TODO Auto-generated method stub
		
	}

	private void printAllStudents() {
		// TODO Auto-generated method stub
		
	}

	private void editCourse(String[] operation) {
		// TODO Auto-generated method stub
		
	}

	private void editStudent(String[] operation) {
		// TODO Auto-generated method stub
		
	}

	private void addCourses(String[] operation) {
		// TODO Auto-generated method stub
		
	}

	private void addStudents(String[] operation) {
		// TODO Auto-generated method stub
		
	}

}
