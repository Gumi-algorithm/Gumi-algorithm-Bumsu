import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2294_동전2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] coin = new int[n];
		for(int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		int[][] dp = new int[n][k + 1];
		for(int j = 1; j < k + 1; j++) {
			if(j == coin[0]) dp[0][j] = 1;
			else if(j % coin[0] == 0) {
				dp[0][j] = dp[0][j - coin[0]] + 1;
			}
		}
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < k + 1; j++) {
				if(j < coin[i]) {
					dp[i][j] = dp[i - 1][j];
				}else if(j == coin[i]) {
					dp[i][j] = 1;
				}
				else {
					int fir = dp[i - 1][j];
					int sec = dp[i][j - coin[i]];
					if(fir == 0 && sec == 0) {
						dp[i][j] = 0;
					}else if(fir == 0 && sec != 0) {
						dp[i][j] = sec + 1;
					}else if(fir != 0 && sec == 0) {
						dp[i][j] = fir;
					}else {
						dp[i][j] = Math.min(fir, sec + 1);
					}
				}
			}
		}
		if(dp[n - 1][k] == 0) {
			System.out.println(-1);
		}else {
			System.out.println(dp[n - 1][k]);
		}
	}

}
