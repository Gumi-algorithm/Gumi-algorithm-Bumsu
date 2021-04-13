import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_17836_공주님을구해라 {

	static class Node {
		int y, x, idx;
		boolean gram;

		public Node(int y, int x, boolean gram, int idx) {
			this.y = y;
			this.x = x;
			this.gram = gram;
			this.idx = idx;
		}

	}

	static int N, M, T;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static int[][] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();

	}
	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, false, 0));
		int[][][] v = new int[N][M][2];
		boolean[][][] vi = new boolean[N][M][2];
		vi[0][0][0] = true;
		boolean flag = true;
		while (!q.isEmpty()) {
			Node now = q.poll();

			if (now.y == N - 1 && now.x == M - 1) {
				flag = false;
				int time = v[now.y][now.x][now.idx];
				if (time > T)
					System.out.println("Fail");
				else {
					System.out.println(time);
				}
				return;
			}
			for (int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if (0 <= ny && ny < N && 0 <= nx && nx < M) {
					if (arr[ny][nx] == 0 && !vi[ny][nx][now.idx]) {
						v[ny][nx][now.idx] = v[now.y][now.x][now.idx] + 1;
						vi[ny][nx][now.idx] = true;
						q.offer(new Node(ny, nx, now.gram, now.idx));
					} else if (arr[ny][nx] == 1 && now.gram && !vi[ny][nx][1]) {
						v[ny][nx][1] = v[now.y][now.x][1] + 1;
						vi[ny][nx][1] = true;
						q.offer(new Node(ny, nx, true, 1));
					} else if (arr[ny][nx] == 2 && !vi[ny][nx][1]) {
						v[ny][nx][1] = v[now.y][now.x][0] + 1;
						vi[ny][nx][1] = true;
						q.offer(new Node(ny, nx, true, 1));
					}
				}
			}
		}
		if (flag) {
			System.out.println("Fail");
		}
	}
}
