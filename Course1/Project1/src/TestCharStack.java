import java.util.EmptyStackException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestCharStack {
	
	private CharStack memo;

	@BeforeClass
	public static void intro(){
		System.out.println("||||||||||||||||\nCharStack Tests\n||||||||||||||||\n");
	}
	
	@Before
	public void before(){
		System.out.println("++++++++++++\nTest Case Started");
		System.out.println("New stack initialized...");
		memo = new CharStack();
		memo.push('q');
		memo.printStack();
	}
	
	@After
	public void after(){
		System.out.println("\nTest Case Ended");
		System.out.println("Tested stack removed...\n############\n");
		memo = null;
	}

	
	@Test
	public void pushTest(){
		System.out.println("Push test: ");
		memo.push('b');
		memo.push('e');
		memo.push('r');
		memo.push('k');
		System.out.println("Pushing...\n\nResult:");
		memo.printStack();
	}
	
	@Test
	public void popTest(){
		System.out.println("Pop test:");
		char poppedChar = memo.pop();
		System.out.println("Popped Character: "+ poppedChar+"\n");
		memo.printStack();
	}
	
	@Test
	public void topTest(){
		System.out.println("Top test:");
		char topChar = memo.top();
		System.out.println("Top Character: "+ topChar+"\n");
		memo.printStack();
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
