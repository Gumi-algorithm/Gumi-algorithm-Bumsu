import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_12865_평범한배낭 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][K + 1];
		int[][] weight = new int[N + 1][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			weight[i + 1] = new int[] {w, v};
		}
		for(int i = 1; i < N + 1; i++) {
			int w = weight[i][0];
			int v = weight[i][1];
			for(int j = 0; j < K + 1; j++) {
				if(j < w) {
					dp[i][j] = dp[i - 1][j];
				}else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v); 
					// 물건을 안 담을 경우, 물건을 안 담았을 때의 w만큼 뺀 가치 + 현재 물건의 가치
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}