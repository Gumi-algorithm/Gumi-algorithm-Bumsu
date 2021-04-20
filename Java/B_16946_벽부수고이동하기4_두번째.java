import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16946_벽부수고이동하기4_두번째 {

	static int N, M;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static boolean[][] arr, v;
	static int[][] map;
	static Queue<int[]> q;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new boolean[N][M];
		v = new boolean[N][M];
		map = new int[N][M];
		q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			String b = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = b.charAt(j) == '0' ? true : false; // 빈칸이면 true
				if (!arr[i][j])
					map[i][j] = 1;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] && !v[i][j]) {
					bfs(i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j] % 10);
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}

	static void bfs(int y, int x) {
		Queue<int[]> wq = new LinkedList<>();
		int cnt = 1;
		v[y][x] = true;
		q.offer(new int[] { y, x });
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];

				if (0 <= ny && ny < N && 0 <= nx && nx < M && !v[ny][nx]) {
					v[ny][nx] = true;
					if (arr[ny][nx]) {
						cnt += 1;
						q.offer(new int[] { ny, nx });
					} else {
						wq.offer(new int[] { ny, nx });
					}
				}
			}
		}
		while (!wq.isEmpty()) { //붙어 있는 벽들을 바로 cnt를 더해줌
			int[] cur = wq.poll();
			map[cur[0]][cur[1]] += cnt;
			v[cur[0]][cur[1]] = false;
		}
	}
}
