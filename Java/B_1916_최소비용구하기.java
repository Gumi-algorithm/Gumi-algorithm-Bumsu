import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_1916_최소비용구하기 {
	
	static class Edge implements Comparable<Edge>{
		int to, w;

		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}

	static int N, M;
	static ArrayList<Edge>[] edges;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		edges = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			edges[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[from].add(new Edge(to, w));
		}
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		System.out.println(dijkstra(start, end));
	}
	
	static int dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		int[] value = new int[N + 1];
		Arrays.fill(value, Integer.MAX_VALUE);
		value[start] = 0;
		
		while(!pq.isEmpty()) {
			int now = pq.peek().to;
			int w = pq.peek().w;
			pq.poll();
			
			if(now == end) break;
			if(value[now] < w) continue;
			
			for(Edge edge : edges[now]) {
				int cost = w + edge.w;
				if(value[edge.to] > cost) {
					value[edge.to] = cost;
					pq.offer(new Edge(edge.to, cost));
				}
			}
		}
		return value[end];
	}
}
