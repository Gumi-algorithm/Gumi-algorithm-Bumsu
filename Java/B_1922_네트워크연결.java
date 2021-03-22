import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int x, y, w;

	public Edge(int x, int y, int w) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
	}
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.w, o.w);
	}
}

public class B_1922_네트워크연결 {

	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		for(int i = 0; i < N + 1; i++) parent[i] = i;
		Edge[] edges = new Edge[M];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(x, y, w);
		}
		int cnt = 0, res = 0;
		Arrays.sort(edges);
		for(Edge edge : edges) {
			if(union(edge.x, edge.y)) {
				res += edge.w;
				if(++cnt == N - 1) break;
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
