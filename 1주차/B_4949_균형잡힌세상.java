import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class B_4949_균형잡힌세상 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(
						new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack <Character> bracket = new Stack<>();
		
		while(true) {
			String result;
			boolean possible = true;
			String santance = br.readLine();
			bracket.push('1');
			
			if(santance.equals(".")) {
				break;
			}
			for(int i = 0; i < santance.length(); i++) {
				char c = santance.charAt(i);
				if(c == '(') {
					bracket.push(c);
				} else if(c == ')') {
					if(bracket.pop() != '(') {
						possible = false;
						break;
					}
				} else if(c == '[') {
					bracket.push(c);
				} else if(c == ']') {
					if(bracket.pop() != '[') {
						possible = false;
						break;
					}
				} else {
					continue;
				}
			}
			
			if(bracket.size() == 1 && possible) {
				result = "yes";
			} else {
				result = "no";
			}
			bracket.clear();
			System.out.println(result);
		}
		
	}

}
