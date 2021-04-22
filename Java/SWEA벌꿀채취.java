import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA벌꿀채취 {

	static class Position implements Comparable<Position> {
		int y, x, val;

		public Position(int y, int x, int val) {
			this.y = y;
			this.x = x;
			this.val = val;
		}

		@Override
		public int compareTo(Position o) {
			return Integer.compare(o.val, this.val);
		}

		@Override
		public String toString() {
			return "[" + y + "," + x + "," + val + "]";
		}

	}

	static int N, M, K, res, cnt;
	static int[] dy = { 1, 0 }, dx = { 0, 1 };
	static int[][] arr = new int[11][11];
	static boolean[][] v;
	static Position[] bee;
	static ArrayList<Position[]> honey = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			res = 0;
			cnt = 0;

			honey.clear();
			bee = new Position[M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bee[0] = new Position(i, j, arr[i][j]);
					dfs(1, i, j);
				}
			}
			int size = honey.size();
			for (int i = 0; i < size; i++) {
				v = new boolean[N][N];
				int sum = cal(honey.get(i), true);
				for (int j = i + 1; j < size; j++) {
					res = Math.max(res, sum + getSum(0, honey.get(j), 0, 0));
				}
			}
			sb.append("#" + t + " " + res + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static int cal(Position[] b, boolean flag) {
		Arrays.sort(b);
		int value = getSum(0, b, 0, 0);
		for (int i = 0; i < M; i++) {
			if (flag) {
				v[b[i].y][b[i].x] = true;
			}
		}
		return value;
	}

	static int getSum(int index, Position[] b, int cnt, int val) {

		int res = 0;
		if (index == M) {
			return val;
		}
		int value = b[index].val;
		if (cnt + value <= K && !v[b[index].y][b[index].x]) {
			res = Math.max(res, getSum(index + 1, b, cnt + value, val + (value * value)));
		}
		res = Math.max(res, getSum(index + 1, b, cnt, val));
		return res;
	}

	static void dfs(int index, int y, int x) {
		if (index == M) {
			Position[] bee1 = Arrays.copyOf(bee, M);
			honey.add(bee1);
			return;
		}
		int ny = y + 0;
		int nx = x + 1;
		if (0 <= ny && ny < N && 0 <= nx && nx < N) {
			bee[index] = new Position(ny, nx, arr[ny][nx]);
			dfs(index + 1, ny, nx);
		}
	}
}
