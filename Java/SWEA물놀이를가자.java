import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA물놀이를가자 {
	
	static int N, M, total;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static char[][] arr;
	static int[][] res;
	static Queue<int[]> que;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T + 1; t++) {
			
			total = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new char[N][M];
			res = new int[N][M];
			que = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				String data = br.readLine();
				for(int j = 0; j < M; j++) {
					arr[i][j] = data.charAt(j);
					if(arr[i][j] == 'W') {
						que.offer(new int[] {i, j});
					}
				}
			}
			bfs();
			sb.append("#" + t + " " + total + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	static void bfs() {
		boolean[][] v = new boolean[N][M];
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			v[cur[0]][cur[1]] = true;
			for(int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if(0 <= ny && ny < N && 0 <= nx && nx < M && !v[ny][nx]) {
					if(arr[ny][nx] == 'L' ) {
						v[ny][nx] = true;
						res[ny][nx] = res[cur[0]][cur[1]] + 1;
						total += res[ny][nx];
						que.offer(new int[] {ny, nx});
					}
				}
			}
		}
	}
}
