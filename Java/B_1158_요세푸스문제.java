import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1158_요세푸스문제 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Queue<Integer> queue = new LinkedList<>();

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N + 1; i++) {
			queue.offer(i);
		}
		sb.append("<");
		while(!queue.isEmpty()) {
			for(int i = 0; i < K - 1; i++) {
				queue.offer(queue.poll());
			}
			sb.append(queue.poll() + ", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		System.out.println(sb);
	}
}
