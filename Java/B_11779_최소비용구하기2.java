import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_11779_최단비용구하기2 {
	
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

	static int N, M, start, end;
	static ArrayList<Edge>[] edges;
	static StringBuilder sb = new StringBuilder();
	
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
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		dijkstra();
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] parent = new int[N + 1];
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
					parent[edge.to] = now; // 가장 가까운 전 지역 저장
					pq.offer(new Edge(edge.to, cost));
				}
			}
		}
		sb.append(value[end] + "\n");
		getRoute(parent);
	}
	
	static void getRoute(int[] parent) {
		Stack<Integer> route = new Stack<>();
		route.add(end);
		while(start != parent[end]) { // 시작점이 올때 까지 반복
			end = parent[end];
			route.add(end);
		}
		route.add(start);
		sb.append(route.size() + "\n");
		while(!route.isEmpty()) sb.append(route.pop() + " ");
		
	}
}
