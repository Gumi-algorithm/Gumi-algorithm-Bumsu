import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_14940_쉬운최단거리 {

	static int N, M;
	static int[][] arr, v;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		v = new int[N][M];
		int[] start = new int[2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 2) {
					start = new int[] {i, j};
				}else if(arr[i][j] == 1) {
					v[i][j] = -1;
				}
			}
		}
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {start[0], start[1]});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for(int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if(0 <= ny && ny < N && 0 <= nx && nx < M) {
					if(arr[ny][nx] == 1 && v[ny][nx] == -1) {
						v[ny][nx] = v[cur[0]][cur[1]] + 1;
						queue.offer(new int[] {ny, nx});
					}
				}
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(v[i][j] + " ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
