import java.util.EmptyStackException;


public class CharStack {
	private static final int capacity = 7;
	private char[] storage;
	private int pointer;

	public CharStack(){
		storage = new char[capacity];
		pointer = -1;
	}
	
	public boolean isEmpty(){
		if(pointer == -1) 
			return true;
		
		return false;
		
	}
	
	public int size(){
		return (pointer+1);
	}
	
	public char top(){
		if(isEmpty()) 
			throw new EmptyStackException();
			
		return storage[pointer];
	}
	
	public char pop(){
		if(isEmpty()) 
			throw new EmptyStackException();
		
		char temp = storage[pointer];
		storage[pointer] = '\u0000';
		pointer--;	
		return temp;
	}
	
	public void push(char inserted){
		if(size() == capacity) 
			throw new IndexOutOfBoundsException();
		
		pointer++;
		storage[pointer] = inserted;
	}
	
	public void printStack(){
		System.out.print("Stack Status\n-------\n");
		for(int i=0; i < storage.length ; i++){
			System.out.print(storage[i] +" ");
			
		}
		System.out.print("\n\n");
	}
	
}
