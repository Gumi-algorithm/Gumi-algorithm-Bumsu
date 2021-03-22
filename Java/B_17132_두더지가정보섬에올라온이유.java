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
		return Integer.compare(o.w, this.w);
	}
}

public class B_17132_두더지가정보섬에올라온이유 {

	static int N;
	static long ans = 0; // 최악 : 200 * (50,000) *(99,999) =  999,9990,000,000
	static int[] parent, count;
	static Edge[] edge;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		count = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
			count[i] = 1;
		}
		
		edge = new Edge[N - 1];
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edge[i] = new Edge(x, y, w);
		}
		
		Arrays.sort(edge); // 내림차순
		for(int i = 0; i < N - 1; i++) {
			union(edge[i].x, edge[i].y, edge[i].w);
		}
		System.out.println(ans);
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y, int w) {
		x = find(x);
		y = find(y);
		if(count[x] > count[y]) {
			int temp = y;
			y = x;
			x = temp;
		}
		ans += (long)(count[x] * count[y]) * (long)w; 
		// 내림차순이기 때문에 지금 들어온 w보다 큰 w은 존재하지 않으므로 tree1의 v갯수 * tree2의 v갯수만큼 곱함
		count[y] += count[x];
		parent[x] = parent[y];
	}
}
