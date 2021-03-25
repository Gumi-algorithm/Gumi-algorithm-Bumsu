import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_7579_앱 {
	static class App{
		int m, c;

		public App(int m, int c) {
			this.m = m;
			this.c = c;
		}
	}
	static int N, M;
	static App[] arr;
	
	public static void main(String[] args) throws IOException {
//		메모리의 제한(10,000,000)이 너무 커서 비용으로 dp를 사용해야됨
//		비용은 최대 100 * 100으로 10,000
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		String[] m = br.readLine().split(" ");
		String[] c = br.readLine().split(" ");
		int sum = 0;
		arr = new App[N];
		for(int i = 0; i < N; i++) {
			arr[i] = new App(Integer.parseInt(m[i]), Integer.parseInt(c[i]));
			sum += arr[i].c;
		}
		
		int[] dp = new int[sum + 1];
		for(int i = 0; i < N; i++) {
			for(int j = sum; j >= 0; j--) {
				if(arr[i].c > j) { // 비용이 더 많이 들면 메모리 삭제를 못함
					dp[j] = dp[j]; 
				}else { // 삭제가 가능하면, 할당 받은 비용 - 해당 비용 + 해당 메모리, 삭제하기 전과 비교
					dp[j] = Math.max(dp[j], dp[j - arr[i].c] + arr[i].m);
				}
			}
		}
		for(int i = 0; i < sum + 1; i++) {
			if(dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}
	}
}
