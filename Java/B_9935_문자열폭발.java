import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B_9935_문자열폭발{

	static String text, pattern;
	static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		text = br.readLine();
		pattern = br.readLine();

		for (int i = 0; i < text.length(); i++) {
			stack.add(text.charAt(i));
			while (stack.size() >= pattern.length() && check()) {
				for (int j = 0; j < pattern.length(); j++) {
					stack.pop();
				}
			}
		}
		if (stack.size() == 0) {
			sb.append("FRULA");
		} else {
			for (Character c : stack)
				sb.append(c);
		}
		System.out.println(sb);
	}

	static boolean check() {
		int len = stack.size();
		int plen = pattern.length();
		for (int i = 0; i < plen; i++) {
			if (stack.get(len - plen + i) != pattern.charAt(i)) {
				return false;
			}
		}
		return true;
	}
}
