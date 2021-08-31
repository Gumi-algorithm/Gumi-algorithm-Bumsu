import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_5547_일루미네이션 {

	static int W, H, res;
	static int[] oddDy = { -1, -1, 0, 0, 1, 1 }, oddDx = { 0, 1, -1, 1, 0, 1 }, evenDy = { -1, -1, 0, 0, 1, 1 },
			evenDx = { -1, 0, -1, 1, -1, 0 };
	static int[][] arr;
	static boolean[][] check;

	static class Node {
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		arr = new int[H + 2][W + 2];
		check = new boolean[H + 2][W + 2];
		for (int i = 1; i < H + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < W + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs(0, 0));
	}

	static boolean checkRange(int y, int x) {
		if (0 <= y && y <= H + 1 && 0 <= x && x <= W + 1) {
			return true;
		}
		return false;
	}

	static int bfs(int y, int x) {
		Queue<Node> q = new LinkedList<>();
		int total = 0;
		check[y][x] = false;
		q.offer(new Node(y, x));
		while (!q.isEmpty()) {
			int wall = 0;
			Node now = q.poll();
			for (int i = 0; i < 6; i++) {
				int ny, nx;
				if (now.y % 2 == 0) {
					ny = now.y + evenDy[i];
					nx = now.x + evenDx[i];
				} else {
					ny = now.y + oddDy[i];
					nx = now.x + oddDx[i];
				}
				if (checkRange(ny, nx)) {
					if (arr[ny][nx] == 1) {
						wall++;
					} else {
						if (!check[ny][nx]) {
							check[ny][nx] = true;
							q.offer(new Node(ny, nx));
						}
					}
				}
			}
			total += wall;
		}
		return total;
	}
}
