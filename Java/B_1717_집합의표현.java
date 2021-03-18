import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1717_집합의표현 {

	static int N, M;
	static int[] head, rank;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		head = new int[N + 1];
		rank = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			head[i] = i;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cmd = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if(cmd == 0) {
				Union(from, to);
			}else {
				if(findSet(from) == findSet(to)) {
					sb.append("YES\n");
				}else {
					sb.append("NO\n");
				}
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
//	Rank
	static int findSet(int x) {
		if(head[x] == x) {
			return x;
		}else {
			return findSet(head[x]);
		}
	}
	static void Union(int x, int y) {
		y = findSet(y);
		x = findSet(x);
		if(rank[y] == rank[x]) {
			head[y] = head[x];
			rank[y] += 1;
		}else {
			head[Math.max(y, x)] = head[Math.min(y, x)];
		}
	}
//	Path Compression
//	static int findSet(int x) {
//		if(head[x] == x) return x;
//		return head[x] = findSet(head[x]);
//	}
//	
//	static void Union(int x, int y) {
//		head[findSet(x)] = head[findSet(y)];
//	}
}
