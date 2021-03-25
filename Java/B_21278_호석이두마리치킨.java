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

// 플로이드-와샬 사용 (위 코드(O(n^4)보다 빠름 (O(n^3))) 
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class Main {
//
//	public static void main(String[] args) throws IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		
//		st = new StringTokenizer(br.readLine(), " ");
//		int N = Integer.parseInt(st.nextToken());
//		int M = Integer.parseInt(st.nextToken());
//		
//		final int INF = 10000;
//		int[][] arr = new int[N + 1][N + 1];
//		for(int i = 0; i < N + 1; i++) {
//			Arrays.fill(arr[i], INF);
//			arr[i][i] = 0;
//		}
//		for(int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			int from = Integer.parseInt(st.nextToken());
//			int to = Integer.parseInt(st.nextToken());
//			arr[from][to] = 1;
//			arr[to][from] = 1;
//		}
//		
//		for(int i = 1; i < N + 1; i++) {
//			for(int j = 1; j < N + 1; j++) {
//				for(int k = 1; k < N + 1; k++) {
//					arr[j][k] = Math.min(arr[j][i] + arr[i][k], arr[j][k]);
//				}
//			}
//		}
//		int x = 0, y = 0;
//		int res = Integer.MAX_VALUE;
//		for(int i = 1; i < N; i++) {
//			for(int j = i + 1; j < N + 1; j++) {
//				int sum = 0;
//				for(int k = 1; k < N + 1; k++) {
//					sum += Math.min(arr[i][k], arr[j][k]);
//				}
//				if(res > sum) {
//					res = sum;
//					y = i;
//					x = j;
//				}
//			}
//		}
//		System.out.println(y + " " + x + " " + res * 2);
//	}
//}
