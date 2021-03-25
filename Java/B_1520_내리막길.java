import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1520_내리막길 {

	static int N, M;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int[][] arr, v;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		v = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				v[i][j] = -1; // 0으로 두면 시간초과가 난다.
			}
		}
		System.out.println(dfs(0, 0));
	}
//	v를 0으로 초기화하면, 방문을 해서 0이 된 건지, 아니면 방문을 안한건지 구분을 하지 못함
//	그래서 0으로 두면 안되는 곳도 일단 들어가기 때문에 안됨
	static int dfs(int y, int x) {
		if(v[y][x] != -1) {
			return v[y][x];
		}
		if(y == N - 1 && x == M - 1) {
			return 1;
		}
		v[y][x] = 0;
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(0 <= ny && ny < N && 0 <= nx && nx < M) {
				if(arr[ny][nx] < arr[y][x]) {
					v[y][x] += dfs(ny, nx);
				}
			}
		}
		return v[y][x];
	}
}
