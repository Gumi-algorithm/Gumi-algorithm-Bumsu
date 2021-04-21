import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_15683_감시 {

	static class CCTV {
		int y, x, d;

		public CCTV(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}

	static int N, M, res, size;
	static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
	static int[][] arr;
	static int[][][] comb;
	static int[][] v;
	static List<CCTV> cctv;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int cnt = 0;
		res = N * M;
		arr = new int[N][M];
		v = new int[N][M];
		cctv = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (0 < arr[i][j] && arr[i][j] < 6) {
					cctv.add(new CCTV(i, j, arr[i][j] - 1));
					v[i][j] = arr[i][j];
				} else if (arr[i][j] == 0) {
					cnt += 1;
				}
			}
		}
		size = cctv.size();
		comb = new int[][][] { { { 0 }, { 1 }, { 2 }, { 3 } }, // 1
				{ { 0, 2 }, { 1, 3 } }, // 2
				{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 0, 3 } }, // 3
				{ { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3 } }, // 4
				{ { 0, 1, 2, 3 } } }; // 5
		dfs(0, cnt);
		System.out.println(res);
	}

	static void dfs(int index, int cnt) {
		if (index == size) {
			res = Math.min(res, cnt);
			return;
		}
		CCTV now = cctv.get(index);
		for (int i = 0; i < comb[now.d].length; i++) {
			int count = change(now.y, now.x, comb[now.d][i], 1);
			dfs(index + 1, cnt - count);
			change(now.y, now.x, comb[now.d][i], -1);
		}
	}

	static int change(int y, int x, int[] d, int af) {
		int count = 0;
		for (int i = 0; i < d.length; i++) {
			int ny = y + dy[d[i]];
			int nx = x + dx[d[i]];
			while (0 <= ny && ny < N && 0 <= nx && nx < M) {
				if (arr[ny][nx] == 6) {
					break;
				} else {
					if (v[ny][nx] == 0) {
						count += 1;
					}
					v[ny][nx] += af;
					ny += dy[d[i]];
					nx += dx[d[i]];
				}
			}
		}
		return count;

	}
}
