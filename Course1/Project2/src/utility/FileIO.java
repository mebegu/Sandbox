package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 *
 * 
 */
public class FileIO {

	/**
	 * @return An instance of FileIO
	 */
	public static FileIO getInstance(){
		if(instance == null)
			instance = new FileIO();
		return instance;
	}

	private FileIO() { output = new ArrayList<String>(); } 

	/**
	 * Sets the file name of selected file type.
	 * 
	 * <code> 0 => Instructions file type. </code>
	 * <code> 1 => HashMapSize file type. </code>
	 * <code> 2 => Student file type. </code>
	 * <code> 3 => Course file type. </code>
	 * 
	 * @param fileType
	 * @param fileName
	 */
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
	
	/**

	 * Returns the file name of selected file type.
	 * 
	 * <code> 0 => Instructions file type. </code>
	 * <code> 1 => HashMapSize file type. </code>
	 * <code> 2 => Student file type. </code>
	 * <code> 3 => Course file type. </code>
	 * 
	 * @param fileType
	 * @return fileName
	 */
	private String getFileName(int fileType) {
		switch (fileType){
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


	/**
	 * Read the requested file type.
	 * <code> 0 => Instructions file type. </code>
	 * <code> 1 => HashMapSize file type. </code>
	 * <code> 2 => Student file type. </code>
	 * <code> 3 => Course file type. </code>
	 * 
	 * @param fileType
	 * @return Read string array.
	 * @throws FileNotFoundException If requested file type cannot be found on directory.
	 */
	public String[] read(int fileType) throws FileNotFoundException{
		ArrayList<String> txt = new ArrayList<String>();   
		String fileName = getFileName(fileType);
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

	/**
	 * Buffers the given array to memory.
	 * @param lines
	 */
	public void bufferOutput(String[] lines){
		
		for(int i=0; i<lines.length; i++)
			output.add(lines[i]);
		
		output.add("");
		
	}

	
	/**
	 * Writes buffered data to a output file.
	 * @throws IOException
	 */
	public void printOutput() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

		for(int i=0; i<output.size(); i++){
			writer.write(output.get(i));
			writer.newLine();
		}

		writer.close();
	}


	/**
	 * Output data that will be written later.
	 */
	private ArrayList<String> output;

	/**
	 * Private instance of FileIO
	 */
	private static FileIO instance;

	/**
	 * Instructions File Type
	 */
	public final static int instructions = 0;
	/**
	 * HashTableSize File Type
	 */
	public final static int hashTableSize= 1;
	/**
	 * StudentData File Type
	 */
	public final static int studentData= 2;
	/**
	 * CourseData File Type
	 */
	public final static int courseData= 3;

	/**
	 * File name of instructions.
	 */
	private static String insFileName;
	/**
	 * File name of HashTableSize File.
	 */
	private static String hSizeFileName;
	/**
	 * File name of StudentData File.
	 */
	private static String studentFileName;
	/**
	 * File name of CourseData File.
	 */
	private static String courseFileName;
}
