import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B_2239_스도쿠 {

	static class Position {
		int y, x;

		public Position(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

	static int cnt = 0;
	static int[][] arr = new int[9][9];
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Position> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			String map = br.readLine();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = map.charAt(j) - '0';
				if (arr[i][j] == 0)
					list.add(new Position(i, j));
			}
		}
		dfs(0);
	}

	static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static void dfs(int index) {
		if (index == list.size()) {
			cnt += 1;
			print();
			return;
		}
		Position now = list.get(index);
		for (int i = 1; i < 10; i++) {
			if (isTrue(now.y, now.x, i)) {
				arr[now.y][now.x] = i;
				dfs(index + 1);
				if (cnt == 1)
					return;
				arr[now.y][now.x] = 0;

			}
		}
	}

	static boolean isTrue(int y, int x, int n) {
		for (int i = 0; i < 9; i++) { // 가로 검사
			if (arr[y][i] == n)
				return false;
		}
		for (int i = 0; i < 9; i++) { // 세로 검사
			if (arr[i][x] == n)
				return false;
		}
		int ny = y / 3 * 3, nx = x / 3 * 3;
		for (int i = 0; i < 3; i++) { // 사각형 검사
			for (int j = 0; j < 3; j++) {
				if (arr[ny + i][nx + j] == n)
					return false;
			}
		}
		return true;
	}
}
