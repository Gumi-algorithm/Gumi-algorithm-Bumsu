import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_9370_미확인도착지 {

	static class Position implements Comparable<Position> {
		int to, w;

		public Position(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.w, o.w);
		}
	}

	static int N, M, T, S, G, H;
	static int[] idx;
	static ArrayList<Position>[] arr;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			S = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			arr = new ArrayList[N + 1];
			for (int i = 0; i < N + 1; i++) {
				arr[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = 2 * Integer.parseInt(st.nextToken());
				if (check(from, to)) { // 지나야하는 곳은 2배의 -1(홀수)
					arr[from].add(new Position(to, w - 1));
					arr[to].add(new Position(from, w - 1));
				} else { // 안지나도 되는 곳은 2배 (짝수)
					arr[from].add(new Position(to, w));
					arr[to].add(new Position(from, w));
				}
			}
			idx = new int[T];
			for (int i = 0; i < T; i++) {
				idx[i] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(idx);
			int[] v = djikstra(); // 결과가 홀수이면 지난 것이고 아니면 안지난것
			// 안지난 것과 지난것의 원래 값이 같다면 지난 것은 -1했기 때문에 무조건 그 경로가 나옴
			for (int i = 0; i < T; i++) {
				if (v[idx[i]] % 2 == 1) {
					sb.append(idx[i] + " ");
				}
			}
			if (sb.length() != 0) {
				sb.setLength(sb.length() - 1);
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static boolean check(int a, int b) {
		if ((a == G && b == H) || (a == H && b == G))
			return true;
		return false;
	}

	static int[] djikstra() {
		PriorityQueue<Position> pq = new PriorityQueue<>();
		int[] val = new int[N + 1];
		Arrays.fill(val, 100000000);
		val[0] = val[S] = 0;
		pq.offer(new Position(S, 0));
		while (!pq.isEmpty()) {
			Position now = pq.poll();

			if (now.w > val[now.to])
				continue;

			for (int i = 0; i < arr[now.to].size(); i++) {
				Position next = arr[now.to].get(i);
				int cost = next.w + val[now.to];
				if (val[next.to] > cost) {
					val[next.to] = cost;
					pq.offer(new Position(next.to, cost));
				}
			}
		}
		return val;
	}
}
