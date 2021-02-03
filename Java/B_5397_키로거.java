
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

//	2021-01-26
//	백준 5397번 키로거

public class B_5397_키로거 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i = 0; i < T; i++) {
			StringBuilder answer = new StringBuilder();
			String password = sc.next();
			Deque<String> left = new ArrayDeque<>();
			Deque<String> right = new ArrayDeque<>();
			
			for(int j = 0; j < password.length(); j++) {
				char index = password.charAt(j);
				if(index == '>') {
					if(right.size() != 0) {
						left.offerLast(right.pollFirst());
					}
				} else if(index == '<') {
					if(left.size() != 0) {
						right.offerFirst(left.pollLast());
					}
				} else if(index == '-') {
					if(left.size() != 0) {
						left.pollLast();
					}
				} else {
					left.offerLast(String.valueOf(index));
				}
			}
			for(String x : left) {
				answer.append(x);
			}
			for(String x : right) {
				answer.append(x);
			}
			System.out.println(answer.toString());
		}
		sc.close();
	}

}
