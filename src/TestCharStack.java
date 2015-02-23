import java.util.EmptyStackException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestCharStack {
	
	private CharStack memo;

	
	@Before
	public void before(){
		System.out.println("New stack initialized...");
		memo = new CharStack();
		memo.push('q');
		memo.printStack();
	}
	
	@After
	public void after(){
		System.out.println("Tested stack removed...");
		System.out.println("\n########################\n");
		memo = null;
	}

	
	@Test
	public void pushTest(){
		System.out.println("++\nPush test: \n++\n");
		memo.push('b');
		memo.push('e');
		memo.push('r');
		memo.push('k');
		memo.printStack();
	}
	
	@Test
	public void popTest(){
		System.out.println("++\nPop test: \n++\n");
		char poppedChar = memo.pop();
		memo.printStack();
		System.out.println("Popped Character: "+ poppedChar+"\n");
	}
	
	@Test
	public void topTest(){
		System.out.println("++\nTop test: \n++\n");
		char topChar = memo.top();
		memo.printStack();
		System.out.println("Top Character: "+ topChar+"\n");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void fullStackTest(){
		System.out.println("++\nFullStack test: \n++\n");
		memo.push('k');
		memo.push('g');
		memo.push('u');
		memo.push('r');
		memo.push('c');
		memo.push('a');
		memo.push('y');
	}
	
	@Test(expected = EmptyStackException.class)
	public void emptyStackTest(){
		System.out.println("++\nEmptyStack test: \n++\n");
		memo.pop();
		memo.pop();
	}
	
	

}
