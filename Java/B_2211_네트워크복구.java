import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_2211_네트워크복구 {

	static class Position implements Comparable<Position> {
		int to, w;

		public Position(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.w, o.w);
		}
	}

	static int N, M;
	static ArrayList<Position>[] arr;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			arr[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[from].add(new Position(to, w));
			arr[to].add(new Position(from, w));
		}
		int[] index = dijkstart();
		int cnt = 0;
		for (int i = 1; i < N + 1; i++) {
			if (index[i] != 0)
				cnt++;
		}
		sb.append(cnt + "\n");
		for (int i = 1; i < N + 1; i++) {
			if (index[i] != 0) {
				sb.append(index[i] + " " + i + "\n");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static int[] dijkstart() {
		PriorityQueue<Position> pq = new PriorityQueue<>();
		int[] val = new int[N + 1];
		int[] idx = new int[N + 1];
		Arrays.fill(val, 100001);
		val[0] = val[1] = 0;
		pq.offer(new Position(1, 0));
		while (!pq.isEmpty()) {
			Position now = pq.poll();

			if (now.w > val[now.to])
				continue;

			for (int i = 0; i < arr[now.to].size(); i++) {
				Position next = arr[now.to].get(i);
				int cost = next.w + val[now.to];
				if (val[next.to] > cost) {
					val[next.to] = cost;
					idx[next.to] = now.to;
					pq.offer(new Position(next.to, cost));
				}
			}
		}
		return idx;
	}
}
