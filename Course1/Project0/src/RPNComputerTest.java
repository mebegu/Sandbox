import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class RPNComputerTest {
	private RPNComputer rpn;

	@BeforeClass
	public static void intro(){
		System.out.println("||||||||||||||||\nRPNComputer Tests\n||||||||||||||||\n");
	}
	
	@Before
	public void before(){
		rpn = RPNComputer.getInstance();
	}
	
	@After
	public void after(){
		rpn.clearMemory();
	}

	
	@Test
	public void sumTest(){
		System.out.println("Sum test: ");
		int result = rpn.compute("23+");
		System.out.println("[3+2] Result: "+result+"\n");
		assertEquals(result, 5);
	}
	
	@Test
	public void extractionTest(){
		System.out.println("Extraction test: ");
		int result = rpn.compute("23-");
		System.out.println("[3-2] Result: "+result+"\n");
		assertEquals(result, 1);
	}
	
	@Test
	public void multiplyTest(){
		System.out.println("Multiply test: ");
		int result = rpn.compute("23*");
		System.out.println("[3*2] Result: "+result+"\n");
		assertEquals(result, 6);
	}
	
	@Test
	public void divisionTest(){
		System.out.println("Division test: ");
		int result = rpn.compute("24/");
		System.out.println("[4/2] Result: "+result+"\n");
		assertEquals(result, 2);
	}
	
	@Test(expected = NullPointerException.class)
	public void emptyExpressionTest(){
		rpn.compute("");
	}
	
	@Test(expected = NullPointerException.class)
	public void nullExpressionTest(){
		String test = null;
		rpn.compute(test);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalExpressionTest(){
		rpn.compute("23>");
	}
	
	@Test(expected = RuntimeException.class)
	public void parseExceptionTest(){
		rpn.compute("253+");
	}
	
	@Test
	public void activity(){
		int result;
		System.out.println("Activity: ");
		result = rpn.compute("34+");
		System.out.println("[34+] [4+3]  Result: "+result);
		assertEquals(result, 7);
		
		result = rpn.compute("345*+");
		System.out.println("[345*+] [(5*4)+3]  Result: "+result);
		assertEquals(result, 23);
		
		result = rpn.compute("345+*");
		System.out.println("[345+*] [(5+4)*3]  Result: "+result);
		assertEquals(result, 27);
		
		result = rpn.compute("34+56+*");
		System.out.println("[34+56+*] [((3+4)*(5+6)]  Result: "+result);
		assertEquals(result, 77);
		
		result = rpn.compute("34+5+");
		System.out.println("[34+5+] [(3+4)+5]  Result: "+result+"\n");
		assertEquals(result, 12);
	}
}
