import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_15809_전국시대 {

	static int N, M;
	static int[][] parent;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1][2];
		for(int i = 1; i < N + 1; i++) {
			parent[i][0] = i;
			parent[i][1] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cmd = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			union(cmd, x, y);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 1; i < N + 1; i++) {
			if(parent[i][0] == i && parent[i][1] != 0) {
				pq.offer(parent[i][1]);
			}
		}
		System.out.println(pq.size());
		if(pq.size() != 0) {
			while(!pq.isEmpty()) sb.append(pq.poll() + " ");
			sb.setLength(sb.length() - 1);
			System.out.println(sb);
		}
	}
	
	static int find(int x) {
		if(parent[x][0] == x) return x;
		return parent[x][0] = find(parent[x][0]);
	}
	
	static void union(int cmd, int x, int y) {
		x = find(x);
		y = find(y);
		if(cmd == 1) {
			parent[y][0] = parent[x][0];
			parent[x][1] += parent[y][1];
		}else {
			if(parent[x][1] > parent[y][1]) {
				int temp = y;
				y = x;
				x = temp;
			}
			parent[y][1] -= parent[x][1];
			parent[x][0] = parent[y][0];
		}
	}
}
