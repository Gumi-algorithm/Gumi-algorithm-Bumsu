import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int y, w;

	public Edge(int y, int w) {
		this.y = y;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.w, o.w);
	}
}

public class B_1504_특정한최단경로 {

	static final int INF = 10000000;
	static int N, E;
	static ArrayList<Edge>[] edge;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edge = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			edge[i] = new ArrayList<>();
		}
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edge[from].add(new Edge(to, w));
			edge[to].add(new Edge(from, w));
		}
		st = new StringTokenizer(br.readLine(), " ");
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		int[] res = new int[2];
		res[0] = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
		res[1] = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
		if(Math.min(res[0], res[1]) >= INF) {
			System.out.println(-1);
		}else {
			System.out.println(Math.min(res[0], res[1]));
		}
	}
	
	static int dijkstra(int start, int end) {
		int[] D = new int[N + 1];
		Arrays.fill(D, INF);
		D[start] = 0;
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		queue.offer(new Edge(start, 0));
		
		while(!queue.isEmpty()) {
			Edge cur = queue.poll();
			
			if(cur.y == end) break;
			if(D[cur.y] < cur.w) continue;
			
			for(Edge e : edge[cur.y]) {
				int cost = cur.w + e.w;
				if(D[e.y] > cost) {
					D[e.y] = cost;
					queue.offer(new Edge(e.y, cost));
				}
			}
		}
		return D[end];
	}
}
