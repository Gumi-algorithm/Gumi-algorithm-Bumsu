import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_3980_선발명단 {

	static int res;
	static boolean[] check = new boolean[11];
	static int[][] arr = new int[11][11];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int C = Integer.parseInt(br.readLine());
		for (int c = 0; c < C; c++) {
			res = 0;
			for (int i = 0; i < 11; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 11; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, 0);
			sb.append(res + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static void dfs(int index, int sum) {

		if (index == 11) {
			res = Math.max(res, sum);
			return;
		}
		for (int i = 0; i < 11; i++) {
			if (arr[i][index] > 0 && !check[i]) {
				check[i] = true;
				dfs(index + 1, sum + arr[i][index]);
				check[i] = false;
			}
		}
	}
}
