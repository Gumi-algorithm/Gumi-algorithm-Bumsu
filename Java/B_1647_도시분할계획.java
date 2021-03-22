import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_1647_도시분할계획 {

	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		for(int i = 0; i < N + 1; i++) parent[i] = i;
		
		PriorityQueue<Edge> que = new PriorityQueue<>(); 
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			que.offer(new Edge(x, y, w));
		}
		int cnt = 0, res = 0;
		for(int i = 0; i < M; i++) {
			Edge edge = que.poll();
			if(union(edge.x, edge.y)) {
				res += edge.w;
				if(++cnt == N - 2) break;
			}
		}
		System.out.println(res);
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return false;
		parent[x] = parent[y];
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int x, y, w;

		public Edge(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
