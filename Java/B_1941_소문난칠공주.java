import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class B_1941_소문난칠공주 {

	static class Pair {
		int y, x;
		char val;

		public Pair(int y, int x, char val) {
			this.y = y;
			this.x = x;
			this.val = val;
		}
	}

	static int cnt = 0;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static boolean[][] v = new boolean[5][5];
	static ArrayList<Pair> arr = new ArrayList<>();
	static HashMap<Character, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map.put('Y', 0);
		map.put('S', 0);
		for (int i = 0; i < 5; i++) {
			String st = br.readLine();
			for (int j = 0; j < 5; j++) {
				arr.add(new Pair(i, j, st.charAt(j)));
			}
		}
		dfs(0, 0);
		System.out.println(cnt);
	}

	static int bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { y, x });
		boolean[][] check = new boolean[5][5];
		check[y][x] = true;
		int count = 1;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = now[0] + dy[i];
				int nx = now[1] + dx[i];
				if (0 <= ny && ny < 5 && 0 <= nx && nx < 5 && !check[ny][nx] && v[ny][nx]) {
					check[ny][nx] = true;
					count += 1;
					q.offer(new int[] { ny, nx });
				}
			}
		}
		return count;
	}

	static void dfs(int index, int start) {
		if (index == 7) {
			if (bfs(arr.get(start - 1).y, arr.get(start - 1).x) == 7) {
				cnt += 1;
			}
			return;
		}
		for (int i = start; i < 25; i++) {
			Pair next = arr.get(i);
			map.put(next.val, map.get(next.val) + 1);
			v[next.y][next.x] = true;
			if(map.get('Y') < 4) {
				dfs(index + 1, i + 1);
			}
			map.put(next.val, map.get(next.val) - 1);
			v[next.y][next.x] = false;
		}
	}

}
