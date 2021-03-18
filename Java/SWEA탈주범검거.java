import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA탈주범검거 {

	static int N, M, R, C, L, tot;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int[][] dir = {{},{0, 1, 2, 3}, {0, 1}, {2, 3}, {0, 3}, {1, 3}, {1, 2}, {0, 2}};
	static int[][] arr;
	static boolean[][] v;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T + 1; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			tot = 1;
			
			arr = new int[N][M];
			v = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 0) {
						v[i][j] = true;
					}
				}
			}
			bfs();
			sb.append("#" + t + " " + tot + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {arr[R][C], R, C, 1});
		v[R][C] = true;
		while(!que.isEmpty()) {
			int[] c = que.poll();
			if(c[3] >= L) {
				continue;
			}
			for(int i = 0; i < dir[c[0]].length; i++) {
				int ny = c[1] + dy[dir[c[0]][i]];
				int nx = c[2] + dx[dir[c[0]][i]];
				if(0 <= ny && ny < N && 0 <= nx && nx < M && !v[ny][nx]) {
					int d = dir[c[0]][i];
					if(d % 2 == 0) {
						d += 1;
					}else {
						d -= 1;
					}
					for(int j = 0; j < dir[arr[ny][nx]].length; j++ ) {
						if(dir[arr[ny][nx]][j] == d) {
							tot += 1;
							v[ny][nx] = true;
							que.offer(new int[] {arr[ny][nx], ny, nx, c[3] + 1});
							break;
						}
					}
				}
			}
		}
	}
}
