import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA등산로조성 {

	static int N, K, len, max;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int[][] arr;
	static boolean[][] v;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			max = Integer.MIN_VALUE;
			len = 0;
			arr = new int[N][N];
			v = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, arr[i][j]);
				}
			}
			ArrayList<int[]> list = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][j] == max) {
						list.add(new int[] {i, j});
					}
				}
			}
			
			for(int i = 0; i < list.size(); i++) {
				v[list.get(i)[0]][list.get(i)[1]] = true;
				dfs(list.get(i), true, 1);
				v[list.get(i)[0]][list.get(i)[1]] = false;
			}
			sb.append("#" + t + " " + len + "\n");
		}
        sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void dfs(int[] cur, boolean p, int cnt) {
		
		for(int i = 0; i < 4; i++) {
			int ny = cur[0] + dy[i];
			int nx = cur[1] + dx[i];
			if(0 <= ny && ny < N && 0 <= nx && nx < N && !v[ny][nx]) {
				if(arr[ny][nx] < arr[cur[0]][cur[1]]) {
					v[ny][nx] = true;
					dfs(new int[] {ny, nx}, p, cnt + 1);
					v[ny][nx] = false;
				}else {
					if(p && arr[ny][nx] < arr[cur[0]][cur[1]] + K) {
						int height = arr[ny][nx];
						arr[ny][nx] = arr[cur[0]][cur[1]] - 1;
						v[ny][nx] = true;
						dfs(new int[] {ny, nx}, !p, cnt + 1);
						v[ny][nx] = false;
						arr[ny][nx] = height;
					}
				}
			}
		}
		len = Math.max(len, cnt);
	}
}