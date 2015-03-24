package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
	public final static int instructions = 0;
	public final static int hashTableSize= 1;
	public final static int studentData= 2;
	public final static int courseData= 3;
	
	private static String insFileName;
	private static String hSizeFileName;
	private static String studentFileName;
	private static String courseFileName;
	
	private static FileIO instance;
	
	public static FileIO getInstance(){
		if(instance == null)
			instance = new FileIO();
		return instance;
	}
	
	public static void setInstance(int fileType, String fileName){

		switch (fileType){
			case 0:
				insFileName = fileName;
				break;
			case 1:
				hSizeFileName = fileName;
				break;
			case 2:
				studentFileName = fileName;
				break;
			case 3:
				courseFileName = fileName;
				break;
			default:
				throw new RuntimeException("The requested file type for instance settings does not exists.");	
			}
	}
	
	
	private FileIO() {}

	public String[] read(int operationName) throws FileNotFoundException{
		ArrayList<String> txt = new ArrayList<String>();   
		String fileName = getFileName(operationName);
		if(fileName == null)
			throw new RuntimeException("Requested operation's instance was not setted.");
		
		Scanner scan = new Scanner(new File(fileName));
		
		while (scan.hasNextLine()){
			String line = scan.nextLine();
			if(!line.equals(""))
				txt.add(line);
		}
		scan.close();	
		String[] lines = new String[txt.size()];
		for(int i=0; i<txt.size(); i++){
			lines[i]= txt.get(i);
		}
		
		return lines;
	}
	
	private String getFileName(int operationName) {
		switch (operationName){
			case 0:
				return insFileName;
			case 1:
				return hSizeFileName;
			case 2:
				return studentFileName;
			case 3:
				return courseFileName;
			default:
				throw new RuntimeException("The requested operation for file reading does not exists.");	
			}
	}

	public void write(String[] lines){
		for(int i=0; i<lines.length; i++)
			System.out.println(lines[i]);
	}
}
