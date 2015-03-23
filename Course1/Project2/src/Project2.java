import java.io.FileNotFoundException;

import controller.Controller;
import utility.FileIO;


public class Project2 {

	public static void main(String[] args) {
		
		FileIO.setInstance(FileIO.hashTableSize, args[0]);
		FileIO.setInstance(FileIO.instructions, args[1]);
		
		String[] instructions = null;
		
		try {
			instructions = FileIO.getInstance().read(FileIO.instructions);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<instructions.length; i++){
			System.out.println("i: "+i+" "+instructions[i]);
		}
		
		for(int i=0; i<instructions.length; i++){
			System.out.println(i);
			Controller.getInstance().eval(instructions[i]);
		}
		
	}

}
