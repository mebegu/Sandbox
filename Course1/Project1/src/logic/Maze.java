package logic;
import java.security.InvalidParameterException;
import java.util.Stack;




/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 * 
 * 
 */
public class Maze {
	
	private Position[][] maze;
	private int width;
	private int height;
	private Stack<Position> memo;
	private boolean isSolved;

	/**
	 * Takes an two dimensional integer array, 
	 * then converts it to a two dimensional Position array.
	 * 
	 * 
	 * @param rawMaze Two dimensional integer array.
	 * 
	 * @requires Non-Null and Non-Empty rawMaze Array.
	 * @requires Every element of rawMaze must be 0, 1, or 9.
	 * 
	 */
	public Maze(int[][] rawMaze) {
		
		if(rawMaze == null)
			throw new NullPointerException("rawMaze cannot be null.");
		
		if(rawMaze.length == 0)
			throw new InvalidParameterException("Maze cannot be empty.");
		
		if(rawMaze[0].length == 0)
			throw new InvalidParameterException("Maze cannot be empty.");
		
		
		
		width = rawMaze.length;
		height = rawMaze[0].length;
		
		maze = new Position[width][height];
		
		memo = new Stack<Position>();
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				maze[i][j] = new Position(i, j, rawMaze[i][j]);
			}
		}
		
		
	}
	
	@Override
	public String toString() {
		return "Maze [width=" + width + ", height=" + height + "]";
	}
	

	/**
	 * @param x coordinate of a position
	 * @param y coordinate of a position
	 * 
	 * @return if the given position can be reachable and was not visited before, returns true. Otherwise, returns false.
	 */
	public boolean isValid(int x, int y){
		if(x<0 || y<0 || x>=width || y>=height || maze[x][y].isVisited() || maze[x][y].isWall())
			return false;
		else
			return true;
	}
	
	/**
	 * @param x coordinate of a position
	 * @param y coordinate of a position
	 * 
	 * @return if the given position is exit of maze, returns true. Otherwise, returns false.
	 */
	public boolean isFinal(int x, int y){
		if(isValid(x, y))
			return (maze[x][y].getStatus() == 9);
		else
			return false;
	}
	

	/**
	 * 
	 * <br>Starts a loop to find the end of maze. 
	 * <br>Pops the top element of {@code Stack<Position> memo}, then checks if the popped position is exit of maze.
	 * <br>
	 * <br>If the finish position is reached, breaks the loop, and prints a info message.
	 * <br>
	 * <br>Otherwise, checks the consecutive neighbors of popped positions, and pushes valid positions to {@code Stack<Position> memo}. 
	 * <br>Then repeats the loop.
	 * <br>
	 * <br>If {@code Stack<Position> memo} becomes empty at the end of a loop, that means there is no solution.
	 * <br>So, breaks the loop, and prints a info message.
	 * 
	 * @requires Start position (0,0) cannot be wall.
	 * 
	 * @throws RuntimeException, If start position is wall.
	 * 
	 */
	private void solveMaze(){
		
		isSolved = false;
		memo.push(maze[0][0]);
		
		if(memo.peek().isWall()){
			throw new RuntimeException("Start position (0,0) cannot be wall.");
		}
			
		
		while(!memo.isEmpty() && !isSolved){
			Position curr = memo.peek();
			
			if(isFinal(curr.getX(), curr.getY())){
				isSolved = true;
			}else{
				
				maze[curr.getX()][curr.getY()].visit();
				maze[curr.getX()][curr.getY()].setAsPath(true);
				
				if(isDeadEnd(curr.getX(), curr.getY())){
					maze[curr.getX()][curr.getY()].setAsPath(false);
					memo.pop();
				}else{
					check(curr.getX()+1, curr.getY());
					check(curr.getX(), curr.getY()+1);
					check(curr.getX()-1, curr.getY());
					check(curr.getX(), curr.getY()-1);
				}
				
				
			}
		}
		
		if(isSolved)
			System.out.println("Maze is solved.");
		else
			System.out.println("No solution available.");
		
	}
	
	
	/**
	 * Checks a position, if the position is valid, and pushes it to {@code Stack<Position> memo}.
	 * 
	 * @param x coordinate of a position
	 * @param y coordinate of a position
	 */
	private void check(int x, int y){
		if(isValid(x,y))
			memo.push(maze[x][y]);
	}
	
	private boolean isDeadEnd(int x, int y){
		if(!isValid(x+1,y) && !isValid(x-1,y) && !isValid(x,y+1) && !isValid(x,y-1))
			return true;
		else
			return false;
	}
	
	
	
	/**
	 * Starts the solving procedure of maze.
	 * Then converts two dimensional Position array to a two dimensional integer array.
	 * 
	 * @return If solution was found, returns the result maze array as two dimensional integer array. The path is marked as -1.
	 * @return If solution was not found, returns NULL. 
	 */
	public int[][] getSolution(){
		int[][] result = new int[width][height];
		
		solveMaze();
		
		if(!isSolved)
			return null;
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				if(maze[i][j].isPath())
					result[i][j] = -1;
				else
					result[i][j] = maze[i][j].getStatus();
			}
		}
		
		return result;
	}
	
	
	

}
