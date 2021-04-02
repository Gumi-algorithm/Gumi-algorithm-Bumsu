import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2624_동전바꿔주기 {

	static class Coin{
		int n, val;

		public Coin(int n, int val) {
			this.n = n;
			this.val = val;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		Coin[] coins = new Coin[K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int val = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			coins[i] = new Coin(n, val);
		}
		int max = 0;
		int[] dp = new int[T + 1];
		dp[0] = 1;
		for(int i = 0; i < K; i++) {
			max += coins[i].val * coins[i].n;
			max = Math.min(max, T); // 최대로 될 수 있는 금액
			for(int j = max; j > 0; j--) { // n개 정해져 있기 때문에 뒤에서 검색
				for(int k = 1; k <= coins[i].n; k++) { // 동전 갯수만큼
					if(j - (k * coins[i].val) >= 0 && dp[j - (k * coins[i].val)] > 0) {
						dp[j] += dp[j - (k * coins[i].val)]; // 경우의 수를 더해줌
					}
				}
			}
		}
		System.out.println(dp[T]);
	}
}
