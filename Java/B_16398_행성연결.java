import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_16398_행성연결 {

	static int N;
	static int[][] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		final int MAX = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		long res = 0;
		int[] val = new int[N];
		boolean[] v = new boolean[N];
		Arrays.fill(val, MAX);
		val[0] = 0;
		for (int i = 0; i < N; i++) {
			int idx = 0, min = MAX;
			for (int j = 0; j < N; j++) {
				if (!v[j] && min > val[j]) {
					min = val[j];
					idx = j;
				}
			}
			res += min;
			v[idx] = true;

			for (int j = 0; j < N; j++) {
				if (!v[j] && arr[idx][j] != 0 && val[j] > arr[idx][j]) {
					val[j] = arr[idx][j];
				}
			}
		}
		System.out.println(res);
	}
}
