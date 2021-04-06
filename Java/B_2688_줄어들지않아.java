import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_2688_줄어들지않아 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		long[][] dp = new long[65][10];
		for(int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		for(int i = 2; i < 65; i++) {
			dp[i][0] = 1;
			for(int j = 1; j < 10; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			long res = 0;
			for(int i = 0; i < 10; i++) {
				res += dp[N][i];
			}
			sb.append(res + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
