import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1194_달이차오른다가자 {

	static class Position {
		int y, x, key, time;

		public Position(int y, int x, int key, int time) {
			this.y = y;
			this.x = x;
			this.key = key;
			this.time = time;
		}
	}

	static int N, M;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static char[][] arr;
	static int[][][] v;
	static Queue<Position> q;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		v = new int[N][M][2];
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			String m = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = m.charAt(j);
				if (arr[i][j] == '0') {
					arr[i][j] = '.';
					q.offer(new Position(i, j, 1, 0));
				}
				v[i][j][1] = 0;
			}
		}
		bfs();
	}

	static void bfs() {
		boolean flag = true;

		while (!q.isEmpty()) {
			Position now = q.poll();

			if (arr[now.y][now.x] == '1') {
				flag = false;
				System.out.println(now.time);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if (0 <= ny && ny < N && 0 <= nx && nx < M) {
					if ((v[ny][nx][1] | now.key) != v[ny][nx][1]) { // 이전에 왔었던 곳과 키값이 변했다면
						if ('a' <= arr[ny][nx] && arr[ny][nx] <= 'f') { // 열쇠를 주웠을 때
							int key = now.key | (1 << (arr[ny][nx] - 'a') + 1); //키를 추가
							v[ny][nx][0] = now.time + 1;
							v[ny][nx][1] = key;
							q.offer(new Position(ny, nx, key, now.time + 1));
						} else if ('A' <= arr[ny][nx] && arr[ny][nx] <= 'F') { //문을 만났을 때
							int key = arr[ny][nx] - 'A' + 1;
							if ((now.key & (1 << key)) > 1) { // 키가 있는지 검사
								v[ny][nx][0] = now.time + 1;
								v[ny][nx][1] = now.key;
								q.offer(new Position(ny, nx, now.key, now.time + 1));
							}
						} else if (arr[ny][nx] == '.') { // 빈 공간
							v[ny][nx][0] = now.time + 1;
							v[ny][nx][1] = now.key;
							q.offer(new Position(ny, nx, now.key, now.time + 1));
						} else if (arr[ny][nx] == '1') { // 탈출구
							q.offer(new Position(ny, nx, now.key, now.time + 1));
						}
					}
				}
			}
		}
		if (flag)
			System.out.println(-1);
	}

}
