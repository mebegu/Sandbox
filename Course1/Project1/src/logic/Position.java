package logic;
import java.security.InvalidParameterException;

/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 * 
 * 
 */
public class Position {
	
	private int x;
	private int y;
	private int status;
	private boolean visited;
	private boolean isPath;

	/**
	 * <br>Stores a position of maze.
	 * <br>Position: 0  =>  means position is open place.
	 * <br>Position: 1  =>  means position is wall.
	 * <br>Position: 9  =>  means position is end.
	 * 
	 * @param x coordinate of the position.
	 * @param y coordinate of the position.
	 * @param status status of the position.
	 * @throws InvalidParameterException, if x, or y is negative, or status is not 0, 1, or 9.
	 */
	public Position(int x, int y, int status) {
		if(x<0 || y<0 || (status!=0 && status!=1 && status!=9) )
			throw new InvalidParameterException("Failed at position creation. INFO: x="+x+" y="+y+" status="+status);
		
		this.x = x;
		this.y = y;
		this.status = status;
		visited = false;
		isPath = false;
	}
	
	/**
	 * Sets the position's path status.
	 * @param isPath
	 */
	public void setAsPath(boolean isPath){
		this.isPath = isPath;
	}
	
	/**
	 * @return True, if the position is part of a path.
	 */
	public boolean isPath(){
		return isPath;
	}
	
	/**
	 * Marks the position as visited.
	 */
	public void visit(){
		visited = true;
	}
	
	/**
	 * @return x coordinate of position
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * @return y coordinate of position
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * @return if the position was visited, returns true, otherwise, returns false.
	 */
	public boolean isVisited(){
		return visited;
	}
	
	/**
	 * @return Status of position.
	 */
	public int getStatus(){
		return status;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

	/**
	 * @return if the position is wall, returns true. Otherwise, returns false.
	 */
	public boolean isWall() {
		if(status == 1)
			return true;
		else
			return false;
	}
	
	

}
