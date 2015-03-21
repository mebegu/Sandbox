package utility;
import java.io.BufferedWriter;
import java.io.File;
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
	
	private static FileIO instance;
	private String lastFileName;
	
	/**
	 * @return An instance of FileIO
	 */
	public static FileIO getInstance(){
		if(instance == null)
			instance = new FileIO();
		
		return instance;
	}

	private FileIO() {}
	
	
	/**
	 * @param fileName Name of the file that has a maze's data.
	 * 
	 * @requires A text file (.txt), that contains maze data as follows;
	 * <br>-Rows should be consecutive.
	 * <br>-Columns should be placed with one empty space.
	 * <br>-End of the each lines must not be empty space.
	 * <br>-There must not be an empty line anywhere.
	 * <br>-Maze must be rectangular.
	 * <br>
	 * <br>Example:
	 * <br>0 1 0 0 1
	 * <br>0 1 1 1 9
	 * <br>0 0 0 0 0
	 * 
	 * 
	 * @return A two dimensional integer array that contains the maze's data.
	 * 
	 * @throws IOException, if file is not found, or file is empty.
	 * 
	 */
	public int[][] readMaze(String fileName) throws IOException{
		
		ArrayList<String> rows = new ArrayList<String>();    
		Scanner fileScanner = new Scanner(new File(fileName));
		
		lastFileName = fileName.replaceAll(".txt", "");
		while (fileScanner.hasNextLine()){
		   rows.add(fileScanner.nextLine());
		}
		
		if(rows.size() == 0){
			fileScanner.close();
			throw new IOException("Maze cannot be empty.");
		}
			
		int height = rows.size();
		int width = rows.get(0).length();
		
		int[][] rawMaze = new int[width/2+1][height];

		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				char rawNumber = rows.get(j).charAt(i);
				
				if(Character.isDigit(rawNumber))
					rawMaze[i/2][j] = Character.getNumericValue(rawNumber);
			}
		}
		
		fileScanner.close();
		
		
		return rawMaze;
	}
	

	/**
	 * <br>Writes the maze, that given as two dimensional integer array, to a text file.
	 * <br>If given maze is NULL, that means there is no solution for the maze. 
	 * <br>So, it creates a text files that tells there was no solution for the maze.
	 * <br>The written file's name is the last read file's name with "-sol" tag.
	 * 
	 * @param rawMaze A two dimensional integer array that contains maze's data.
	 * 
	 * @throws IOException, if file cannot be written.
	 * 
	 */
	public void writeSolution(int[][] rawMaze) throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter(lastFileName+"-sol.txt"));
		int[] finalPos = new int[2];
		if(rawMaze == null){
			
			out.write("No solution is available for the maze.");
			
		}else{

			for(int i=0; i<rawMaze[0].length; i++){
				for(int j=0; j<rawMaze.length; j++){
					if(rawMaze[j][i] == 9){
						finalPos[0] = j;
						finalPos[1] = i;
					}
					
					if(rawMaze[j][i] == -1)
						out.write("* ");
					else
						out.write(""+rawMaze[j][i]+" ");

				}
				out.newLine();
			}
			out.newLine();	
			out.newLine();
			out.write("The exit found at coordinate: "+"("+finalPos[0]+","+finalPos[1]+")");
		}

		out.close();
	}

}
