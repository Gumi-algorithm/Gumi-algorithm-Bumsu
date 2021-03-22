import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class B_1753_최단경로 {
	
	static final int INF = 1000000000;
	static int V, E;
	static int[] d;
	static ArrayList<int[]>[] edges;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		d = new int[V + 1];
		for(int i = 0; i < V + 1; i++) {
			d[i] = INF;
		}
		edges = new ArrayList[V + 1];
		for(int i = 1; i < V + 1; i++) {
			edges[i] = new ArrayList<>();
		}
		int start = Integer.parseInt(br.readLine());
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[from].add(new int[] {to, w});
		}
		
		Dijkstra(start);
		for(int i = 1; i < V + 1; i++) {
			sb.append((d[i] == INF ? "INF" : d[i]) + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void Dijkstra(int start) {
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		d[start] = 0;
		que.offer(new int[] {start, 0});
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int dist = cur[1];
			int now = cur[0];
			
			if(d[now] < dist) {
				continue;
			}
			for(int[] i : edges[now]) {
				int cost = dist + i[1];
				if(cost < d[i[0]]) {
					d[i[0]] = cost;
					que.offer(new int[] {i[0], cost});
				}
			}
		}
	}
}
