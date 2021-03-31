import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14722_우유도시 {

	static int N, res = 0;
	static int[] dy = {1, 0}, dx = {0, 1};
	static int[][] arr;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[N][N][3];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0][0] = arr[0][0] == 0 ? 1 : 0;
		
		// 0을 제외한 모든 곳에 조건이 추가로 있는데 이유는 무조건 0(딸기우유)보다 다른 우유를 먹은 횟수가 많을 순 없어서.
		for (int i = 1; i < N; i++) {
	        int milk = arr[0][i];
	 
	        dp[0][i][0] = milk == 0 ? dp[0][i - 1][2] + 1 : dp[0][i - 1][0]; 
	        dp[0][i][1] = milk == 1 && dp[0][i][2] < dp[0][i][0] ? dp[0][i - 1][0] + 1 : dp[0][i - 1][1];
	        dp[0][i][2] = milk == 2 && dp[0][i][0] < dp[0][i][1] ? dp[0][i - 1][1] + 1 : dp[0][i - 1][2];
	    }
		
	    for (int i = 1; i < N; i++) {
	        int milk = arr[i][0];
	 
	        dp[i][0][0] = milk == 0 ? dp[i - 1][0][2] + 1 : dp[i - 1][0][0];
	        dp[i][0][1] = milk == 1 && dp[i][0][2] < dp[i][0][0] ? dp[i - 1][0][0] + 1 : dp[i - 1][0][1];
	        dp[i][0][2] = milk == 2 && dp[i][0][0] < dp[i][0][1] ? dp[i - 1][0][1] + 1 : dp[i - 1][0][2];
	    }
	 
	    for (int i = 1; i < N; i++) {
	        for (int j = 1; j < N; j++) {
	            int milk = arr[i][j];
	 
	            dp[i][j][0] = milk == 0 
	                ? Math.max(dp[i][j - 1][2] + 1, dp[i - 1][j][2] + 1)
	                : Math.max(dp[i][j - 1][0], dp[i - 1][j][0]);
	            dp[i][j][1] = milk == 1 && dp[i][j][2] < dp[i][j][0]
	                ? Math.max(dp[i][j - 1][0] + 1, dp[i - 1][j][0] + 1)
	                : Math.max(dp[i][j - 1][1], dp[i - 1][j][1]);
	 
	            dp[i][j][2] = milk == 2 && dp[i][j][0] < dp[i][j][1]
	                ? Math.max(dp[i][j - 1][1] + 1, dp[i - 1][j][1] + 1)
	                : Math.max(dp[i][j - 1][2], dp[i - 1][j][2]);
	        }
	    }
	    System.out.println(Math.max(dp[N - 1][N - 1][0], Math.max(dp[N - 1][N - 1][1], dp[N - 1][N - 1][2])));
	}
}
