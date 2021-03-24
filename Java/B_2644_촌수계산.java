import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2644_촌수계산 {

	static int N, M, start, end;
	static int[] parent;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		boolean[] v = new boolean[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			parent[y] = x;
		}
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {start, 0});
		v[start] = true;
		int res = -1;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			if(now[0] == end) {
				res = now[1];
				break;
			}
			if(!v[parent[now[0]]]) q.offer(new int[] {parent[now[0]], now[1] + 1});
			for(int i = 0; i < N + 1; i++) {
				if(parent[i] == now[0] && !v[i]) {
					v[i] = true;
					q.offer(new int[] {i, now[1] + 1});
				}
			}
		}
		System.out.println(res);
	}
	
}
