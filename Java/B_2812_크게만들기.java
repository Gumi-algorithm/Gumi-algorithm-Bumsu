import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_2812_크게만들기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int k = K;
		int[] number = new int[N];
		Stack<Integer> stack = new Stack<>();
		String data = br.readLine();
		for(int i = 0; i < N; i++) {
			number[i] = data.charAt(i) -'0';
		}
		stack.add(number[0]);
		for(int i = 1; i < N; i++) {
			while(!stack.isEmpty() && stack.peek() < number[i] && K > 0) {
				//맨 끝에 넣은 값이 넣으려는 값과 비교해서 작으면 버림
				K -= 1; // k를 1씩 감소시킴
				stack.pop();
			}
			stack.add(number[i]);
		}
		for(int i = 0; i < N - k; i++) {
			sb.append(stack.get(i));
		}
		System.out.println(sb);
	}
}