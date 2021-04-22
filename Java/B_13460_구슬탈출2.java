import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_13460_구슬탈출2 {

	static class Position {
		Marble[] marble;
		int cnt;

		public Position(Marble[] marble, int cnt) {
			this.marble = marble;
			this.cnt = cnt;
		}
	}

	static class Marble {
		int y, x, cnt;

		public Marble(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}

	static int N, M;
	static int[] O, dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static char[][] arr;
	static boolean[][][][] v;
	static Marble R, B;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		v = new boolean[N][M][N][M];

		for (int i = 0; i < N; i++) {
			String m = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = m.charAt(j);
				if (arr[i][j] == 'R') {
					arr[i][j] = '.';
					R = new Marble(i, j, 0);
				} else if (arr[i][j] == 'B') {
					B = new Marble(i, j, 0);
					arr[i][j] = '.';
				} else if (arr[i][j] == 'O') {
					O = new int[] { i, j };
				}
			}
		}
		bfs();
	}

	static void bfs() {
		int flag = 0;
		Queue<Position> q = new LinkedList<>();
		q.offer(new Position(new Marble[] { R, B }, 0));
		v[R.y][R.x][B.y][B.x] = true;

		while (!q.isEmpty()) {
			Position now = q.poll();
			for (int j = 0; j < 4; j++) {
				Marble[] marble = new Marble[2];
				for (int i = 0; i < 2; i++) {
					int ny = now.marble[i].y + dy[j];
					int nx = now.marble[i].x + dx[j];
					int cnt = 0;
					while (0 <= ny && ny < N && 0 <= nx && nx < M) {
						if (arr[ny][nx] == '.') {
							ny += dy[j];
							nx += dx[j];
							cnt += 1;
						} else if (arr[ny][nx] == 'O') {
							if (i == 0)
								flag = 1;
							else
								flag = 2;
							break;
						} else {
							break;
						}
					}
					ny -= dy[j];
					nx -= dx[j];
					marble[i] = new Marble(ny, nx, cnt);
				}
				if (flag == 1) { // 빨간 공만 구멍에 빠진 경우
					if (now.cnt + 1 > 10) {
						System.out.println(-1);
					} else {
						System.out.println(now.cnt + 1);
					}
					break;
				} else if (flag == 2) { // 파란공이 구멍에 빠진 경우
					flag = 0;
					continue;
				} else {
					if (marble[0].y == marble[1].y && marble[0].x == marble[1].x) { // 구슬의 위치가 같은 경우
						if (marble[0].cnt < marble[1].cnt) {
							marble[1].y -= dy[j];
							marble[1].x -= dx[j];
						} else {
							marble[0].y -= dy[j];
							marble[0].x -= dx[j];
						}
					}
					if (!v[marble[0].y][marble[0].x][marble[1].y][marble[1].x]) { // 구슬 방문 체크
						v[marble[0].y][marble[0].x][marble[1].y][marble[1].x] = true;
						q.offer(new Position(marble, now.cnt + 1));
					}
				}
			}
			if (flag == 1)
				break;
		}
		if (flag != 1)
			System.out.println(-1);
	}
}
