import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1260_DFS와BFS {

	static int N, M, V;
	static int[][] arr;
	static int[] result;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		check = new boolean[N + 1];
		arr = new int[N + 1][N + 1];
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			arr[to][from] = 1;
			arr[from][to] = 1;
		}
		result = new int[N];
		sb.append(V + " ");
		dfs(V, 1);
		sb.setLength(sb.length() - 1);
		sb.append("\n");
		check[V] = false;
		bfs(V);sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void dfs(int index, int cnt) {
		check[index] = true;
		for(int i = 1; i < N + 1; i++) {
			if(arr[index][i] == 1 && !check[i]) {
				sb.append(i + " ");
				dfs(i, cnt + 1);
			}
		}
	}
	static void bfs(int start) {
		Queue<Integer> que = new LinkedList<>();
		que.offer(start);
		while(!que.isEmpty()) {
			int curr = que.poll();
			sb.append(curr + " ");
			for(int i = 1; i < N + 1; i++) {
				if(arr[curr][i] == 1 && check[i]) {
					que.offer(i);
					check[i] = false;
				}
			}
		}
	}
}