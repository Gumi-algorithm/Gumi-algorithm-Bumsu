import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_11062_카드게임 {

	static int N;
	static int[] card;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			card = new int[N];
			dp = new int[N][N];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				card[i] = Integer.parseInt(st.nextToken());
			}
			sb.append(dfs(0, N - 1, 0) + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static int dfs(int l, int r, int cnt) {
		
		if(cnt == N) {
			return 0;
		}
		
		if(dp[l][r] != 0) return dp[l][r];
		
		if(cnt % 2 == 0) { // 나 차례 최대 점수를 저장
			dp[l][r] = Math.max(dfs(l + 1, r, cnt + 1) + card[l], dfs(l, r - 1, cnt + 1) + card[r]);
		}else { // 상대방 차례 내 점수가 최저가 되는 점수를 고름
			dp[l][r] = Math.min(dfs(l + 1, r, cnt + 1), dfs(l, r - 1, cnt + 1));
		}
		return dp[l][r];
	}
}
