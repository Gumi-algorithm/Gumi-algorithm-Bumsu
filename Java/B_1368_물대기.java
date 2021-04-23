import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_1368_물대기 {

	static class Position implements Comparable<Position> {
		int y, x, cost;

		public Position(int y, int x, int cost) {
			super();
			this.y = y;
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static int N;
	static int[] p;
	static PriorityQueue<Position> pq;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		p = new int[N + 1];
		pq = new PriorityQueue<>();
		for (int i = 1; i < N + 1; i++) {
			int cost = Integer.parseInt(br.readLine());
			p[i] = i;
			pq.offer(new Position(i, 0, cost)); // 물 뚫기는 0과 연결
		}
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < N + 1; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if (i < j) {
					pq.offer(new Position(i, j, cost)); // 하나만 가지고 있으면 됨
				}
			}
		}
		int cnt = 0, res = 0;
		while (!pq.isEmpty()) {
			Position now = pq.poll();

			if (union(now.y, now.x)) {
				res += now.cost;
				if(++cnt == N) { // 물 뚫기 때문에 N개 연결
					break;
				}
			}

		}
		System.out.println(res);
	}

	static int find(int x) {
		if (x == p[x])
			return x;
		return p[x] = find(p[x]);
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return false;
		p[x] = y;
		return true;
	}
}
