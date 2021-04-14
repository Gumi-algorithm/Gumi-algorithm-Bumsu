import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_7576_토마토 {

	static class Position {
		int y, x, time;

		public Position(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}

	}

	static int M, N, time, cnt;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static int[][] arr;
	static boolean[][] v;
	static Queue<Position> q;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		time = 0;
		cnt = 0;
		arr = new int[N][M];
		v = new boolean[N][M];
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					v[i][j] = true;
					q.offer(new Position(i, j, 0));
				} else if (arr[i][j] == 0) {
					cnt += 1;
				}
			}
		}
		bfs();
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Position now = q.poll();
			time = Math.max(time, now.time);

			for (int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if (0 <= ny && ny < N && 0 <= nx && nx < M && !v[ny][nx]) {
					if (arr[ny][nx] == 0) {
						v[ny][nx] = true;
						cnt -= 1;
						q.offer(new Position(ny, nx, now.time + 1));
					}
				}
			}
		}
		if (cnt == 0) {
			System.out.println(time);
		} else {
			System.out.println(-1);
		}
	}
}
