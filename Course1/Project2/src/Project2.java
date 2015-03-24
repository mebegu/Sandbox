import java.io.FileNotFoundException;

import controller.Controller;
import utility.FileIO;


public class Project2 {

	public static void main(String[] args) {
		
		//FileIO.setInstance(FileIO.hashTableSize, args[0]);
		//FileIO.setInstance(FileIO.instructions, args[1]);
		
		FileIO.setInstance(FileIO.hashTableSize, "hashSize.txt");
		FileIO.setInstance(FileIO.instructions, "myInstructions.txt");
		
		String[] instructions = null;
		
		try {
			instructions = FileIO.getInstance().read(FileIO.instructions);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<instructions.length; i++){
			//System.out.println("i: "+(i+1)+" "+instructions[i]);
		}
		
		for(int i=0; i<instructions.length; i++){
			//System.out.println("At operation: "+(i+1));
			Controller.getInstance().eval(instructions[i]);
		}
		
		//InfoStorage.getInstance().printAllDatabase();
		
	}

}
