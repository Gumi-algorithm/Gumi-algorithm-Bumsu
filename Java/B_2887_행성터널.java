import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_2887_행성터널 {

	static class Position implements Comparable<Position>{
		int idx, val;

		public Position(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.val, o.val);
		}
	}
	
	static class Node implements Comparable<Node>{
		int from, to, val;

		public Node(int from, int to, int val) {
			this.from = from;
			this.to = to;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.val, o.val);
		}
		
	}
	static int N;
	static int[] parent;
	static Position[] ax, ay, az;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		ax = new Position[N];
		ay = new Position[N];
		az = new Position[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			ax[i] = new Position(i, x);
			ay[i] = new Position(i, y);
			az[i] = new Position(i, z);
		}
		Arrays.sort(ax); // x좌표만 정렬
		Arrays.sort(ay); // y좌표만 정렬
		Arrays.sort(az); // z좌표만 정렬
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i = 0; i < N - 1; i++) { // 3좌표의 거리를 우선순위큐에 저장
			pq.offer(new Node(ax[i].idx, ax[i + 1].idx, Math.abs(ax[i].val - ax[i + 1].val)));
			pq.offer(new Node(ay[i].idx, ay[i + 1].idx, Math.abs(ay[i].val - ay[i + 1].val)));
			pq.offer(new Node(az[i].idx, az[i + 1].idx, Math.abs(az[i].val - az[i + 1].val)));
		}
		int cnt = 0;
		long res = 0;
		while(!pq.isEmpty()) { 
			Node now = pq.poll();
			if(union(now.from, now.to)) { // union하여 N - 1개를 하면  break
				res += now.val;
				cnt += 1;
				if(cnt == N - 1) break;
			}
		}
		System.out.println(res);
	}
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px == py) return false;
		parent[py] = px;
		return true;
	}
}
