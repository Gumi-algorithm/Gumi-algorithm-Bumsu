import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_4179_불 {

	static class Position {
		int y, x, time;

		public Position(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	static int N, M;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static char[][] arr;
	static int[][] v;
	static Queue<Position> jihoon;
	static Queue<Position> fire;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		v = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				v[i][j] = -1;
			}
		}
		arr = new char[N][M];
		fire = new LinkedList<>();
		jihoon = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			String m = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = m.charAt(j);
				if (arr[i][j] == 'J') {
					jihoon.offer(new Position(i, j, 0));
					v[i][j] = 0;
				} else if (arr[i][j] == 'F') {
					fire.offer(new Position(i, j, 0));
					v[i][j] = 0;
				}
			}
		}
		bfs();
		int time = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (0 < i && i < N - 1 && 0 < j && j < M - 1)
					continue;
				if ((arr[i][j] == 'J' || arr[i][j] == '.') && v[i][j] != -1) {
					time = Math.min(time, v[i][j] + 1);
				}
			}
		}
		System.out.println(time == Integer.MAX_VALUE ? "IMPOSSIBLE" : time);

	}
	static void bfs() {
		while (!jihoon.isEmpty()) {
			Position now = jihoon.poll();

			for (int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if (0 <= ny && ny < N && 0 <= nx && nx < M && v[ny][nx] == -1) {
					if (arr[ny][nx] == '.') {
						v[ny][nx] = now.time + 1;
						jihoon.offer(new Position(ny, nx, now.time + 1));
					}
				}
			}
		}
		while (!fire.isEmpty()) {
			Position now = fire.poll();

			for (int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if (0 <= ny && ny < N && 0 <= nx && nx < M && v[ny][nx] >= now.time + 1) {
					v[ny][nx] = -1;
					fire.offer(new Position(ny, nx, now.time + 1));
				}
			}
		}
	}
}
