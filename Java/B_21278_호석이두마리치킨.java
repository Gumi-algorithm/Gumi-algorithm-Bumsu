import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_21278_호석이두마리치킨 {

	static int N, M;
	static ArrayList<Integer>[] arr;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			arr[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			arr[to].add(from);
		}
		int y = 0, x = 0, min = Integer.MAX_VALUE;
		for(int i = 1; i < N; i++) { //모든 임의의 두 점에서 bfs
			for(int j = i + 1; j < N + 1; j++) {
				int val = bfs(i, j);
				if(min > val) {
					y = i;
					x = j;
					min = val;
				}
			}
		}
		System.out.println(y + " " + x + " " + min * 2);
	}
	
	static int bfs(int x, int y) {
		boolean[] v = new boolean[N + 1];
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {x, 0});
		que.offer(new int[] {y, 0});
		v[x] = v[y] = true;
		int res = 0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			
			for(int i = 0; i < arr[now[0]].size(); i++) {
				int next = arr[now[0]].get(i);
				if(!v[next]) {
					res += now[1] + 1;
					que.offer(new int[] {next, now[1] + 1});
					v[next] = true;
				}
			}
		}
		return res;
	}
}
