import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1915_가장큰정사각형 {

	static int N, M;
	static int[][] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M + 1];
		for (int i = 1; i < N + 1; i++) {
			String sq = br.readLine();
			for (int j = 1; j < M + 1; j++) {
				arr[i][j] = sq.charAt(j - 1) - '0';
			}
		}
		int res = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				if (arr[i][j] == 1) {
					arr[i][j] = Math.min(arr[i - 1][j], arr[i][j - 1]);
					arr[i][j] = Math.min(arr[i][j], arr[i - 1][j - 1]);
					arr[i][j] += 1;
					res = Math.max(res, arr[i][j]);
				}
			}
		}
		System.out.println(res * res);
	}
}
