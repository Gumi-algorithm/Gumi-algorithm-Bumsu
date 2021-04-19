import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA프리랜서 {

	static class Day implements Comparable<Day> {
		int start, end, w;

		public Day(int start, int end, int w) {
			this.start = start;
			this.end = end;
			this.w = w;
		}
		@Override
		public int compareTo(Day o) {
			return Integer.compare(this.end, o.end);
		}
	}

	static int N, M;
	static Day[] arr;
	static int[] dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new Day[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				arr[i] = new Day(start, end, w);
			}
			Arrays.sort(arr);
			dp = new int[M + 1];
			for(int i = 1; i < M + 1; i++) {
				dp[i] = Math.max(dp[i], dp[i - 1]);
				for(int j = 0; j < N; j++) {
					if(arr[j].end == i) {
						dp[i] = Math.max(dp[i], arr[j].w + dp[arr[j].start - 1]);
					}else if(arr[j].end > i) {
						break;
					}
				}
			}
			int res = 0;
			for (int i = 1; i < M + 1; i++) {
				res = Math.max(res, dp[i]);
			}
			sb.append("#" + t + " " + res + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
