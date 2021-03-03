import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15486_퇴사2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] day = new int[N + 1][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			day[i + 1][0] = i + Integer.parseInt(st.nextToken());
			day[i + 1][1] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			if(day[i][0] < N + 1) {
				dp[day[i][0]] = Math.max(dp[day[i][0]], day[i][1] + dp[i - 1]);
			}
			dp[i] = Math.max(dp[i], dp[i - 1]);
		}
		System.out.println(dp[N]);
	}
}