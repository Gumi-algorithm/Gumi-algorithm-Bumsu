import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16946_벽부수고이동하기4_첫번째 {

	static class Position {
		int y, x;

		public Position(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int N, M, group;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static int[][] arr;
	static boolean[][] v;
	static ArrayList<Position> blank;
	static HashMap<Integer, Integer> map = new HashMap<>();
	static Queue<Position> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		group = 2;
		arr = new int[N][M];
		v = new boolean[N][M];
		blank = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String m = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = m.charAt(j) - '0';
				if (arr[i][j] == 0) {
					blank.add(new Position(i, j));
				}
			}
		}
		fillBlank();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!v[i][j]) {
					boolean[] g = new boolean[group + 1];
					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (0 <= ny && ny < N && 0 <= nx && nx < M && v[ny][nx]) {
							if (!g[arr[ny][nx]]) {
								arr[i][j] += map.get(arr[ny][nx]);
								g[arr[ny][nx]] = true;
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (v[i][j]) {
					sb.append(0);
				} else {
					sb.append(arr[i][j] % 10);
				}
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static void bfs(Position p) {
		ArrayList<Position> list = new ArrayList<>();
		list.add(p);
		int cnt = 1;
		v[p.y][p.x] = true;
		q.offer(p);
		while (!q.isEmpty()) {
			Position now = q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if (0 <= ny && ny < N && 0 <= nx && nx < M && !v[ny][nx]) {
					if (arr[ny][nx] == 0) {
						cnt += 1;
						v[ny][nx] = true;
						q.offer(new Position(ny, nx));
						list.add(new Position(ny, nx));
					}
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			Position blank = list.get(i);
			arr[blank.y][blank.x] = group;
		}
		map.put(group++, cnt);
	}

	static void fillBlank() {
		for (int i = 0; i < blank.size(); i++) {
			Position now = blank.get(i);
			if (arr[now.y][now.x] == 0) {
				bfs(now);
			}
		}
	}
}
