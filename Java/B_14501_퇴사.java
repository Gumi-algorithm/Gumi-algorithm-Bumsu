import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14501_퇴사 {

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
			dp[i] = dp[i - 1]; // 일을 안 한 경우
			for(int j = 1; j < i + 1; j++) {
				if(i == day[j][0]) { // 일 끝난 경우를 찾는 것
					dp[i] = Math.max(dp[i], dp[j - 1] + day[j][1]); 
					// 일을 선택 안 한 경우와 선택 한 경우의 최대를 찾음
				}
			}
		}
		System.out.println(dp[N]);
	}
}