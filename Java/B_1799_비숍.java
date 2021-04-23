import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B_1799_비숍 {

	static class Position {
		int y, x;

		public Position(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

	static int N, b, w;
	static int[][] arr;
	static ArrayList<Position> white = new ArrayList<>();
	static ArrayList<Position> black = new ArrayList<>();
	static HashMap<Integer, Boolean> right = new HashMap<>();
	static HashMap<Integer, Boolean> left = new HashMap<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					// 비숍은 흰색바닥과 검은색 바닥을 왔다갔다 못함
					// 흰색은 흰색만 검은색은 검은색만(같은 대각선만 왔다 갔다 됨)
					// 그래서 쪼개서 조합을 해야됨(전체로 하면 시간초과)
					if ((i + j) % 2 == 0) {
						white.add(new Position(i, j));
					} else {
						black.add(new Position(i, j));
					}
				}
			}
		}
		for (int i = 0; i <= 2 * (N - 1); i++) { // 오른 대각선
			right.put(i, true);
		}
		for (int i = -(N - 1); i < N; i++) { // 왼 대각선
			left.put(i, true);
		}
		dfs(0, 0, white, true);
		dfs(0, 0, black, false);
		System.out.println(w + b);
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	static void dfs(int cnt, int start, ArrayList<Position> blank, boolean flag) {
		for (int i = start; i < blank.size(); i++) {
			Position now = blank.get(i);
			if (arr[now.y][now.x] == 1 && isTrue(now.y, now.x)) {
				changeMap(now.y, now.x, false);
				arr[now.y][now.x] = 2;
				dfs(cnt + 1, i + 1, blank, flag);
				arr[now.y][now.x] = 1;
				changeMap(now.y, now.x, true);
			}

		}
		if (flag) {
			w = Math.max(w, cnt);
		} else {
			b = Math.max(b, cnt);
		}
	}

	static boolean isTrue(int y, int x) { // 체스판의 규칙
		int r = y + x, l = y - x;
		if (left.get(l) && right.get(r)) {
			return true;
		}
		return false;
	}

	static void changeMap(int y, int x, boolean flag) {
		left.put(y - x, flag);
		right.put(y + x, flag);
	}
}
