import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_17144_미세먼지안녕2번째 {

	static class Position {
		int y, x, w;

		public Position(int y, int x, int w) {
			this.y = y;
			this.x = x;
			this.w = w;
		}
	}
	static int N, M, T;
	static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
	static int[][] arr, cleaner;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		cleaner = new int[2][2];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1) {
					cleaner[idx++] = new int[] { i, j };
				}
			}
		}
		for (int i = 0; i < T; i++) {
			spread();
			Clock();
			AntiClock();
		}
		int res = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				res += arr[i][j];
			}
		}
		System.out.println(res);
	}

	static void spread() {
		Queue<Position> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] > 0) {
					q.offer(new Position(i, j, arr[i][j]));
				}
			}
		}
		while (!q.isEmpty()) {
			Position now = q.poll();

			int dust = now.w / 5;
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if (0 <= ny && ny < N && 0 <= nx && nx < M && arr[ny][nx] >= 0) {
					arr[ny][nx] += dust;
					cnt += 1;
				}
			}
			arr[now.y][now.x] -= cnt * dust;
		}
	}

	static void Clock() {
		int y = cleaner[0][0], x = cleaner[0][1];
		int end = y;
		int[] dire = new int[] { 0, 1, 2, 3 };
		for (int i = 0; i < 4; i++) {
			while (true) {
				int ny = y + dy[dire[i]];
				int nx = x + dx[dire[i]];
				if (0 <= ny && ny <= end && 0 <= nx && nx < M) {
					if (arr[ny][nx] != -1 && arr[y][x] != -1) {
						arr[y][x] = arr[ny][nx];
						arr[ny][nx] = 0;
					}
					y = ny;
					x = nx;
				} else {
					break;
				}
			}
		}
	}

	static void AntiClock() {
		int y = cleaner[1][0], x = cleaner[1][1];
		int start = y;
		int[] dire = new int[] { 2, 1, 0, 3 };
		for (int i = 0; i < 4; i++) {
			while (true) {
				int ny = y + dy[dire[i]];
				int nx = x + dx[dire[i]];
				if (start <= ny && ny < N && 0 <= nx && nx < M) {
					if (arr[ny][nx] != -1 && arr[y][x] != -1) {
						arr[y][x] = arr[ny][nx];
						arr[ny][nx] = 0;
					}
					y = ny;
					x = nx;
				} else {
					break;
				}
			}
		}
	}
}
