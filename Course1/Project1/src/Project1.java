import java.io.IOException;
import logic.Maze;
import utility.FileIO;



/**
 * 
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 * 
 * 
 */
public class Project1 {

	
	/**
	 * Main method of the Project1 program.
	 * Reads a maze file from directory, creates a maze, 
	 * tries to solve the maze, then writes result to a file.
	 * 
	 * 
	 * @param args 
	 * <br>Arguments list that has the name of file which contains maze data.
	 * <br>If arguments list is empty, 
	 * program initializes its 0th element with the default string "maze.txt".
	 * 
	 * @throws IOException, if FileIO requires it.
	 * 
	 * 
	 */
	public static void main(String[] args)  {
		
		try {
			FileIO utility = FileIO.getInstance();
			
			if(args.length == 0)
				args = new String[]{"maze.txt"};
			
			int[][] rawMaze = utility.readMaze(args[0]);
			Maze myMaze = new Maze(rawMaze);
			rawMaze = myMaze.getSolution();
			utility.writeSolution(rawMaze);
		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
