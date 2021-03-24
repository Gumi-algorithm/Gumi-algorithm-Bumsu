import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class B_12852_1로만들기2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		Arrays.fill(dp, 1000001);
		dp[1] = 0;
		int[] parent = new int[N + 1];
		parent[1] = 1;
		for(int i = 1; i < N + 1; i++) {
			if(i * 3 < N + 1) {
				if(dp[i * 3] > dp[i] + 1) {
					dp[i * 3] = dp[i] + 1;
					parent[i * 3] = i;
				}
			}
			if(i * 2 < N + 1) {
				if(dp[i * 2] > dp[i] + 1) {
					dp[i * 2] = dp[i] + 1;
					parent[i * 2] = i;
				}
				
			}
			if(i + 1 < N + 1) {
				if(dp[i + 1] > dp[i] + 1) {
					dp[i + 1] = dp[i] + 1;
					parent[i + 1] = i;
				}
			}
		}
		sb.append(dp[N] + "\n");
		Queue<Integer> queue = new LinkedList<>();
		if(N != 1) queue.offer(N);
		while(1 != parent[N]) {
			queue.offer(parent[N]);
			N = parent[N];
		}
		queue.offer(1);
		while(!queue.isEmpty()) sb.append(queue.poll() + " ");
		System.out.println(sb);
	}
}