import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_11985_오렌지출하 {

	static int N, M, K;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		long[] dp = new long[N + 1];
		for (int i = 1; i < N + 1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			dp[i] = Long.MAX_VALUE;
		}

		for (int i = 1; i < N + 1; i++) {
			long max = arr[i], min = arr[i];
			
			for (int j = i; j >= Math.max(1, i - M + 1); j--) {
				max = Math.max(max, arr[j]);
				min = Math.min(min, arr[j]);
				dp[i] = Math.min(dp[i], dp[j - 1] + K + (i - j + 1) * (max - min));
			}
		}
		System.out.println(dp[N]);
	}
}
