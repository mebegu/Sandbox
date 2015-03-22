package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class FileIO {
	protected final static int instructions = 0;
	protected final static int hashTableSize= 1;
	protected final static int studentData= 2;
	protected final static int courseData= 3;
	private static String insFileName;
	private static String hSizeFileName;
	private static FileIO instance;
	
	protected static FileIO getInstance(){
		if(instance == null)
			instance = new FileIO();
		return instance;
	}
	
	protected static void setInstance(String insFileName, String hSizeFileName){
		FileIO.insFileName = insFileName;
		FileIO.hSizeFileName = hSizeFileName;
	}
	private FileIO() {}

	protected String[] read(int operationName) throws FileNotFoundException{
		ArrayList<String> lines = new ArrayList<String>();   
		String fileName = getFileName(operationName);
		
		Scanner scan = new Scanner(new File(fileName));
		
		while (scan.hasNextLine()){
			lines.add(scan.nextLine());
		}
		
		scan.close();	
		return (String[]) lines.toArray();
	}
	
	private String getFileName(int operationName) {
		if(insFileName == null && hSizeFileName == null)
			throw new RuntimeException("Instances were not setted.");
		
		
		switch (operationName){
			case 0:
				return insFileName;
			case 1:
				return hSizeFileName;
			case 2:
				return "students.txt";
			case 3:
				return "courses.txt";
			default:
				throw new RuntimeException("The requested operation for file reading does not exists.");	
			}
	}

	protected void write(String[] lines){
		
	}
}
