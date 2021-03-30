import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_1823_수확 {

	static int N, res = 0;
	static int[] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
//		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[N][N];
		System.out.println(dfs(0, N - 1, 1)); // 처음엔 수확할 수 있는 벼 0, N - 1뿐
	}
	static int dfs(int l, int r, int cnt) { // l, r은 수확할 수 있는 벼, cnt는 수확 횟수 확인 가능
		
		if(cnt == N + 1) return 0; // N번 재배하면 return
		if(dp[l][r] != 0) return dp[l][r]; // 이미 거기를 재배해 봤으면 그 값을 리턴
		
		return dp[l][r] = Math.max(dfs(l + 1, r, cnt + 1) + cnt * arr[l], 
							dfs(l, r - 1, cnt + 1) + cnt * arr[r]);
		// 왼쪽으로 배기하던 오른쪽으로 배기하던 둘 중에 큰 값 저장
	}
}
