public class RPNComputer {

	private static RPNComputer instance;
	private CharStack memo;

	public static RPNComputer getInstance(){
		if(instance == null) instance = new RPNComputer();

		return instance;

	}

	private RPNComputer() {
		memo = new CharStack();
	}

	public void clearMemory(){
		memo = new CharStack();
	}

	public int compute(String expr) {
		if(expr == null || expr.equals("")) throw new NullPointerException();

		for(int i=0; i<expr.length(); i++){
			char current = expr.charAt(i);

			if(current == '+' || current == '-' || current == '*' || current == '/'){
				int first;
				int second;
				
				if(memo.isEmpty()) 
					first = 0;
				else{
					first = Character.getNumericValue(memo.top());
					if(first == -1)
						first = (int)memo.pop();
					else
						memo.pop();
				}
				
				if(memo.isEmpty()) 
					second = 0;
				else{
					second = Character.getNumericValue(memo.top());
					if(second == -1)
						second = (int)memo.pop();
					else
						memo.pop();
				}
				
				int result = doMath(first, second, current);
				
				char rToMemo = Character.toChars(result)[0];
				memo.push(rToMemo);
				
			}else if(Character.isDigit(current)){
				memo.push(current);
			}else{
				throw new IllegalArgumentException();
			}
		}

		if(memo.size() == 1) 
			return memo.pop();
		else{
				throw new RuntimeException("Invalid expression");
		}
	}

	private int doMath(int first, int second, char current) {
		switch (current) {
		case '+':
			return (first + second);
		case '-': 
			return (first - second);
		case '*':
			return (first * second);
		case '/':        
			return (first / second);
		default:
			return 0;
		}
	}

}
