import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_11725_트리의부모찾기 {

	static int N;
	static int[] p;
	static ArrayList<Integer>[] arr;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		p = new int[N + 1];
		arr = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x].add(y);
			arr[y].add(x);
		}
		bfs(1);
		for(int i = 2; i < N + 1; i++) {
			sb.append(p[i] + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void bfs(int root) {
		boolean[] v = new boolean[N + 1];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(root);
		v[root] = true;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int i = 0; i < arr[now].size(); i++) {
				int next = arr[now].get(i);
				if(!v[next]) {
					queue.offer(next);
					v[next] = true;
					p[next] = now;
				}
			}
		}
	}
}
