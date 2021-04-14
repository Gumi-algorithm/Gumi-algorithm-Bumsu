import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_1238_파티 {

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

	static int N, M, X;
	static ArrayList<Position>[] arr;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
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
		}
		int[] res = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			int[] value = djikstra(i);
			if (i == X) {
				for (int j = 1; j < N + 1; j++) {
					res[j] += value[j];
				}
			} else {
				res[i] += value[X];
			}
		}
		int max = 0;
		for (int i = 1; i < N + 1; i++) {
			max = Math.max(max, res[i]);
		}
		System.out.println(max);
	}

	static int[] djikstra(int start) {
		int[] v = new int[N + 1];
		Arrays.fill(v, 100001);
		v[0] = 0;
		v[start] = 0;
		PriorityQueue<Position> pq = new PriorityQueue<>();
		pq.offer(new Position(start, 0));
		while (!pq.isEmpty()) {
			Position now = pq.poll();

			if (now.w > v[now.to])
				continue;
			if (start != X && now.to == X) {
				break;
			}

			for (int i = 0; i < arr[now.to].size(); i++) {
				Position next = arr[now.to].get(i);
				int cost = next.w + v[now.to];
				if (v[next.to] > cost) {
					v[next.to] = cost;
					pq.offer(new Position(next.to, cost));
				}
			}
		}
		return v;
	}
}
