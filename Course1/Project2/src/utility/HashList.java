package utility;

import java.io.FileNotFoundException;



class HashList <K>{
	
	
	private HashListNode<K>[] memo;
	private int size;

	@SuppressWarnings("unchecked")
	protected HashList() {
			try {
				size = Integer.parseInt(FileIO.getInstance().read(FileIO.hashTableSize)[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}


		memo = new HashListNode[size];
	}
	
	private int hash(int key){
		return key % memo.length;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected  void put(int key, K value){
		int hashKey = hash(key);
		int count = 0;
		
		while(memo[hashKey] != null && count < memo.length) {
			hashKey++;
			count++;
			hashKey = hashKey % memo.length;
		}
		
		if(count < memo.length){
			//if(Student.getStudent(studentID).register(courseID) && Course.getCourse(courseID).register(studentID))
			memo[key] = new HashListNode(key, value);
		}
		else
			throw new RuntimeException("Hash list is full.");
		
	}
	
	protected K getValue(int key){
		int hashKey = hash(key);
		int count = 0;
		
		while(memo[hashKey].getKey() != key && count < memo.length) {
			hashKey++;
			count++;
			hashKey = hashKey % memo.length;
		}
		
		if(count < memo.length){
			//if(Student.getStudent(studentID).register(courseID) && Course.getCourse(courseID).register(studentID))
			return memo[key].getValue();
		}
		else
			return null;
	}
	
	
	/*protected void register(int courseID, int studentID, double grade){
		int key = courseID % memo.length;

		int count = 0;
		
		while(memo[key] != null && count < memo.length) {
			key++;
			count++;
			key = key % memo.length;
		}
		
		if(count < memo.length){
			if(Student.getStudent(studentID).register(courseID) && Course.getCourse(courseID).register(studentID))
				memo[key] = new HashListNode(courseID, studentID, grade);
		}
		else
			throw new RuntimeException("Registration list is full.");
	}*/
	
	/*protected double getGrade(int courseID, int studentID){
		int key = courseID % memo.length;
		int count = 0;
		
		while(memo[key].getCourseID() != courseID && memo[key].getStudentID() != studentID && count < memo.length) {
			key++;
			count++;
			key = key % memo.length;
		}
		
		if(count < memo.length)
			return memo[key].getGrade();
		else
			throw new RuntimeException("Course with such ID does not exists.");
	}*/
	

}
