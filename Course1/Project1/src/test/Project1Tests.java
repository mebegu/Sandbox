package test;
import java.security.InvalidParameterException;

import logic.Maze;
import logic.Position;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * @author Mehmet Berk Gürçay <code>mehmetgurcay@ku.edu.tr</code>
 * 
 * 
 */
public class Project1Tests {
	
	@Rule
	public ExpectedException thrown= ExpectedException.none();


	@Test
	public void positionTest() {
		Position testPos = new Position(0, 5, 1);
		
		Assert.assertEquals(0, testPos.getX());
		Assert.assertEquals(5, testPos.getY());
		Assert.assertEquals(1, testPos.getStatus());
	}
	
	@Test
	public void positionFailTest() {
		thrown.expect(InvalidParameterException.class);
		thrown.expectMessage("Failed at position creation. INFO: x=0 y=5 status=3");
		
		new Position(0, 5, 3);
	}
	
	@Test
	public void mazeTest() {
		int[][] rawMaze = new int[3][1];
		
		rawMaze[0][0] = 0;
		rawMaze[1][0] = 1;
		rawMaze[2][0] = 9;
		
		Maze testMaze = new Maze(rawMaze);
		
		Assert.assertTrue(testMaze.isValid(0, 0));
		Assert.assertTrue(testMaze.isFinal(2, 0));
		Assert.assertTrue(testMaze.isValid(2, 0));
		
		Assert.assertFalse(testMaze.isValid(1, 0));
		
		Assert.assertFalse(testMaze.isValid(5, 5));
		Assert.assertFalse(testMaze.isFinal(0, 0));
		
		
	}
	
	@Test
	public void mazeFailTest() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("rawMaze cannot be null.");
		
		new Maze(null);
	}
	
	@Test
	public void solutionTest() {
		int[][] rawMaze = new int[][]{{0,0,0,0,1,1},
				                      {1,1,1,0,1,1},
				                      {0,0,1,0,1,9},
				                      {0,1,1,0,0,0}};
		
		int[][] solution = new int[][]{{-1,-1,-1,-1, 1, 1},
                					   { 1, 1, 1,-1, 1, 1},
                					   { 0, 0, 1,-1, 1, 9},
                					   { 0, 1, 1,-1,-1,-1}};
		
		Maze maze = new Maze(rawMaze);
		rawMaze = maze.getSolution();
		
		Assert.assertArrayEquals(solution, rawMaze);

	}
	
	@Test
	public void FailTest() {
		int[][] rawMaze = new int[][]{{0,0,0,0,1,1},
				                      {1,1,1,0,1,1},
				                      {0,0,1,0,1,9},
				                      {0,1,1,0,1,0}};
		
		Maze maze = new Maze(rawMaze);
		rawMaze = maze.getSolution();
		
		Assert.assertNull(rawMaze);
	}
	
	@Test
	public void emptyMazeTest() {
		thrown.expect(InvalidParameterException.class);
		thrown.expectMessage("Maze cannot be empty.");
		
		int[][] rawMaze = new int[][]{};
		new Maze(rawMaze);
	}
	
	
	@Test
	public void startIsWallTest() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Start position (0,0) cannot be wall.");
		
		int[][] rawMaze = new int[][]{{1,0,0,0,1,1},
				                      {1,1,1,0,1,1},
				                      {0,0,1,0,1,9},
				                      {0,1,1,0,0,0}};
		
		Maze maze = new Maze(rawMaze);
		rawMaze = maze.getSolution();
		
		Assert.assertNull(rawMaze);
	}
	
	@Test
	public void justHaveStartTest() {
		int[][] rawMaze = new int[][]{{0}};
		
		Maze maze = new Maze(rawMaze);
		rawMaze = maze.getSolution();
		
		Assert.assertNull(rawMaze);
	}
	
	@Test
	public void startIsEndTest() {
		int[][] rawMaze = new int[][]{{9,0,0,0,1,1},
				                      {1,1,1,0,1,1},
				                      {0,0,1,0,1,0},
				                      {0,1,1,0,0,0}};
		
		int[][] solution = new int[][]{{9,0,0,0,1,1},
									   {1,1,1,0,1,1},
									   {0,0,1,0,1,0},
									   {0,1,1,0,0,0}};
		
		Maze maze = new Maze(rawMaze);
		rawMaze = maze.getSolution();
		
		Assert.assertArrayEquals(solution, rawMaze);

	}
	
	@Test
	public void startIsSurroundedTest() {
		int[][] rawMaze = new int[][]{{0,1,0,0,1,1},
				                      {1,1,1,0,1,1},
				                      {0,0,1,0,1,9},
				                      {0,1,1,0,0,0}};
		
		Maze maze = new Maze(rawMaze);
		rawMaze = maze.getSolution();
		
		Assert.assertNull(rawMaze);

	}
	
	
	
	

}
