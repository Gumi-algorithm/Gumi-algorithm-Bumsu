import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14601_샤워실바닥깔기 {

	static class Position {
		int y, x;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int K, cnt;
	static int[][] arr;
	static Position hole;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		cnt = 0;
		arr = new int[(int) Math.pow(2, K)][(int) Math.pow(2, K)];
		st = new StringTokenizer(br.readLine());

		hole = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		arr[hole.y - 1][hole.x - 1] = -1;

		dfs(0, 0, (int) Math.pow(2, K));

		for (int i = (int) Math.pow(2, K) - 1; i >= 0; i--) {
			for (int j = 0; j < (int) Math.pow(2, K); j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	// 다시 봐야할 필요성이 있음 개어려움
	static void dfs(int y, int x, int n) {
		cnt++;
		int len = n / 2;
		if (checkArr(y, x, len))
			arr[y + len - 1][x + len - 1] = cnt;
		if (checkArr(y, x + len, len))
			arr[y + len - 1][x + len] = cnt;
		if (checkArr(y + len, x, len))
			arr[y + len][x + len - 1] = cnt;
		if (checkArr(y + len, x + len, len))
			arr[y + len][x + len] = cnt;
		if (n == 2)
			return;

		dfs(y, x, len);
		dfs(y, x + len, len);
		dfs(y + len, x, len);
		dfs(y + len, x + len, len);
	}

	static boolean checkArr(int y, int x, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[y + i][x + j] != 0)
					return false;
			}
		}
		return true;
	}
}
