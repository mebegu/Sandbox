package utility;

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

	protected String[] read(int operationName){
		String[] lines = null;
		
		return lines;
	}
	
	protected void write(String[] lines){
		
	}
}
