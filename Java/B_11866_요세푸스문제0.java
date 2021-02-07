import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_11866_요세푸스문제0 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("<");
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> number = new LinkedList<Integer>();
		for(int i = 1; i < N + 1; i++) {
			number.offer(i);
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < K - 1; j++) {
				number.offer(number.poll());
			}
			sb.append(number.poll() + ", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		System.out.println(sb);
	}
}