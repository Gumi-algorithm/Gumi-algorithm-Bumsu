import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
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

public class B_1197_최소스패닝트리 {

	static int V, E;
	static int[] parent;
	static Edge[] edges;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[V + 1];
		edges = new Edge[E];
		for(int i = 0; i < V + 1; i++)  parent[i] = i;
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(x, y, w);
		}
		
		Arrays.sort(edges);
		int cnt = 0, res = 0;
		for(Edge e : edges) {
			if(union(e.x, e.y)) {
				res += e.w;
				if(++cnt == V - 1) break;
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
}
